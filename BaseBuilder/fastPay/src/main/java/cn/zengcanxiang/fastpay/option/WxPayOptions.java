package cn.zengcanxiang.fastpay.option;

import android.app.Activity;

import com.tencent.mm.opensdk.openapi.IWXAPI;

import cn.zengcanxiang.baseBuilder.pay.PayBuilder;
import cn.zengcanxiang.baseBuilder.pay.PayOptions;
import cn.zengcanxiang.fastwx.WxChat;

/**
 * 支付参数--微信
 * <br/>作者：zengcanxiang<br/>
 * 时间：2017/3/10
 */
@SuppressWarnings("all")
public class WxPayOptions extends PayOptions<WxPayOptions.WxPayBuilder> {

    private static IWXAPI mWXApi;

    private WxPayOptions(WxPayBuilder builder) {
        super(builder);
        mWXApi = WxChat.getIWXAPIInstance(builder.getContext(), builder.getWxAppId());
    }

    public IWXAPI getWXApi() {
        return mWXApi;
    }

    /**
     * 支付参数构造器--微信
     */
    public static class WxPayBuilder extends PayBuilder {

        private String wxAppId;

        public WxPayBuilder() {

        }

        public WxPayBuilder(Activity activity, String params, String wxAppId) {
            super(activity, params);
            this.wxAppId = wxAppId;
        }

        @Override
        public WxPayOptions builder() {
            return new WxPayOptions(this);
        }

        public String getWxAppId() {
            return wxAppId;
        }

        public WxPayBuilder setWxAppId(String wxAppId) {
            this.wxAppId = wxAppId;
            return this;
        }

    }
}
