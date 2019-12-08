package cz.ucl.logic.data.managers;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.logic.app.entities.definition.task.ITasksCollection;
import cz.ucl.logic.app.entities.task.TasksCollection;
import cz.ucl.logic.app.services.definition.TasksOrder;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.dao.TaskDAO;
import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.data.managers.definitions.ITaskManager;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITaskDAOToTaskMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ICategoryToCategoryDAOMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ITagToTagDAOMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ITaskToTaskDAOMapper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TaskManager implements ITaskManager {

    private final int perPage = 30; // TODO: Change later

    private final IHibernateSessionFactory hibernateSessionFactory;
    private final ICategoryToCategoryDAOMapper categoryToCategoryDAO;
    private final ITagToTagDAOMapper tagToTagDAOMapper;
    private final ITaskDAOToTaskMapper taskDAOToTaskMapper;

    public TaskManager(
            IHibernateSessionFactory hibernateSessionFactory,
            ICategoryToCategoryDAOMapper categoryToCategoryDAOMapper,
            ITagToTagDAOMapper tagToTagDAOMapper,
            ITaskDAOToTaskMapper taskDAOToTaskMapper
    ) {
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.categoryToCategoryDAO = categoryToCategoryDAOMapper;
        this.tagToTagDAOMapper = tagToTagDAOMapper;
        this.taskDAOToTaskMapper = taskDAOToTaskMapper;
    }

    @Override
    public ITasksCollection getAllByFilter(long userId, ITaskFilter filter) {
        AtomicReference<ITasksCollection> tasksCollection = new AtomicReference<>();
        hibernateSessionFactory.createSession(s -> {
            StringBuilder queryBuilder = new StringBuilder("from TaskDAO where user_id = :user_id");

            if (filter.getByCategory() != null)
                queryBuilder.append(" and category_id = :category_id");
            if (filter.getByTags().length != 0)
                queryBuilder.append(" and tag_id in :tag_ids");

            if (filter.getTaskDoneStatus() == TaskDoneStatus.IS_DONE)
                queryBuilder.append(" and isdone = true");
            else if (filter.getTaskDoneStatus() == TaskDoneStatus.IS_NOT_DONE)
                queryBuilder.append(" and isdone = false");

            queryBuilder.append(" and (title LIKE :search OR note LIKE :search_2)");

            Query countQuery = s.createQuery("select COUNT(*) " + queryBuilder.toString());
            int amountOfPages = ((int) ((Long) setParams(countQuery, userId, filter).uniqueResult() / perPage)) + 1;

            Query<TaskDAO> query = s.createQuery(queryBuilder.toString(), TaskDAO.class);
            query = setParams(query, userId, filter)
                    .setFirstResult((filter.getPage() - 1) * filter.getPage())
                    .setMaxResults(perPage);
            ITask[] tasks = query.getResultStream().map(taskDAOToTaskMapper::mapOrNull).toArray(ITask[]::new);
            tasksCollection.set(new TasksCollection(filter.getPage(), amountOfPages, tasks));
        });

        return tasksCollection.get();
    }

    @Override
    public ITask get(long userId, long taskId) {
        AtomicReference<ITask> task = new AtomicReference<>();
        hibernateSessionFactory.createSession(s -> {
            TaskDAO taskDAO = s.createQuery("from TaskDAO where user_id = :user_id AND id = :task_id ", TaskDAO.class)
                    .setParameter("user_id", userId)
                    .setParameter("task_id", taskId)
                    .getSingleResult();

            task.set(taskDAOToTaskMapper.deepMapOrNull(taskDAO));
        });

        return task.get();
    }

    @Override
    public void add(long userId, String title, String note, ICategory category, ITag[] tags, LocalDateTime deadline, boolean isDone) {
        hibernateSessionFactory.createSession(s -> {
            CategoryDAO categoryDAO = categoryToCategoryDAO.mapOrNull(category);

            TaskDAO taskDAO = new TaskDAO();
            taskDAO.setCategory(categoryDAO);
            taskDAO.setTitle(title);
            taskDAO.setNote(note);
            taskDAO.setTags(
                    Arrays.stream(tags)
                            .map(tagToTagDAOMapper::mapOrNull)
                            .collect(Collectors.toList())
            );
            taskDAO.setDeadline(deadline);
            taskDAO.setDone(isDone);
            taskDAO.setUser(new UserDAO(userId));

            s.save(taskDAO);
        });
    }

    @Override
    public void update(long taskId, long userId, String title, String note, ICategory category, ITag[] tags, LocalDateTime deadline, boolean isDone) {
        hibernateSessionFactory.createSession(s -> {
            TaskDAO task = getTaskForUser(taskId, userId, s);

            task.setTitle(title);
            task.setNote(note);
            task.setCategory(categoryToCategoryDAO.mapOrNull(category));
            task.setTags(
                    Arrays.stream(tags)
                            .map(tagToTagDAOMapper::mapOrNull)
                            .collect(Collectors.toList())
            );
            task.setDeadline(deadline);
            task.setDone(isDone);
            task.setUpdated(LocalDateTime.now());

            s.update(task);
        });
    }

    @Override
    public void destroy(long taskId, long userId) {
        hibernateSessionFactory.createSession(s -> {
            TaskDAO task = getTaskForUser(taskId, userId, s);
            s.delete(task);
        });
    }

    private TaskDAO getTaskForUser(long taskId, long userId, Session s) {
        return s.createQuery("from TaskDAO where user_id = :user_id AND id = :task_id", TaskDAO.class)
                .setParameter("user_id", userId)
                .setParameter("task_id", taskId)
                .getSingleResult();
    }

    private String getCorrectOrderQuery(TasksOrder order) {
        String start = "order by isdone";

        switch (order) {
            case BY_DEADLINE_AT_ASC:
                return start + ", deadline ASC";
            case BY_DEADLINE_AT_DESC:
                return start + ", deadline DESC";
            case BY_UPDATED_AT_DESC:
                return start + ", updated DESC";
            case BY_UPDATED_AT_ASC:
                return start + ", updated ASC";
            case BY_CREATED_AT_ASC:
                return start + ", created ASC";
            case BY_CREATED_AT_DESC:
                return start + ", created DESC";
            case BY_TITLE_ASC:
                return start + ", title ASC";
            case BY_TITLE_DESC:
                return start + ", title DESC";
        }

        return start;
    }

    private Query setParams(Query query, long userId, ITaskFilter filter) {
        query.setParameter("user_id", userId);

        if (filter.getByCategory() != null)
            query.setParameter("category_id", filter.getByCategory().getId());
        if (filter.getByTags().length != 0)
            query.setParameter("tag_ids", Arrays.stream(filter.getByTags()).mapToInt(i -> (int) i.getId()));

        query.setParameter("search", "%" + filter.getSearchKeyword() + "%");
        query.setParameter("search_2", "%" + filter.getSearchKeyword() + "%");

        return query;
    }
}
