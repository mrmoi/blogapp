package com.example.blogApp.auth;

import com.example.blogApp.users.User;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Entity
@Table(name="USER_ROLE")
public class AuthGroup {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=5, max=30)
    @Column(name="USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name="AUTH_GROUP")
    private String authGroup;

    @OneToMany(mappedBy = "roleId",
               fetch = FetchType.EAGER,
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        users.add(user);
        user.setRoleId(this);
    }

    public void deleteUser(User user) {
        users.remove(user);
        user.setRoleId(null);
    }


}
