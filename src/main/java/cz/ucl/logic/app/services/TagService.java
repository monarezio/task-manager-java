package cz.ucl.logic.app.services;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.services.definition.ITagService;
import cz.ucl.logic.app.services.definition.IUserService;
import cz.ucl.logic.data.managers.definitions.ITagManager;
import cz.ucl.logic.exceptions.InvalidColorException;
import cz.ucl.logic.exceptions.TagInUseException;

public class TagService implements ITagService {

    private final IUserService userService;
    private final ITagManager tagManager;

    public TagService(IUserService userService, ITagManager tagManager) {
        this.userService = userService;
        this.tagManager = tagManager;
    }

    @Override
    public ITag[] getAllTags() {
        return tagManager.getAllForUser(userService.getUserLoggedIn().getId());
    }

    @Override
    public ITag getTagById(int id) {
        return tagManager.getTagForUserById(userService.getUserLoggedIn().getId(), id);
    }

    @Override
    public void createTag(String title) {
        tagManager.createTag(title, userService.getUserLoggedIn().getId());
    }

    @Override
    public void createTag(String title, Color color) throws InvalidColorException {
        tagManager.createTag(title, color, userService.getUserLoggedIn().getId());
    }

    @Override
    public void updateTag(int id, String title, Color color) {
        tagManager.updateTag(userService.getUserLoggedIn().getId(), id, title, color);
    }

    @Override
    public void destroyTag(int id) throws TagInUseException {
        tagManager.destroyTag(userService.getUserLoggedIn().getId(), id);
    }
}
