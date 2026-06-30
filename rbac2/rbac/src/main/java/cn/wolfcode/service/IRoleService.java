package cn.wolfcode.service;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Role;
import cn.wolfcode.domain.RoleQuery;
import com.github.pagehelper.PageInfo;

public interface IRoleService {
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Long id);
    Role getRole(Long id);
    PageInfo<Role> selectAllRole(QueryObjet qo);
    PageInfo<Role> selectRoleByQuery(RoleQuery query);
    void saveOrUpdate(Role role);
}