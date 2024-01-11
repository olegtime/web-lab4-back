package org.example.untitled2.beans;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.Objects;

@Setter
@Entity
@Table(name = "lab4users", schema = "s368311")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;

    public User() {
        super();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User bean)) return false;
        return getId() == bean.getId() &&
                Objects.equals(getUsername(), bean.getUsername()) &&
                Objects.equals(getPassword(), bean.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                '}';
    }

}