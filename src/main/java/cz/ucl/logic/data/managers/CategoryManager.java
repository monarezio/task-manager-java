package cz.ucl.logic.data.managers;

import cz.ucl.logic.app.entities.Category;
import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.data.dao.CategoryDAO;
import cz.ucl.logic.data.dao.ColorDAO;
import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.data.managers.definitions.ICategoryManager;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ICategoryDAOToCategoryMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;
import cz.ucl.logic.exceptions.InvalidColorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class CategoryManager implements ICategoryManager {

    private final IHibernateSessionFactory hibernateSessionFactory;
    private final ICategoryDAOToCategoryMapper categoryDaoToCategory;
    private final IColorToColorDAOMapper colorToColorDAOMapper;

    public CategoryManager(IHibernateSessionFactory hibernateSessionFactory, ICategoryDAOToCategoryMapper categoryDaoToCategory, IColorToColorDAOMapper colorToColorDAOMapper) {
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.categoryDaoToCategory = categoryDaoToCategory;
        this.colorToColorDAOMapper = colorToColorDAOMapper;
    }

    @Override
    public ICategory[] getAllForUser(long id) {
        List<ICategory> result = new ArrayList<>();

        hibernateSessionFactory.createSession(s -> {
            result.addAll(
                    s.createQuery("from CategoryDAO where user_id = ?0", CategoryDAO.class)
                            .setParameter(0, id)
                            .getResultStream()
                            .map(categoryDaoToCategory::mapOrNull)
                            .collect(Collectors.toList())
            );
        });

        return result.toArray(new ICategory[0]);
    }

    @Override
    public void createCategory(String title, Color color, long userId) throws InvalidColorException {
        try {
            final ColorDAO colorDAO = colorToColorDAOMapper.mapOrNull(color);

            hibernateSessionFactory.createSession(s -> {
                CategoryDAO category = new CategoryDAO();
                category.setTitle(title);
                category.setColor(colorDAO);
                category.setUser(new UserDAO(userId));
                s.save(category);
            });
        } catch (IllegalArgumentException e) {
            throw new InvalidColorException("Invalid color");
        }
    }

    @Override
    public void createCategory(String title, long userId) {
        try {
            createCategory(title, null, userId);
        } catch (InvalidColorException ignored) {
        }
    }

    @Override
    public void destroyCategory(long userId, long id) {
        hibernateSessionFactory.createSession(s -> {
            CategoryDAO category = s.createQuery("from CategoryDAO where id = ?0 AND user_id = ?1", CategoryDAO.class)
                    .setParameter(0, id)
                    .setParameter(1, userId)
                    .getSingleResult();

            s.delete(category);
        });
    }

    @Override
    public ICategory getCategoryForUserById(long userId, long categoryId) {
        AtomicReference<ICategory> result = new AtomicReference<>();
        hibernateSessionFactory.createSession(s -> result.set(
                categoryDaoToCategory.deepMapOrNull(
                        s.createQuery("select cat from CategoryDAO cat LEFT JOIN FETCH cat.tasks where cat.id = ?0 AND cat.user.id = ?1", CategoryDAO.class)
                                .setParameter(0, categoryId)
                                .setParameter(1, userId)
                                .getSingleResult()
                )
        ));
        return result.get();
    }

    @Override
    public void updateCategory(long userId, long id, String title, Color color) {
        hibernateSessionFactory.createSession(s -> {
            CategoryDAO category = s.createQuery("from CategoryDAO where id = ?0 AND user_id = ?1", CategoryDAO.class)
                    .setParameter(0, id)
                    .setParameter(1, userId)
                    .getSingleResult();
            category.setColor(colorToColorDAOMapper.mapOrNull(color));
            category.setTitle(title);
            category.setUpdated(LocalDateTime.now());

            s.update(category);
        });
    }
}
