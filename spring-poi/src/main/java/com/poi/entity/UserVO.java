package com.poi.entity;

/**
 * @author yh
 * @description
 * @date 2022/6/5
 */


public class UserVO {
    private String name;
    private Long id;
    private String createTime;

    public UserVO() {
    }


    public UserVO(String name, Long id, String time) {
        this.name = name;
        this.id = id;
        this.createTime = time;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
