package cz.ucl.logic.data.dao;

import javax.persistence.*;
import java.util.List;

@Entity
public final class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @OneToMany
    private List<CategoryDAO> categories;

    @ManyToMany
    private List<TagDAO> tags;

    @OneToMany
    private List<TaskDAO> tasks;

}
