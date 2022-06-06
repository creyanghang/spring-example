package com.resumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.resumer.entity.po.user.Role;
import com.resumer.entity.req.user.RoleReq;
import com.resumer.exception.RemoteException;
import com.resumer.mapper.user.RoleMapper;
import com.resumer.result.enums.UserCodeEnum;
import com.resumer.service.RoleService;
import com.resumer.service.UserRoleService;
import com.resumer.util.BeanMapperUtils;
import com.resumer.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 添加角色
     *
     * @param req 参数
     * @author yh
     * @date 2022/5/28
     */
    @Override
    public void addRole(RoleReq req) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getRoleKey, req.getRoleKey());
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new RemoteException(UserCodeEnum.ROLE_EXIST);
        }
        Role role = BeanMapperUtils.map(req, Role.class);
        baseMapper.insert(role);
    }

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return List<Role>
     * @author yh
     * @date 2022/5/29
     */
    @Override
    public List<Role> queryRoleByUserId(Long userId) {
        List<Long> roleId = userRoleService.queryUserRoleId(userId);
        if(EmptyUtil.isEmpty(roleId)){
            return Collections.emptyList();
        }
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Role::getRoleId, roleId)
                // 角色状态（0正常 1停用）
                .eq(Role::getStatus, 1);
        return baseMapper.selectList(wrapper);
    }
}
