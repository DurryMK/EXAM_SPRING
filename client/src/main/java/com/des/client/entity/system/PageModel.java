package com.des.client.entity.system;

import com.des.client.entity.base.BaseEntity;

import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2021/1/4 16:10
 */
public class PageModel extends BaseEntity {
    private static final long serialVersionUID = 185171668694754101L;

    private Map data;
    private PageCondition condition;

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public PageCondition getCondition() {
        return condition;
    }

    public void setCondition(PageCondition condition) {
        condition.setOwner("");
        this.condition = condition;
    }


    @Override
    public String toString() {
        return "PageModel{" +
                "data=" + data +
                ", condition=" + condition +
                '}';
    }
}
