package com.des.client.entity.paper;

import com.des.client.entity.base.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/19 20:19
 */
public class PaperType extends BaseEntity {
    private static final long serialVersionUID = -400715543614340605L;

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PaperType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
