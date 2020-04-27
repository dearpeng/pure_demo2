package com.alimama.api.modelVOs;

import com.alimama.api.model.Employer;
import com.alimama.api.model.Permission;
import com.alimama.api.model.Role;

import java.util.Set;

/**
 * Created by PengWX on 2020/4/27.
 */
public class EmployerVO extends Employer {

    private Set<Role> roles;

    private Set<Permission> permissions;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
