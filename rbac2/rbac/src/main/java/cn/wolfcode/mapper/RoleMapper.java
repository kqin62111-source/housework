package cn.wolfcode.mapper;

import cn.wolfcode.domain.Role;
import cn.wolfcode.domain.RoleQuery;

import java.util.List;

public interface RoleMapper {
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Long id);
    Role getRole(Long id);
    List<Role> selectAllRole();
    List<Role> selectRoleByQuery(RoleQuery query);
}