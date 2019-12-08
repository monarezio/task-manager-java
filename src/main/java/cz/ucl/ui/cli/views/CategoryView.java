package cz.ucl.ui.cli.views;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.ui.definition.views.ICategoryView;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CategoryView implements ICategoryView {
    @Override
    public String formatCategoryList(ICategory[] categoryList) {
        return null;
    }

    @Override
    public String formatCategory(ICategory category) {
        StringBuilder sb = new StringBuilder("Detail kategorie:\n");
        sb.append("ID: ");
        sb.append(category.getId());
        sb.append("\n");
        sb.append("Název: ");
        sb.append(category.getTitle());
        sb.append("\n");
        sb.append("Barva: ");
        sb.append(category.getColor());
        sb.append("\n");
        sb.append("Úkoly: ");
        sb.append(
                Arrays.stream(category.getTasks())
                        .map(ITask::getTitle)
                        .collect(Collectors.joining(", "))
        );

        return sb.toString();
    }
}
