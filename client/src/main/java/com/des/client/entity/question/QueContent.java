package com.des.client.entity.question;

import com.des.client.entity.base.BaseEntity;

public class QueContent extends BaseEntity {

    private static final long serialVersionUID = 262481688057589415L;

    /**
     *题目内容实体类
     * */
    private String id;//编号
    private String content;//内容
    private String mark;//备注
    private String vacancy1;//空位
    private String vacancy2;
    private String vacancy3;
    private String vacancy4;
    private String vacancy5;
    private String vacancy6;
    private String vacancy7;
    private String vacancy8;
    private String vacancy9;
    private String vacancy10;
    private String img1;//题目中包含的图片链接
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String other1;//其它
    private String other2;
    private String other3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getVacancy1() {
        return vacancy1;
    }

    public void setVacancy1(String vacancy1) {
        this.vacancy1 = vacancy1;
    }

    public String getVacancy2() {
        return vacancy2;
    }

    public void setVacancy2(String vacancy2) {
        this.vacancy2 = vacancy2;
    }

    public String getVacancy3() {
        return vacancy3;
    }

    public void setVacancy3(String vacancy3) {
        this.vacancy3 = vacancy3;
    }

    public String getVacancy4() {
        return vacancy4;
    }

    public void setVacancy4(String vacancy4) {
        this.vacancy4 = vacancy4;
    }

    public String getVacancy5() {
        return vacancy5;
    }

    public void setVacancy5(String vacancy5) {
        this.vacancy5 = vacancy5;
    }

    public String getVacancy6() {
        return vacancy6;
    }

    public void setVacancy6(String vacancy6) {
        this.vacancy6 = vacancy6;
    }

    public String getVacancy7() {
        return vacancy7;
    }

    public void setVacancy7(String vacancy7) {
        this.vacancy7 = vacancy7;
    }

    public String getVacancy8() {
        return vacancy8;
    }

    public void setVacancy8(String vacancy8) {
        this.vacancy8 = vacancy8;
    }

    public String getVacancy9() {
        return vacancy9;
    }

    public void setVacancy9(String vacancy9) {
        this.vacancy9 = vacancy9;
    }

    public String getVacancy10() {
        return vacancy10;
    }

    public void setVacancy10(String vacancy10) {
        this.vacancy10 = vacancy10;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", mark='" + mark + '\'' +
                ", vacancy1='" + vacancy1 + '\'' +
                ", vacancy2='" + vacancy2 + '\'' +
                ", vacancy3='" + vacancy3 + '\'' +
                ", vacancy4='" + vacancy4 + '\'' +
                ", vacancy5='" + vacancy5 + '\'' +
                ", vacancy6='" + vacancy6 + '\'' +
                ", vacancy7='" + vacancy7 + '\'' +
                ", vacancy8='" + vacancy8 + '\'' +
                ", vacancy9='" + vacancy9 + '\'' +
                ", vacancy10='" + vacancy10 + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", img3='" + img3 + '\'' +
                ", img4='" + img4 + '\'' +
                ", img5='" + img5 + '\'' +
                ", other1='" + other1 + '\'' +
                ", other2='" + other2 + '\'' +
                ", other3='" + other3 + '\'' +
                '}';
    }
}
