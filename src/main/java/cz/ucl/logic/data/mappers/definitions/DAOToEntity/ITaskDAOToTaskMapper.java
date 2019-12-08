package cz.ucl.logic.data.mappers.definitions.DAOToEntity;

import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.dao.TaskDAO;
import cz.ucl.logic.data.mappers.definitions.IDeepMapper;
import cz.ucl.logic.data.mappers.definitions.IMapper;

public interface ITaskDAOToTaskMapper extends IMapper<TaskDAO, ITask>, IDeepMapper<TaskDAO, ITask> {
}
