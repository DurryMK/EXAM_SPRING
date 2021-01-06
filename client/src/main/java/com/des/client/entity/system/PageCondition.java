package com.des.client.entity.system;

import com.des.client.entity.base.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/26 10:38
 */
public class PageCondition extends BaseEntity {
    /**
     * 查询页面信息的条件
     */
    private static final long serialVersionUID = 5385307891716261931L;
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
