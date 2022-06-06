package com.resumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.resumer.entity.po.user.RoleMenu;
import com.resumer.entity.req.user.RoleMenuReq;
import com.resumer.mapper.user.RoleMenuMapper;
import com.resumer.service.RoleMenuService;
import com.resumer.util.BeanMapperUtils;
import com.resumer.util.EmptyUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    /**
     * 根据角色查询用户权限
     *
     * @param roleId 角色id
     * @return 菜单id
     * @author yh
     * @date 2022/5/28
     */
    @Override
    public List<Long> queryMenuId(List<Long> roleId) {
        if (EmptyUtil.isEmpty(roleId)) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(RoleMenu::getRoleId, roleId);
        List<RoleMenu> roleMenus = baseMapper.selectList(wrapper);
        return roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }

    /**
     * 添加菜单
     *
     * @param req 参数
     * @return Result
     * @author yh
     * @date 2022/5/29
     */
    @Override
    public void saveRoleMenu(RoleMenuReq req) {
        RoleMenu map = BeanMapperUtils.map(req, RoleMenu.class);
        baseMapper.insert(map);
    }
}
