package com.zd.study.java.bean;

public class Goods {
    public int values;//物品的价值
    public int weight;//物品的重要程度
    public int attachmentId;//附件id
    public int attachmentOneId;
    public int attachmentTwoId;

    public Goods(int values, int weight, int attachmentId) {
        this.values = values;
        this.weight = weight;
        this.attachmentId = attachmentId;
    }

    public void setAttachmentOne(int attachmentId) {
        this.attachmentOneId = attachmentId;
    }

    public void setAttachmentTwo(int attachmentId) {
        this.attachmentTwoId = attachmentId;
    }
}
