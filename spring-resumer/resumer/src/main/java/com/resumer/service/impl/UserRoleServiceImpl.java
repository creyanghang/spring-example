package com.resumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.resumer.entity.po.user.UserRole;
import com.resumer.mapper.user.UserRoleMapper;
import com.resumer.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return 角色id
     * @author yh
     * @date 2022/5/28
     */
    @Override
    public List<Long> queryUserRoleId(Long userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = baseMapper.selectList(wrapper);
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    /**
     * 添加角色功能
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @author yh
     * @date 2022/5/29
     */
    @Override
    public void saveUserRole(Long userId, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        baseMapper.insert(userRole);
    }
}
