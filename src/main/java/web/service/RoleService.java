package web.service;

import web.model.Role;

import java.util.Map;
import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();

    Set<Role> getRolesFromForm(Set<Role> allRoles, Map<String, String> form);
}
