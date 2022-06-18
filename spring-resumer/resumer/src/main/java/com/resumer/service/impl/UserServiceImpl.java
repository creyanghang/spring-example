package com.resumer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.constant.LoginConst;
import com.common.entity.UserLoginInfo;
import com.resumer.entity.po.user.Menu;
import com.resumer.entity.po.user.Role;
import com.resumer.entity.po.user.Users;
import com.resumer.entity.req.user.UserPwReq;
import com.resumer.entity.req.user.UserReq;
import com.resumer.enums.MenuTypeEnum;
import com.resumer.enums.UserSexEnum;
import com.common.exception.RemoteException;
import com.resumer.mapper.user.UsersMapper;
import com.common.result.enums.LoginInCodeEnum;
import com.resumer.service.MenuService;
import com.resumer.service.RoleService;
import com.resumer.service.UserRoleService;
import com.resumer.service.UserService;
import com.common.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UsersMapper, Users> implements UserService {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;


    /**
     * 用户密码登录
     *
     * @param req 参数
     * @return UserLoginInfo
     * @author yh
     * @date 2022/5/28
     */
    @Override
    public UserLoginInfo userLogin(UserPwReq req) {
        return userLogin(null, req.getUserName(), req.getPassWord(), null, null);
    }

    /**
     * 获取用户信息
     *
     * @param userId   用户id
     * @param userName 用户名
     * @param passWord 密码
     * @param wechatId 微信号
     * @param email    邮箱
     * @return UserLoginInfo
     * @author yh
     * @date 2022/5/28
     */
    @Override
    public UserLoginInfo userLogin(Long userId, String userName, String passWord, String wechatId, String email) {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(EmptyUtil.isNotEmpty(userId), Users::getUserId, userId)
                .eq(EmptyUtil.isNotEmpty(userName), Users::getUserName, userName)
                .eq(EmptyUtil.isNotEmpty(passWord), Users::getPassword, passWord)
                .eq(EmptyUtil.isNotEmpty(wechatId), Users::getWechatId, wechatId)
                .eq(EmptyUtil.isNotEmpty(email), Users::getEmail, email)
                .select(Users::getUserId, Users::getUserName, Users::getNickName, Users::getHeadPhoto, Users::getSex);
        Users users = baseMapper.selectOne(wrapper);
        if (EmptyUtil.isEmpty(users)) {
            // 用户不存在
            throw new RemoteException(LoginInCodeEnum.USER_NO_FOUND);
        }
        UserLoginInfo userLoginInfo = BeanMapperUtils.map(users, UserLoginInfo.class);
        userLoginInfo.setGender(UserSexEnum.getByCode(users.getSex()));

        List<Role> roles = roleService.queryRoleByUserId(users.getUserId());

        if (EmptyUtil.isNotEmpty(roles)) {
            Role role = roles.get(0);
            userLoginInfo.setRoleId(role.getRoleId());
            userLoginInfo.setRoleCode(role.getRoleKey());
            userLoginInfo.setRoleName(role.getName());
        }
        return userLoginInfo;
    }


    /**
     * 处理用户登录缓存
     *
     * @param userLoginInfo 用户信息
     * @author yh
     * @date 2022/5/28
     */
    @Override
    public void disposeCache(UserLoginInfo userLoginInfo, HttpServletRequest servletRequest) {
        Long userId = userLoginInfo.getUserId();
        String beforeToken = redisCache.getValue(LoginConst.LOGIN_USER_TOKEN, userId.toString());
        if (EmptyUtil.isNotEmpty(beforeToken)) {
            // 如果存在先删除
            redisCache.removeValue(LoginConst.LOGIN_USER_TOKEN, userId.toString());
            redisCache.removeValue(LoginConst.USER_LOGIN_REDIS_KEY, beforeToken);
            redisCache.removeValue(LoginConst.USER_ROLE_AUTH_KEY, beforeToken);
        }

        String token = JwtUtil.createJWT(userId.toString());
        userLoginInfo.setToken(token);

        // 缓存用户token
        redisCache.set(LoginConst.LOGIN_USER_TOKEN, userId.toString(), token, LoginConst.USER_TOKEN_HOLD_MINUTE, TimeUnit.MINUTES);
        // 缓存用户信息
        userLoginInfo.setIp(LoginUtil.getIp(servletRequest));
        redisCache.set(LoginConst.USER_LOGIN_REDIS_KEY, token, userLoginInfo, LoginConst.USER_TOKEN_HOLD_MINUTE, TimeUnit.MINUTES);

        // 获取路由权限缓存
        List<Menu> menus = menuService.queryMenuByUserId(userId, MenuTypeEnum.MENU_AUTH.getCode());
        Set<String> menuPathList = menus.stream().map(Menu::getPath).collect(Collectors.toSet());
        redisCache.set(LoginConst.USER_ROLE_AUTH_KEY, token, menuPathList, LoginConst.USER_TOKEN_HOLD_MINUTE, TimeUnit.MINUTES);
    }

    /**
     * 注册用户
     *
     * @param req 参数
     * @author yh
     * @date 2022/5/29
     */
    @Override
    public void addUser(UserReq req) {
        Users users = BeanMapperUtils.map(req, Users.class);
        baseMapper.insert(users);
        if (EmptyUtil.isNotEmpty(req.getRoleId())) {
            // 添加用户角色
            userRoleService.saveUserRole(users.getUserId(), req.getRoleId());
        }
    }
}
