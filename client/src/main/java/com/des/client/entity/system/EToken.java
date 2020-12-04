package com.des.client.entity.system;

import com.des.client.entity.base.BaseEntity;

public class EToken extends BaseEntity {
    private static final long serialVersionUID = -8221190324350084636L;
    private String id;
    private String name;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "EToken{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
