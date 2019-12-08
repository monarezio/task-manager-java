package cz.ucl;

import cz.ucl.logic.app.entities.definition.ICategory;
import cz.ucl.logic.app.entities.definition.IUser;
import cz.ucl.logic.app.entities.definition.task.ITask;
import cz.ucl.logic.data.hibernate.HibernateSessionFactory;
import cz.ucl.logic.data.managers.CategoryManager;
import cz.ucl.logic.data.mappers.DAOToEntity.CategoryDAOToCategoryMapper;
import cz.ucl.logic.data.mappers.DAOToEntity.ColorDAOToColorMapper;
import cz.ucl.logic.data.mappers.entityToDAO.ColorToColorDAOMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Playground {

    public static void main(String[] args) {

    }

}
