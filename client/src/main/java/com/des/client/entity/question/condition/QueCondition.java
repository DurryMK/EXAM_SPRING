package com.des.client.entity.question.condition;

import com.des.client.entity.system.PageCondition;

import java.util.Arrays;

/**
 * @author durry
 * @version 1.0
 * @date 2021/01/06 17:39
 */
public class QueCondition extends PageCondition {
    private static final long serialVersionUID = 8564975191083394153L;
    public static final String SYSTEM_OWNER = "0";//系统题库的拥有者
    /**
     * 查询题库列表的查询条件
     */
    private int currentPage;//当前页码
    private int start;//起始下标
    private int pageSize;//查询数量
    private String key;//关键字
    private int total;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "QueCondition{" +
                "currentPage=" + currentPage +
                ", start=" + start +
                ", pageSize=" + pageSize +
                ", key='" + key + '\'' +
                ", total=" + total +
                '}';
    }
}
