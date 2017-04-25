package cn.zengcanxiang.fastpay.helper.Wx;

/**
 * 微信下单实体类
 */
public class OrderParams {
    String outTradeNo;
    String body;
    String totalMoney;
    String tradeType;
    String notifyUrl;
    String spbillCreateIp;
    String deviceInfo;
    String signType;
    String detail;
    String attach;
    String feeType;
    String timeStart;
    String timeExpire;
    String goodsType;
    String limitPay;

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public void setLimitPay(String limitPay) {
        this.limitPay = limitPay;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getBody() {
        return body;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public String getTradeType() {
        return tradeType;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public String getSignType() {
        return signType;
    }

    public String getDetail() {
        return detail;
    }

    public String getAttach() {
        return attach;
    }

    public String getFeeType() {
        return feeType;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public String getLimitPay() {
        return limitPay;
    }
}
