package com.des.client.entity.paper.condition;

import com.des.client.entity.system.PageCondition;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/29 20:15
 */
public class PaperCondition extends PageCondition {
    private static final long serialVersionUID = 8564975191083394153L;
    /**
     * 查询试卷列表信息的条件
     */
    private int currentPage;//当前页码
    private int start;//起始下标
    private int pageSize;//查询数量
    private String key;//关键字
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    @Override
    public String toString() {
        return "PaperCondition{" +
                "currentPage=" + currentPage +
                ", start=" + start +
                ", pageSize=" + pageSize +
                ", key='" + key + '\'' +
                ", total=" + total +
                '}';
    }
}
