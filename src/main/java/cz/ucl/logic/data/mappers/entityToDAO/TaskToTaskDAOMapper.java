package cz.ucl.logic.data.mappers.entityToDAO;

import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.dao.TaskDAO;
import cz.ucl.logic.data.mappers.definitions.entityToDAO.ITaskToTaskDAOMapper;

final public class TaskToTaskDAOMapper implements ITaskToTaskDAOMapper {

    public TaskToTaskDAOMapper() {}

    @Override
    public TaskDAO mapOrNull(ITask v) {
        try {
            throw new Exception(""); //TODO: Implement
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
