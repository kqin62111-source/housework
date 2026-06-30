package cn.wolfcode.service.impl;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Role;
import cn.wolfcode.domain.RoleQuery;
import cn.wolfcode.mapper.RoleMapper;
import cn.wolfcode.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void addRole(Role role) {
        roleMapper.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRole(Long id) {
        if (id != null) {
            roleMapper.deleteRole(id);
        }
    }

    @Override
    public Role getRole(Long id) {
        return roleMapper.getRole(id);
    }

    @Override
    public PageInfo<Role> selectAllRole(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(roleMapper.selectAllRole());
    }

    @Override
    public PageInfo<Role> selectRoleByQuery(RoleQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(roleMapper.selectRoleByQuery(query));
    }

    @Override
    public void saveOrUpdate(Role role) {
        if (role == null) return;
        if (role.getId() == null) {
            this.addRole(role);
        } else {
            this.updateRole(role);
        }
    }
}