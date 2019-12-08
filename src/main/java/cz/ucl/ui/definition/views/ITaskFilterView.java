package cz.ucl.ui.definition.views;

import cz.ucl.logic.app.entities.definition.task.ITaskFilter;

public interface ITaskFilterView {

    String formatTaskFilter(ITaskFilter taskFilter);

}
