package cz.ucl.logic.app.services;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.services.definition.ICategoryService;
import cz.ucl.logic.app.services.definition.IUserService;
import cz.ucl.logic.data.managers.definitions.ICategoryManager;
import cz.ucl.logic.exceptions.InvalidColorException;

public class CategoryService implements ICategoryService {

    private final IUserService userService;
    private final ICategoryManager categoryManager;

    public CategoryService(IUserService userService, ICategoryManager categoryManager) {
        this.userService = userService;
        this.categoryManager = categoryManager;
    }

    @Override
    public ICategory[] getAllCategories() {
        return categoryManager.getAllForUser(userService.getUserLoggedIn().getId());
    }

    @Override
    public ICategory getCategoryById(int id) {
        return categoryManager.getCategoryForUserById(userService.getUserLoggedIn().getId(), id);
    }

    @Override
    public void createCategory(String title) throws InvalidColorException {
        categoryManager.createCategory(title, userService.getUserLoggedIn().getId());
    }

    @Override
    public void createCategory(String title, Color color) throws InvalidColorException {
        categoryManager.createCategory(title, color, userService.getUserLoggedIn().getId());
    }

    @Override
    public void updateCategory(int id, String title, Color color) {
        categoryManager.updateCategory(id, title, color);
    }

    @Override
    public void destroyCategory(int id) {
        categoryManager.destroyCategory(id);
    }
}
