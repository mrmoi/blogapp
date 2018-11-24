package com.example.blogApp.users;

import com.example.blogApp.auth.AuthGroup;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Size;

@Transactional
@Entity
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private long id;

    @Size(min=5, max=30, message = "username has to be between 5 and 30 characters")
    @Column(name="USERNAME", nullable = false, unique = true)
    private String username;

    @Size(min=5, max=30, message = "password has to be between 5 and 30 characters")
    @Column(name="PASSWORD")
    private String password;

    @Size(min=5, max=30, message = "first name has to be between 5 and 30 characters")
    @Column(name="FIRSTNAME")
    private String firstName;

    @Size(min=5, max=30, message = "last name has to be between 5 and 30 characters")
    @Column(name="LASTNAME")
    private String lastName;

    @Size(min=5, max=30, message = "email has to be between 5 and 30 characters")
    @Column(name="EMAIL", unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ROLE_ID")
    private AuthGroup roleId;

    User() {
        // default constructor
    }

    public User(String username, String password, String firstName, String lastName, String email, AuthGroup roleId) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roleId = roleId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AuthGroup getRoleId() {
        return roleId;
    }

    public void setRoleId(AuthGroup roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        return roleId != null && roleId.equals(((User) o).id);
    }
    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User{" +
                "roleId=" + roleId +
                '}';
    }
}