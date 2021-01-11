package com.des.client.entity.system;

import com.alibaba.fastjson.JSON;
import com.des.client.consts.Res;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/18 21:53
 * 请求响应结果的实体类
 */
public class Emap {

    private Map map = new HashMap();

    /**
     * 仅返回错误状态
     */
    public Map exception() {
        this.map = new HashMap<String, Object>();
        map.put(Res.RESTOKEN, Res.EXCEPTION);
        return this.map;
    }

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
     * 返回成功状态 并传单个参数
     */
    public Map success(Object obj) {
        this.map = new HashMap<String, Object>();
        this.map.put(Res.RESTOKEN, Res.SUCCESS);
        this.map.put(Res.RESINFO, obj);
        return this.map;
    }
    /**
     * 返回成功状态 并传多个参数
     */
    public Map success(String[] keys, Object... objs) {
        this.map = new HashMap<String, Object>();
        this.map.put(Res.RESTOKEN, Res.SUCCESS);
        int index = 0;
        Map map1 = new HashMap<String, Object>();
        for (String key : keys) {
            map1.put(key, objs[index]);
            index++;
        }
        this.map.put(Res.RESINFO, map1);
        return this.map;
    }

    /**
     * 返回失败状态 并传递失败信息
     */
    public Map fail(String msg) {
        this.map = new HashMap<String, Object>();
        this.map.put(Res.RESTOKEN, Res.FAIL);
        this.map.put(Res.RESINFO, msg);
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
     * 返回成功状态 传参 并转JSON格式
     */
    public Map successJ(String[] keys, Object... objs) {
        this.map = new HashMap<String, Object>();
        this.map.put(Res.RESTOKEN, Res.SUCCESS);
        int index = 0;
        Map map1 = new HashMap<String, Object>();
        for (String key : keys) {
            map1.put(key, objs[index]);
            index++;
        }
        this.map.put(Res.RESINFO, map1);

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
