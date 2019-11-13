package cz.ucl.logic.data.dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "abc")
public final class UserDAO {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CategoryDAO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDAO> categories) {
        this.categories = categories;
    }

    public List<TagDAO> getTags() {
        return tags;
    }

    public void setTags(List<TagDAO> tags) {
        this.tags = tags;
    }

    public List<TaskDAO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDAO> tasks) {
        this.tasks = tasks;
    }

}
