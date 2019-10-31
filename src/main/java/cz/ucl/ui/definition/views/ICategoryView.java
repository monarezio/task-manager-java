package cz.ucl.ui.definition.views;

import cz.ucl.logic.app.entities.definition.ICategory;

/** Views are used only for formatting purposes. They are stateless. */
public interface ICategoryView {
    String formatCategoryList(ICategory[] categoryList);
    String formatCategory(ICategory category);
}
