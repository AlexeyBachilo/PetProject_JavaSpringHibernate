package com.petproject.service;

import com.petproject.entity.Role;
import com.petproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public void addRole(Role role){
        roleRepository.save(role);
    }

    public Long nextId(){
        List<Role> roles = roleRepository.findAll();
        int count = roles.size();
        Long id = roles.get(count-1).getRoleId();
        return id+1;
    }
}
