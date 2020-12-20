package com.des.client.entity.paper;

import com.des.client.entity.base.BaseEntity;
import com.des.client.entity.question.Question;

import java.util.LinkedList;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/15 19:43
 * 试卷属性实体类
 */
public class Paper extends BaseEntity {

    private static final long serialVersionUID = -102700627461469183L;

    private String id;
    private String title;
    private String remark;
    private String type;
    private String start;
    private String end;
    private String code;
    private String owner;
    private String imgUrl;
    private LinkedList<Question> questionList;
    private Invigilates invigilates;
    private String status;
    private String personal;
    private String level;
    private String del;
    private String time;

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LinkedList<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(LinkedList<Question> questionList) {
        this.questionList = questionList;
    }

    public Invigilates getInvigilates() {
        return invigilates;
    }

    public void setInvigilates(Invigilates invigilates) {
        this.invigilates = invigilates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", code='" + code + '\'' +
                ", owner='" + owner + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", questionList=" + questionList +
                ", invigilates=" + invigilates +
                ", status='" + status + '\'' +
                ", personal='" + personal + '\'' +
                ", level='" + level + '\'' +
                ", del='" + del + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
