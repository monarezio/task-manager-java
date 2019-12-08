package cz.ucl.logic.data.mappers.factory;

import cz.ucl.logic.data.dao.UserDAO;
import cz.ucl.logic.data.mappers.DAOToEntity.*;
import cz.ucl.logic.data.mappers.definitions.DAOToEntity.*;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.*;
import cz.ucl.logic.data.mappers.entityToDAO.*;

public class MapperFactory implements IMapperFactory {

    private final IColorDAOToColorMapper colorDAOToColorMapper;
    private final ICategoryDAOToCategoryMapper categoryDAOToCategoryMapper;
    private final ITagDAOToTagMapper tagDAOToTagMapper;
    private final ITaskDAOToTaskMapper taskDAOToTaskMapper;
    private final IUserDAOToUserMapper userDAOToUserMapper;

    private final IColorToColorDAOMapper colorToColorDAOMapper;
    private final ICategoryToCategoryDAOMapper categoryToCategoryDAOMapper;
    private final ITagToTagDAOMapper tagToTagDAOMapper;
    private final ITaskToTaskDAOMapper taskToTaskDAOMapper;
    private final IUserToUserDAOMapper userToUserDAOMapper;

    public MapperFactory() {
        taskDAOToTaskMapper = new TaskDAOToTaskMapper(this);
        tagDAOToTagMapper = new TagDAOToTagMapper(this);
        categoryDAOToCategoryMapper = new CategoryDAOToCategoryMapper(this);
        colorDAOToColorMapper = new ColorDAOToColorMapper();
        userDAOToUserMapper = new UserDAOToUserMapper();
        colorToColorDAOMapper = new ColorToColorDAOMapper();
        categoryToCategoryDAOMapper = new CategoryToCategoryDAOMapper(this);
        tagToTagDAOMapper = new TagToTagDAOMapper(this);
        taskToTaskDAOMapper = new TaskToTaskDAOMapper();
        userToUserDAOMapper = new UserToUserDAOMapper();
    }

    @Override
    public ICategoryDAOToCategoryMapper getCategoryDAOToCategoryMapper() {
        return categoryDAOToCategoryMapper;
    }

    @Override
    public IColorDAOToColorMapper getColorDaoToColorMapper() {
        return colorDAOToColorMapper;
    }

    @Override
    public ITagDAOToTagMapper getTagDAOToTagMapper() {
        return tagDAOToTagMapper;
    }

    @Override
    public ITaskDAOToTaskMapper getTaskDAOToTaskMapper() {
        return taskDAOToTaskMapper;
    }

    @Override
    public IUserDAOToUserMapper getUserDAOUserDaoToUserMapper() {
        return userDAOToUserMapper;
    }

    @Override
    public ICategoryToCategoryDAOMapper getCategoryToCategoryDaoMapper() {
        return categoryToCategoryDAOMapper;
    }

    @Override
    public IColorToColorDAOMapper getColorToColorDaoMapper() {
        return colorToColorDAOMapper;
    }

    @Override
    public ITagToTagDAOMapper getTagToTagDaoMapper() {
        return tagToTagDAOMapper;
    }

    @Override
    public ITaskToTaskDAOMapper getTaskToTaskDaoMapper() {
        return taskToTaskDAOMapper;
    }

    @Override
    public IUserToUserDAOMapper getUserToUserDaoMapper() {
        return userToUserDAOMapper;
    }
}
