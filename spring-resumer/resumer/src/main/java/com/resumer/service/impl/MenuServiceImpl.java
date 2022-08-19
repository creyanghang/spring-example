package com.resumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.resumer.entity.po.user.Menu;
import com.resumer.entity.req.user.MenuReq;
import com.resumer.mapper.user.MenuMapper;
import com.resumer.service.MenuService;
import com.resumer.service.RoleMenuService;
import com.resumer.service.UserRoleService;
import com.common.util.BeanMapperUtils;
import com.common.util.EmptyUtil;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Service
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final UserRoleService userRoleService;
    private final RoleMenuService roleMenuService;

    /**
     * 根据用户id查询权限
     *
     * @param userId   用户id
     * @param menuType 权限类型 auth:路由权限，web:web菜单
     * @return 权限
     * @author yh
     * @date 2022/5/28
     */

    @Override
    public List<Menu> queryMenuByUserId(Long userId, String menuType) {
        List<Long> roleIdList = userRoleService.queryUserRoleId(userId);
        List<Long> menuIdList = roleMenuService.queryMenuId(roleIdList);
        if (EmptyUtil.isEmpty(menuIdList)) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Menu::getMenuId, menuIdList)
                .eq(Menu::getMenuType, menuType)
                // 菜单状态（0正常 1停用）
                .eq(Menu::getStatus, 0)
                .orderByAsc(Menu::getSort);
       return baseMapper.selectList(wrapper);
    }

    /**
     * 添加系统菜单
     * @param req 参数
     * @author yh
     * @date 2022/5/29
     */
    @Override
    public void saveMenu(MenuReq req){
        Menu menu = BeanMapperUtils.map(req, Menu.class);
        baseMapper.insert(menu);
    }
}
