package cz.ucl.logic.data.managers;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.data.dao.ColorDAO;
import cz.ucl.logic.data.dao.TagDAO;
import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.hibernate.definitions.IHibernateSessionFactory;
import cz.ucl.logic.data.managers.definitions.ITagManager;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.ITagDAOToTagMapper;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.IColorToColorDAOMapper;
import cz.ucl.logic.exceptions.InvalidColorException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class TagManager implements ITagManager {

    private final IHibernateSessionFactory hibernateSessionFactory;
    private final ITagDAOToTagMapper tagDAOToTagMapper;
    private final IColorToColorDAOMapper colorToColorDAOMapper;

    public TagManager(ITagDAOToTagMapper tagDAOToTagMapper, IColorToColorDAOMapper colorToColorDAOMapper, IHibernateSessionFactory hibernateSessionFactory) {
        this.hibernateSessionFactory = hibernateSessionFactory;
        this.colorToColorDAOMapper = colorToColorDAOMapper;
        this.tagDAOToTagMapper = tagDAOToTagMapper;
    }

    @Override
    public ITag[] getAllForUser(long id) {
        List<ITag> result = new ArrayList<>();

        hibernateSessionFactory.createSession(s -> {
            result.addAll(
                    s.createQuery("from TagDAO where user_id = ?0", TagDAO.class)
                            .setParameter(0, id)
                            .getResultStream()
                            .map(tagDAOToTagMapper::mapOrNull)
                            .collect(Collectors.toList())
            );
        });

        return result.toArray(new ITag[0]);
    }

    @Override
    public void createTag(String title, Color color, long userId) throws InvalidColorException {
        try {
            final ColorDAO colorDAO = colorToColorDAOMapper.mapOrNull(color);

            hibernateSessionFactory.createSession(s -> {
                TagDAO tag = new TagDAO();
                tag.setTitle(title);
                tag.setColor(colorDAO);
                tag.setUser(new UserDAO(userId));
                s.save(tag);
            });
        } catch (IllegalArgumentException e) {
            throw new InvalidColorException("Invalid color");
        }
    }

    @Override
    public void createTag(String title, long userId) {
        try {
            createTag(title, null, userId);
        } catch (InvalidColorException ignored) {
        }
    }

    @Override
    public void destroyTag(long id) {
        hibernateSessionFactory.createSession(s -> s.delete(new TagDAO(id)));
    }

    @Override
    public ITag getTagForUserById(long userId, long tagId) {
        AtomicReference<ITag> result = new AtomicReference<>();
        hibernateSessionFactory.createSession(s -> {
            result.set(
                    tagDAOToTagMapper.mapOrNull(
                            s.createQuery("from TagDAO where id = ?0 AND user_id = ?1", TagDAO.class)
                                    .setParameter(0, tagId)
                                    .setParameter(1, userId)
                                    .getSingleResult()
                    )
            );
        });
        return result.get();
    }

    @Override
    public void updateTag(long id, String title, Color color) {
        hibernateSessionFactory.createSession(s -> {
            TagDAO tag = s.get(TagDAO.class, id);
            tag.setColor(colorToColorDAOMapper.mapOrNull(color));
            tag.setTitle(title);

            s.update(tag);
        });
    }
}
