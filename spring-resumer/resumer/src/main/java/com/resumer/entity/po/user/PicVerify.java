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
 * 图片验证码
 * </p>
 *
 * @author yh
 * @since 2022-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PicVerify extends Model<PicVerify> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 删除标识：0、否 1、是
     */
    private Boolean deleted;

    /**
     * 验证码-明码
     */
    private String verifyCode;

    /**
     * 加密后的文件名称
     */
    private String verifyFileName;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
