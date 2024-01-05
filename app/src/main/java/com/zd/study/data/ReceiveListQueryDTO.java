package com.zd.study.data;

import android.util.Log;

public class ReceiveListQueryDTO {

    /**
     * 关键字(订单中心编号)
     */
    private String orderCode;

    /**
     * 车架号 vin码
     */
    private String Vin;

    /**
     * 销售渠道编码
     */
    private String saleChannelCode;

    /**
     * 订单类型编码
     */
    private String orderTypeCode;

    /**
     * 款项编码
     */
    private String itemCode;

    /**
     * 匹配状态
     */
    private String matchStatus;

    /**
     * 实收开始日期
     */
    private String startTradeDate;

    /**
     * 实收结束日期
     */
    private String endTradeDate;

    /**
     * 测试数据
     */
    private Boolean isTest;

    /**
     * 产品线
     */
    private String productLineCode;
    /**
     * 是否 存量数据
     */
    private Boolean isAll;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 每页条数
     */
    private Integer pageSize;


    private ReceiveListQueryDTO(Builder builder) {
        this.orderCode = builder.orderCode;
        this.Vin = builder.Vin;
        this.saleChannelCode = builder.saleChannelCode;
        this.orderTypeCode = builder.orderTypeCode;
        this.itemCode = builder.itemCode;
        this.matchStatus = builder.matchStatus;
        this.startTradeDate = builder.startTradeDate;
        this.endTradeDate = builder.endTradeDate;
        this.isTest = builder.isTest;
        this.productLineCode = builder.productLineCode;
        this.isAll = builder.isAll;
        this.page = builder.page;
        this.pageSize = builder.pageSize;
    }


    public  static class Builder {

        private String orderCode;

        public Builder orderCode(String orderCode) {
            this.orderCode = orderCode;
            return this;
        }

        private String Vin;

        public Builder Vin(String vin) {
            this.Vin = Vin;
            return this;
        }

        private String saleChannelCode;

        public Builder saleChannelCode(String saleChannelCode) {
            this.saleChannelCode = saleChannelCode;
            return this;
        }

        private String orderTypeCode;

        public Builder orderTypeCode(String orderTypeCode) {
            this.orderTypeCode = orderTypeCode;
            return this;
        }

        private String itemCode;

        public Builder itemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        private String matchStatus;

        public Builder matchStatus(String matchStatus) {
            this.matchStatus = matchStatus;
            return this;
        }

        private String startTradeDate;

        public Builder startTradeDate(String startTradeDate) {
            this.startTradeDate = startTradeDate;
            return this;
        }

        private String endTradeDate;

        public Builder endTradeDate(String endTradeDate) {
            this.endTradeDate = endTradeDate;
            return this;
        }


        private Boolean isTest;

        public Builder isTest(Boolean isTest) {
            this.isTest = isTest;
            return this;
        }

        private String productLineCode;

        public Builder productLineCode(String productLineCode) {
            this.productLineCode = productLineCode;
            return this;
        }

        private Boolean isAll;

        public Builder isAll(Boolean isAll) {
            this.isAll = isAll;
            return this;
        }

        private Integer page;


        public Builder page(Integer page) {
            this.page = page;
            return this;
        }


        private Integer pageSize;

        public Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ReceiveListQueryDTO build() {
            return new ReceiveListQueryDTO(this);
        }
    }


    public static void main(String[] args) {
        ReceiveListQueryDTO receiveListQueryDTO=new ReceiveListQueryDTO.Builder()
                .isAll(Boolean.TRUE)
                .itemCode("asdasd")
                .page(1)
                .pageSize(123)
                .build();

        String code = receiveListQueryDTO.itemCode;
        Log.d("ceshi", " code ====    ");
    }
}
