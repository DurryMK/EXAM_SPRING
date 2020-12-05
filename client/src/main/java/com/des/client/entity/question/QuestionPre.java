package com.des.client.entity.question;

import com.des.client.entity.base.BaseEntity;

public class QuestionPre extends BaseEntity {
    private static final long serialVersionUID = 1396499155984111467L;
    /**
     * 题目预览实体类
     */
    private String id;
    private String title;//题目标题
    private String contentId;//内容Id
    private String origin;//原题链接
    private String type;//题目类型
    private String level;//难度
    private String site;//来源网站
    private String from;//题目出处

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "QuestionPre{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contentId='" + contentId + '\'' +
                ", origin='" + origin + '\'' +
                ", type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", site='" + site + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}