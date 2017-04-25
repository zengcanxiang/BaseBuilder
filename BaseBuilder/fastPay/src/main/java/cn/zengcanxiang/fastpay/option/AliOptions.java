package cn.zengcanxiang.fastpay.option;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

import cn.zengcanxiang.baseBuilder.pay.PayBuilder;
import cn.zengcanxiang.baseBuilder.pay.PayOptions;

/**
 * 支付参数--支付宝
 * <br/>作者：zengcanxiang<br/>
 * 时间：2017/3/10
 */
@SuppressWarnings("all")
public class AliOptions extends PayOptions<AliOptions.AliBuilder> {

    private PayTask mPayTask;

    private AliOptions(AliBuilder builder) {
        super(builder);
        if (builder.getContext() instanceof Activity) {
            mPayTask = new PayTask((Activity) builder.getContext());
        } else {
            throw new IllegalArgumentException("上下文变量不为Activity：" + builder.getContext());
        }
    }

    public PayTask getPayTask() {
        return mPayTask;
    }

    /**
     * 支付参数构造器--支付宝
     */
    public static class AliBuilder extends PayBuilder {

        public AliBuilder() {
        }

        public AliBuilder(Activity activity, String params) {
            super(activity, params);
        }

        @Override
        public AliOptions builder() {
            return new AliOptions(this);
        }
    }

}
