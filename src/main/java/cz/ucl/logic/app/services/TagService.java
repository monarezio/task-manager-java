package cz.ucl.logic.app.services;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.services.definition.ITagService;
import cz.ucl.logic.app.services.definition.IUserService;

// TODO: Implement this
public class TagService implements ITagService {

    private final IUserService userService;

    public TagService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ITag[] getAllTags() {
        return new ITag[0];
    }

    @Override
    public ITag getTagById(int id) {
        return null;
    }

    @Override
    public void createTag(String title) {

    }

    @Override
    public void createTag(String title, Color color) {

    }

    @Override
    public void updateTag(int id, String title, Color color) {

    }

    @Override
    public void destroyTag(int id) {

    }
}
