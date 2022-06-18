package com.resumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.resumer.entity.po.user.UserRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 查询用户角色
     *
     * @param userId 用户id
     * @return 角色id
     * @author yh
     * @date 2022/5/28
     */
    List<Long> queryUserRoleId(Long userId);

    /**
     * 添加角色功能
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @author yh
     * @date 2022/5/29
     */
    void saveUserRole(Long userId, Long roleId);
}
