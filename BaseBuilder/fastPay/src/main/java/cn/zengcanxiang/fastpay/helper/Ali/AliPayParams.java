package cn.zengcanxiang.fastpay.helper.Ali;

import cn.zengcanxiang.baseBuilder.pay.PayParams;

/**
 * 支付宝支付参数实体类
 */
public class AliPayParams extends PayParams {
    private String outTradeNo;
    private String subject;
    private String totalMoney;
    private String timeOut;
    private String goodsType;
    private String backParams;
    private String extendParams;
    private String enablePayChannels;
    private String disablePayChannels;
    private String storeId;
    private String promoParams;

    /**
     * 设置唯一单号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 设置商品的标题/交易标题/订单标题/订单关键字等
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 设置需要支付的价格
     */
    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    /**
     * 超时时间 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     */
    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * 商品类型 0—虚拟类商品，1—实物类商品
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 回传参数
     */
    public void setBackParams(String backParams) {
        this.backParams = backParams;
    }

    /**
     * <a href="https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.x7kkCI&treeId=204&articleId=105465&docType=1#kzcs">业务扩展参数</a>
     */
    public void setExtendParams(String extendParams) {
        this.extendParams = extendParams;
    }

    /**
     * <a href="https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.x7kkCI&treeId=204&articleId=105465&docType=1#qdsm">可用渠道</a>
     */
    public void setEnablePayChannels(String enablePayChannels) {
        this.enablePayChannels = enablePayChannels;
    }

    /**
     * <a href="https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.x7kkCI&treeId=204&articleId=105465&docType=1#qdsm">禁用渠道</a>
     */
    public void setDisablePayChannels(String disablePayChannels) {
        this.disablePayChannels = disablePayChannels;
    }

    /**
     * 商户门店编号
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /**
     * 优惠参数，仅与支付宝协商后可用
     */
    public void setPromoParams(String promoParams) {
        this.promoParams = promoParams;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public String getBackParams() {
        return backParams;
    }

    public String getExtendParams() {
        return extendParams;
    }

    public String getEnablePayChannels() {
        return enablePayChannels;
    }

    public String getDisablePayChannels() {
        return disablePayChannels;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getPromoParams() {
        return promoParams;
    }
}
