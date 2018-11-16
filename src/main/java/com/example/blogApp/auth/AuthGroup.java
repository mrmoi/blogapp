package com.example.blogApp.auth;

import com.example.blogApp.users.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER_ROLE")
public class AuthGroup {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

/*    @Id
    @Column(name="USER_ROLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private AuthGroup userRoleId;*/

    @Column(name="USERNAME")
    private String username;

    @Column(name="AUTH_GROUP")
    private String authGroup;

    @OneToMany(mappedBy = "roleId",
               fetch = FetchType.EAGER,
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    private List<User> users = new ArrayList<>();

/*    public AuthGroup(String username, String authGroup, List<User> users) {
        this.username = username;
        this.authGroup = authGroup;
        this.users = users;
    }*/

    public AuthGroup() {

    }

    public AuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }

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

/*    public AuthGroup getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(AuthGroup userRoleId) {
        this.userRoleId = userRoleId;
    }*/

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addRole(User user) {
        users.add(user);
        user.setRoleId(this);
    }

/*    public void removeComment(PostComment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }*/
}
