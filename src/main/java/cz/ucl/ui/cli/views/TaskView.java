package cz.ucl.ui.cli.views;

import cz.ucl.logic.app.entities.definition.ITag;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.ui.definition.views.ITaskView;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TaskView implements ITaskView {

    private final static DateTimeFormatter czechDateTimeFormat = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");

    @Override
    public String formatTaskList(ITask[] taskList) {
        return null;
    }

    @Override
    public String formatTask(ITask task) {
        StringBuilder sb = new StringBuilder("Detail tagu:\n");
        sb.append("ID: ");
        sb.append(task.getId());
        sb.append("\n");
        sb.append("Název: ");
        sb.append(task.getTitle());
        sb.append("\n");
        sb.append("Datum vytvořeno: ");
        sb.append(task.getCreatedAt().format(czechDateTimeFormat));
        sb.append("\n");
        sb.append("Datum upraveno: ");
        sb.append(task.getUpdatedAt().format(czechDateTimeFormat));
        sb.append("\n");
        sb.append("Kategorie: ");
        if(task.getCategory() != null)
            sb.append(task.getCategory().getTitle());
        else
            sb.append("Uncategorized");
        sb.append("\n");
        sb.append("Tags: ");
        sb.append(
                Arrays.stream(task.getTags())
                        .map(ITag::getTitle)
                        .collect(Collectors.joining(", "))
        );

        return sb.toString();
    }
}
