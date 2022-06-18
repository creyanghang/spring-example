package com.resumer.entity.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 删除标识：0、否 1、是
     */
    private Boolean deleted;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 微信账号
     */
    private String wechatId;

    /**
     * 用户性别（0男，1女，2未知）
     */
    private Integer sex;

    /**
     * 头像
     */
    private String headPhoto;

    /**
     * 有效-valid , 无效-invalid, 锁定-locked
     */
    private String status;


    @Override
    public Serializable pkVal() {
        return this.userId;
    }

}
