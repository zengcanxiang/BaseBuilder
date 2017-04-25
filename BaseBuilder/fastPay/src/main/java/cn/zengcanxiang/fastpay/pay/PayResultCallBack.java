package cn.zengcanxiang.fastpay.pay;

/**
 * 支付结果回调
 * <br/>作者：zengcanxiang<br/>
 * 时间：2017/3/20
 */
public interface PayResultCallBack {
    /**
     * 支付成功
     */
    void onSuccess();

    /**
     * 支付失败
     *
     * @param error_code 错误编码
     */
    void onError(int error_code);

    /**
     * 支付取消
     */
    void onCancel();

    /**
     * 支付结束
     */
    void onFinish();
}
