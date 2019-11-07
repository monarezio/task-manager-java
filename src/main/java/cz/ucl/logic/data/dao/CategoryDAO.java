package cz.ucl.logic.data.dao;

import javax.persistence.*;
import java.util.List;

@Entity
public final class CategoryDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @Enumerated(EnumType.STRING)
    private ColorDAO color;

    @ManyToOne
    private UserDAO user;

    @OneToMany
    private List<TaskDAO> tasks;

}
