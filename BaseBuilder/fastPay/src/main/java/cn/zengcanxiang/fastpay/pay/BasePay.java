package cn.zengcanxiang.fastpay.pay;


import cn.zengcanxiang.baseBuilder.pay.PayOptions;

/**
 * 支付工具类总类
 * <br/>作者：zengcanxiang<br/>
 * 时间：2017/3/10
 */
@SuppressWarnings("all")
public abstract class BasePay<O extends PayOptions, PC extends PayResultCallBack> {

    protected final String TAG = "fastPay-" + getClass().getSimpleName();
    /**
     * 支付失败
     */
    public static final int ERROR_CODE_PAY = 0x001;

    /**
     * 支付参数错误
     */
    public static final int ERROR_CODE_PARAM = 0x002;

    public O mOption;

    public BasePay(O option) {
        this.mOption = option;
    }

    /**
     * 支付
     */
    public abstract void doPay(PC callback);

}
