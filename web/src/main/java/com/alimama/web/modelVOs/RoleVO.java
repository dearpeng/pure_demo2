package com.alimama.web.modelVOs;

import com.alimama.api.model.Role;

import java.security.Permissions;
import java.util.Set;

/**
 * Created by PengWX on 2020/4/27.
 */
public class RoleVO extends Role {

    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;

    public Set<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permissions> permissions) {
        this.permissions = permissions;
    }
}
