package id.dicka.oauth2.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User extends BaseEntityID implements UserDetails {

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ENABLED")
    private boolean enabled;

    @Column(name = "ACCOUNT_EXPIRED")
    private boolean accountExpired;

    @Column(name = "CREDENTIALS_EXPIRED")
    private boolean credentialsExpired;

    @Column(name = "ACCOUNT_LOCKED")
    private boolean accountLocked;

    @Column(name = "LOCK_TIME")
    private Date lockTime;

    @Column(name = "FAILED_ATTEMPS")
    private int failedAttemps;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ROLE_USER", joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    },inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    })
    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission -> {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return ! accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ! accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return ! credentialsExpired;
    }
}
