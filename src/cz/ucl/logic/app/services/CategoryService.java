package cz.ucl.logic.app.services;

import cz.ucl.logic.app.entities.definition.Color;
import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.services.definition.ICategoryService;
import cz.ucl.logic.app.services.definition.IUserService;

// TODO: Implement this
public class CategoryService implements ICategoryService {

    private IUserService userService;

    public CategoryService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public ICategory[] getAllCategories() {
        return new ICategory[0];
    }

    @Override
    public ICategory getCategoryById(int id) {
        return null;
    }

    @Override
    public void createCategory(String title) {

    }

    @Override
    public void createCategory(String title, Color color) {

    }

    @Override
    public void updateCategory(int id, String title, Color color) {

    }

    @Override
    public void destroyCategory(int id) {

    }
}
