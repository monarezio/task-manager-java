package cz.ucl.logic.data.dao;

import cz.ucl.logic.app.entities.definition.Color;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "abc")
public final class TagDAO {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToOne
    private UserDAO user;

    @OneToMany
    private List<TaskDAO> tasks;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public UserDAO getUser() {
        return user;
    }

    public void setUser(UserDAO user) {
        this.user = user;
    }

    public List<TaskDAO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDAO> tasks) {
        this.tasks = tasks;
    }
}
