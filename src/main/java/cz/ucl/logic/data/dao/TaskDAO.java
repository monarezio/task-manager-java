package cz.ucl.logic.data.dao;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public final class TaskDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String note;
    private LocalDate created = LocalDate.now();
    private LocalDate updated;
    private boolean isDone;

    @ManyToOne
    private UserDAO user;

    @ManyToOne
    private CategoryDAO category;

    @ManyToMany
    private List<TagDAO> tags;

}
