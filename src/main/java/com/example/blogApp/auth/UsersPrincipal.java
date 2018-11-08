package com.example.blogApp.auth;

import com.example.blogApp.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UsersPrincipal implements UserDetails{

    private User user;
    private List<AuthGroup> authGoups;

    public UsersPrincipal(User user, List<AuthGroup> authGoups){
        super();
        this.user = user;
        this.authGoups = authGoups;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*return Collections.singleton(new SimpleGrantedAuthority("USER"));*/
        if(null==authGoups){
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        authGoups.forEach(group->{
            grantedAuthorities.add(new SimpleGrantedAuthority(group.getAuthGroup()));
        });
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
