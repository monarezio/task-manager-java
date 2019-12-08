package cz.ucl.logic.data.dao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public final class TaskDAO {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String note;

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updated = LocalDateTime.now();

    @Column(nullable = true)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private boolean isDone;

    @ManyToOne
    private UserDAO user;

    @ManyToOne
    private CategoryDAO category;

    @ManyToMany
    private List<TagDAO> tags;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public UserDAO getUser() {
        return user;
    }

    public void setUser(UserDAO user) {
        this.user = user;
    }

    public CategoryDAO getCategory() {
        return category;
    }

    public void setCategory(CategoryDAO category) {
        this.category = category;
    }

    public List<TagDAO> getTags() {
        return tags;
    }

    public void setTags(List<TagDAO> tags) {
        this.tags = tags;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
