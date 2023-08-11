package com.petproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

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

    @Override
    public String getAuthority() {
        return getName();
    }
}
