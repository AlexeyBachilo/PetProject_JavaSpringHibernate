package com.petproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements Serializable, GrantedAuthority {
    @Id
    @Column(name = "roleid")
    private Long roleId;
    @Column(name = "name")
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = null;

    public Role(Long id){
        this.roleId = id;
    }

    public Role(Long id, String name){
        this.roleId = id;
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
