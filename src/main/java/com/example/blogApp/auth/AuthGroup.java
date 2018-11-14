package com.example.blogApp.auth;

import com.example.blogApp.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER_ROLE")
public class AuthGroup {
    @Id
    @Column(name="ROLE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="USERNAME")
    private String username;

    @Column(name="AUTH_GROUP")
    private String authGroup;

    @OneToMany(mappedBy = "roleId",
               fetch = FetchType.EAGER,
               cascade = CascadeType.ALL)
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
}
