package com.des.client.entity.system;

import com.alibaba.fastjson.JSON;
import com.des.client.consts.Res;

import java.util.HashMap;
import java.util.Map;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/18 21:53
 * 请求响应结果的实体类
 */
public class Emap {
    private Map map = null;

    /**
     * 仅返回失败状态
     */
    public Map fail() {
        this.map = new HashMap<String, Object>();
        map.put(Res.RESTOKEN, Res.FAIL);
        return this.map;
    }

    /**
     * 仅返回成功状态
     */
    public Map success() {
        this.map = new HashMap<String, Object>();
        map.put(Res.RESTOKEN, Res.SUCCESS);
        return this.map;
    }

    /**
     * 返回成功状态 并传参
     */
    public Map success(Object obj) {
        this.map = new HashMap<String, Object>();
        this.map.put(Res.RESTOKEN, Res.SUCCESS);
        this.map.put(Res.RESINFO, obj);
        return this.map;
    }

    /**
     * 返回失败状态 并传参
     */
    public Map fail(Object obj) {
        this.map = new HashMap<String, Object>();
        this.map.put(Res.RESTOKEN, Res.FAIL);
        this.map.put(Res.RESINFO, obj);
        return this.map;
    }

    /**
     * 返回成功状态 传参 并转JSON格式
     */
    public Map successJ(Object obj) {
        this.map = new HashMap<String, Object>();
        this.map.put(Res.RESTOKEN, Res.SUCCESS);
        this.map.put(Res.RESINFO, JSON.toJSON(obj));
        return this.map;
    }

    /**
     * 返回失败状态 传参 并转JSON格式
     */
    public Map failJ(Object obj) {
        this.map = new HashMap<String, Object>();
        this.map.put(Res.RESTOKEN, Res.FAIL);
        this.map.put(Res.RESINFO, JSON.toJSON(obj));
        return this.map;
    }


    /**
     * 获取当前map的键
     */
    public String token() {
        return (String) this.map.get(Res.RESTOKEN);
    }

    /**
     * 获取当前map的值
     */
    public Object info() {
        return this.map.get(Res.RESINFO);
    }

    /**
     * 获取当前map的实体
     */
    public Map back() {
        return this.map;
    }
}
