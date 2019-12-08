package cz.ucl.logic.data.mappers.definitions.entityToDAO;

import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.dao.TaskDAO;
import cz.ucl.logic.data.mappers.definitions.IMapper;

public interface ITaskToTaskDAOMapper extends IMapper<ITask, TaskDAO> {
}
