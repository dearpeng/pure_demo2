package com.alimama.web.modelVOs;

import com.alimama.api.model.Employer;
import com.alimama.api.model.Role;

import java.util.Set;

/**
 * Created by PengWX on 2020/4/27.
 */
public class EmployerVO extends Employer {

    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
