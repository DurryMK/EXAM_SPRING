package com.des.client.entity;

import com.des.client.entity.base.BaseEntity;

import java.util.Arrays;
import java.util.List;

public class PageContainer extends BaseEntity {

    private static final long serialVersionUID = 4097888609707868099L;

    /**
     * 页面列表信息的容器
     */
    private Integer currentPage;//当前页码
    private Object infos;//数据
    private Integer pageSize;//当前的分页数
    private Integer total;//总数据量
    private Object types;//数据所包含的所有题目类型
    private Object levels;//数据所包含的所有难度等级
    private String searchKey;//获取数据的关键字
    private Object createTimes;//数据所包含的所有时间区间

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Object getInfos() {
        return infos;
    }

    public void setInfos(Object infos) {
        this.infos = infos;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getTypes() {
        return types;
    }

    public void setTypes(Object types) {
        this.types = types;
    }

    public Object getLevels() {
        return levels;
    }

    public void setLevels(Object levels) {
        this.levels = levels;
    }

    public Object getCreateTimes() {
        return createTimes;
    }

    public void setCreateTimes(Object createTimes) {
        this.createTimes = createTimes;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }


    @Override
    public String toString() {
        return "ListInPage{" +
                "currentPage=" + currentPage +
                ", infos=" + infos +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", types='" + types + '\'' +
                ", levels='" + levels + '\'' +
                ", searchKey='" + searchKey + '\'' +
                ", createTime='" + createTimes + '\'' +
                '}';
    }
}
