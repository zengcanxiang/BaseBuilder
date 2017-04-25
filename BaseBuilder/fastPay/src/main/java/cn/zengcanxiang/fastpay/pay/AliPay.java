package cn.zengcanxiang.fastpay.pay;

import android.os.Handler;
import android.text.TextUtils;

import com.alipay.sdk.app.EnvUtils;
import com.orhanobut.logger.Logger;

import java.util.Map;

import cn.zengcanxiang.fastpay.option.AliOptions;

/**
 * 支付宝支付，为支付宝2.0支付版本
 * <br/>作者：zengcanxiang<br/>
 * 时间：2017/3/10
 */
@SuppressWarnings("all")
public class AliPay extends BasePay<AliOptions, AliPay.AliPayResultCallBack> {

    /**
     * 网络连接出错
     */
    public static final int ERROR_NETWORK = 0x003;

    /**
     * 支付宝返回参数为空
     */
    public static final int ERROR_CODE_RESULT = 0x004;


    public AliPay(AliOptions option) {
        super(option);
    }

    @Override
    public void doPay(final AliPayResultCallBack callBack) {
        final Handler handler = new Handler();
        if (mOption.isSandbox) {
            Logger.t(TAG).d("支付宝支付开启沙箱");
            EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mOption.isDebug) {
                    Logger.t(TAG).d("支付宝支付请求参数：" + mOption.mParams);
                }
                final Map<String, String> pay_result = mOption.getPayTask().payV2(mOption.mParams, false);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack == null) {
                            throw new IllegalArgumentException("支付回调为空");
                        }
                        if (pay_result == null) {
                            callBack.onError(ERROR_CODE_RESULT);
                            return;
                        }
                        if (mOption.isDebug) {
                            Logger.t(TAG).d("支付宝支付请求结果：" + pay_result);
                        }
                        String resultStatus = pay_result.get("resultStatus");
                        if (TextUtils.equals(resultStatus, "9000")) {
                            //支付成功
                            callBack.onSuccess();
                        } else if (TextUtils.equals(resultStatus, "8000")) {
                            //支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            callBack.onDealing();
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            //支付取消
                            callBack.onCancel();
                        } else if (TextUtils.equals(resultStatus, "6002")) {
                            //网络连接出错
                            callBack.onError(ERROR_NETWORK);
                        } else if (TextUtils.equals(resultStatus, "4000")) {
                            //支付错误
                            callBack.onError(ERROR_CODE_PAY);
                        }
                        callBack.onFinish();
                    }
                });
            }
        }).start();
    }

    public interface AliPayResultCallBack extends PayResultCallBack {
        /**
         * 正在处理中
         */
        void onDealing();
    }
}
