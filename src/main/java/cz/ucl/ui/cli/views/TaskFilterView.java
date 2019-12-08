package cz.ucl.ui.cli.views;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.TaskDoneStatus;
import cz.ucl.logic.app.entities.definition.task.ITaskFilter;
import cz.ucl.ui.definition.views.ITaskFilterView;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TaskFilterView implements ITaskFilterView {
    @Override
    public String formatTaskFilter(ITaskFilter taskFilter) {
        StringBuilder sb = new StringBuilder("Akutalní filtrace:\n");
        sb.append("Fulltext search: ");
        sb.append(taskFilter.getSearchKeyword());
        sb.append("\n");
        sb.append("Seřazení: ");
        sb.append(taskFilter.getOrder());
        sb.append("\n");
        sb.append("Podle tagů: ");
        sb.append(Arrays.stream(taskFilter.getByTags()).map(ITag::getTitle).collect(Collectors.joining(", ")));
        if (taskFilter.getByCategory() != null) {
            sb.append("\n");
            sb.append("Podle kategorie: ");
            sb.append(taskFilter.getByCategory().getTitle());
        }
        if (taskFilter.getTaskDoneStatus() == TaskDoneStatus.IS_DONE) {
            sb.append("\n");
            sb.append("Zobrazují se pouze hotové úlohy");
        } else if(taskFilter.getTaskDoneStatus() == TaskDoneStatus.IS_NOT_DONE) {
            sb.append("\n");
            sb.append("Zobrazují se pouze nehotové úlohy");
        }
        return sb.toString();
    }
}
