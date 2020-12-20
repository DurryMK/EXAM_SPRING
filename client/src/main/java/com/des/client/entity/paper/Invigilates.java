package com.des.client.entity.paper;

import com.des.client.entity.base.BaseEntity;

/**
 * @author durry
 * @version 1.0
 * @date 2020/12/15 19:46
 * 试卷反作弊属性
 */
public class Invigilates extends BaseEntity {

    private static final long serialVersionUID = 5809995458178911858L;

    private String join;//允许参加考试的次数
    private String page;//允许切换页面的次数
    private String isCopy;//是否允许复制
    private String isPaste;//是否允许粘贴
    private String isRname;//是否仅允许实名考生参加
    private String leave;//允许离开摄像头界面的次数
    private String isCamera;//是否开启摄像头监考
    private String isDoRname;//是否考前进行人脸识别
    private String duration;//单次考试时长

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getIsCopy() {
        return isCopy;
    }

    public void setIsCopy(String isCopy) {
        this.isCopy = isCopy;
    }

    public String getIsPaste() {
        return isPaste;
    }

    public void setIsPaste(String isPaste) {
        this.isPaste = isPaste;
    }

    public String getIsRname() {
        return isRname;
    }

    public void setIsRname(String isRname) {
        this.isRname = isRname;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getIsCamera() {
        return isCamera;
    }

    public void setIsCamera(String isCamera) {
        this.isCamera = isCamera;
    }

    public String getIsDoRname() {
        return isDoRname;
    }

    public void setIsDoRname(String isDoRname) {
        this.isDoRname = isDoRname;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Invigilates{" +
                "join='" + join + '\'' +
                ", page='" + page + '\'' +
                ", isCopy='" + isCopy + '\'' +
                ", isPaste='" + isPaste + '\'' +
                ", isRname='" + isRname + '\'' +
                ", leave='" + leave + '\'' +
                ", isCamera='" + isCamera + '\'' +
                ", isDoRname='" + isDoRname + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
