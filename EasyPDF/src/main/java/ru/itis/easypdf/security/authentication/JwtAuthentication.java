package ru.itis.easypdf.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * created: 22-01-2022 - 16:10
 * project: EasyPDF
 *
 * @author dinar
 * @version v0.1
 */

public class JwtAuthentication implements Authentication {

    private String token;

    private boolean isAuthenticated;

    private UserDetails userDetails;

    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails != null ? userDetails.getAuthorities() : Collections.emptyList();
    }

    @Override
    public Object getCredentials() {
        return userDetails.getPassword();
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.isAuthenticated = authenticated;
    }

    @Override
    public String getName() {
        return token;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}
