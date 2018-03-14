package com.zzrh.automat.bean;

/**
 * Created by Gmrxus on 2018/3/13.
 */

public class PayCodeInfo {
    /**
     * returnCode : success
     * data : {"qrUrl":"weixin://wxpay/bizpayurl?pr=wEXmQ1q"}
     */

    private String returnCode;
    private DataBean data;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * qrUrl : weixin://wxpay/bizpayurl?pr=wEXmQ1q
         */

        private String qrUrl;

        public String getQrUrl() {
            return qrUrl;
        }

        public void setQrUrl(String qrUrl) {
            this.qrUrl = qrUrl;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "qrUrl='" + qrUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "PayCodeInfo{" +
                "returnCode='" + returnCode + '\'' +
                ", data=" + data +
                '}';
    }
}
