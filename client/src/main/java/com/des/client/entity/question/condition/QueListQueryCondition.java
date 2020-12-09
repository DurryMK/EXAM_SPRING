package com.des.client.entity.question.condition;

import java.util.Arrays;

/**
 * 查询题库列表的查询条件
 */
public class QueListQueryCondition {
    private String[] types;
    private int start;//起始下标
    private int size;//查询数量
    private String[] levels;
    private String[] times;
    private String owner;
    private String key;

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public void setLimit(int start ,int size) {
        this.start = start;
        this.size = size;
    }
    public void setLimit(int flag) {
        this.start = 0;
        this.size = 0;
    }
    public String[] getLevels() {
        return levels;
    }

    public void setLevels(String[] levels) {
        this.levels = levels;
    }

    public String[] getTimes() {
        return times;
    }

    public void setTimes(String[] times) {
        this.times = times;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "QueListQueryCondition{" +
                "types=" + Arrays.toString(types) +
                ", start=" + start +
                ", size=" + size +
                ", levels=" + Arrays.toString(levels) +
                ", times=" + Arrays.toString(times) +
                ", owner='" + owner + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public void setKey(String key) {
        this.key = key;
    }
}
