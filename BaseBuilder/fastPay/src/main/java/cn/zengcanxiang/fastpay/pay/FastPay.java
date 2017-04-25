package cn.zengcanxiang.fastpay.pay;


public interface FastPay<PC extends PayResultCallBack> {
    void doPay(PC callback);
}
