package com.gabrielmagalhaes.todosimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = Task.TABLE_NAME)

public class Task {
    public static final String TABLE_NAME = "task";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name= "id", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "description", length = 395, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 395)
    private String description;

    

    public Task() {
    }

    public Task(long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Task id(long id) {
        setId(id);
        return this;
    }

    public Task user(User user) {
        setUser(user);
        return this;
    }

    public Task description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id && Objects.equals(user, task.user) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, description);
    }
    //TALVEZ ALTERAR O HASHCODE E O EQUALS. CONFIRMAR.

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    
}
