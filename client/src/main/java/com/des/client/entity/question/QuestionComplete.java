package com.des.client.entity.question;

import com.des.client.entity.base.BaseEntity;

public class QuestionComplete extends BaseEntity {

    private static final long serialVersionUID = 8676303584279244634L;

    /**
     * 题目完整信息实体类
     */
    private String title;//题目标题
    private String content;//内容
    private String origin;//原题链接
    private String type;//题目类型
    private String level;//难度
    private String site;//来源网站
    private String from;//题目出处
    private String time;//创建时间

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "QuestionComplete{" +
                "title='" + title + '\'' +
            ", origin='" + origin + '\'' +
            ", type='" + type + '\'' +
            ", level='" + level + '\'' +
            ", site='" + site + '\'' +
            ", from='" + from + '\'' +
            ", time='" + time + '\'' +
            '}';
}

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

}
