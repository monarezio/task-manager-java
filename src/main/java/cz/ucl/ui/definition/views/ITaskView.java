package cz.ucl.ui.definition.views;

import cz.ucl.logic.app.entities.definition.ITask;

/** Views are used only for formatting purposes. They are stateless. */
public interface ITaskView {
    String formatTaskList(ITask[] taskList);
    String formatTask(ITask task);
}
