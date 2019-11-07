package cz.ucl.logic.data.dao;

import cz.ucl.logic.app.entities.definition.Color;

import javax.persistence.*;
import java.util.List;

@Entity
public final class TagDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne
    private UserDAO user;

    @OneToMany
    private List<TaskDAO> tasks;

}
