package cz.ucl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableValidator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Playground {

    public static void main(String[] args) {
        String s = LocalDateTime.now().minusDays(20).toString();
        System.out.println(s);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");

        System.out.println(LocalDateTime.now().minusDays(69).format(dtf));

        System.out.println(LocalDateTime.parse("27/12/2019 1:00", dtf));
    }

}
