package com.resumer.entity.po.user;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;


    @Override
    public Serializable pkVal() {
        return this.userId;
    }

}
