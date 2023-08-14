package com.petproject.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_user_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
    @Column(name = "userid", unique = true, nullable = false)
    private Long userId;
    @Column(name = "login", nullable = false)
    private String login;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "userpoints")
    private int userPoints = 0;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Transient
    private String passwordConfirm;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks = null;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "granteduserid", referencedColumnName = "userId"),
            @JoinColumn(name = "roleid", referencedColumnName = "roleId")})
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userPoints == user.userPoints && Objects.equals(userId, user.userId) && Objects.equals(login, user.login) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(passwordConfirm, user.passwordConfirm) && Objects.equals(tasks, user.tasks) && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, login, firstName, lastName, userPoints, email, password, passwordConfirm, tasks, roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return email;
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
