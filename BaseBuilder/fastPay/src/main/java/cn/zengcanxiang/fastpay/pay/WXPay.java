package cn.zengcanxiang.fastpay.pay;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;

import cn.zengcanxiang.fastpay.option.WxPayOptions;
import cn.zengcanxiang.fastwx.WXCallbackActivity;
import cn.zengcanxiang.fastwx.WxCallback;

import static com.tencent.mm.opensdk.modelbase.BaseResp.ErrCode.ERR_COMM;
import static com.tencent.mm.opensdk.modelbase.BaseResp.ErrCode.ERR_OK;
import static com.tencent.mm.opensdk.modelbase.BaseResp.ErrCode.ERR_USER_CANCEL;


/**
 * 微信支付工具类
 * <br/>作者：zengcanxiang<br/>
 * 时间：2017/3/10
 */
@SuppressWarnings("all")
public class WXPay extends BasePay<WxPayOptions, WXPay.WXPayResultCallBack> {

    private WXPayResultCallBack mCallBack;

    private static WXPay mWXPay;

    /**
     * 未安装微信或微信版本过低
     */
    public static final int ERROR_CODE_NO_WX = 0x003;

    public static WXPay getInstance() {
        return mWXPay;
    }

    public WXPay(WxPayOptions option) {
        super(option);
        Logger.init("WXPay").methodCount(0).hideThreadInfo();
        WXCallbackActivity.addCallback(WXCallbackActivity.WX_CALLBACK_PAY, new WxCallback() {
            @Override
            public void callback(BaseResp baseResp) {
                payCallback(baseResp);
            }
        });
    }

    @Override
    public void doPay(WXPayResultCallBack callback) {
        mCallBack = callback;
        if (!check()) {
            if (mCallBack != null) {
                mCallBack.onError(ERROR_CODE_NO_WX);
            } else {
                throw new IllegalArgumentException("支付回调为空");
            }
            return;
        }

        JSONObject params;
        try {
            params = JSON.parseObject(mOption.mParams);
            //判断调起微信支付操作接口的参数不能少
            if (params == null || TextUtils.isEmpty(params.getString("appid")) || TextUtils.isEmpty(params.getString("partnerid"))
                    || TextUtils.isEmpty(params.getString("prepayid")) || TextUtils.isEmpty(params.getString("package")) ||
                    TextUtils.isEmpty(params.getString("noncestr")) || TextUtils.isEmpty(params.getString("timestamp")) ||
                    TextUtils.isEmpty(params.getString("sign"))) {
                callback.onError(ERROR_CODE_PARAM);
                return;
            }
            PayReq req = new PayReq();
            //微信开放平台审核通过的应用APPID
            req.appId = params.getString("appid");
            //微信支付分配的商户号
            req.partnerId = params.getString("partnerid");
            //微信返回的支付交易会话ID
            req.prepayId = params.getString("prepayid");
            //暂填写固定值Sign=WXPay
            req.packageValue = params.getString("package");
            //随机字符串
            req.nonceStr = params.getString("noncestr");
            //时间戳
            req.timeStamp = params.getString("timestamp");
            //签名
            req.sign = params.getString("sign");
            if (mOption.isDebug) {
                Log.i(TAG,"------------- 微信支付所有参数 -------------");
                Logger.json(JSON.toJSONString(req));
            }
            mOption.getWXApi().sendReq(req);
        } catch (Exception e) {
            if (mOption.isDebug) {
                Logger.e(e.getMessage());
                Logger.e(e, "message");
            }
            callback.onError(ERROR_CODE_PARAM);
        }
    }

    /**
     * 检测是否支持微信支付
     */
    private boolean check() {
        return mOption.getWXApi().isWXAppInstalled() && mOption.getWXApi().getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }

    /**
     * 支付回调响应
     */
    public void payCallback(BaseResp baseResp) {
        if (mCallBack == null) {
            return;
        }
        if (mOption.isDebug) {
            Log.i(TAG, "微信支付所有返回参数：");
            Logger.json(JSON.toJSONString(baseResp));
        }

        if (baseResp.errCode == ERR_OK) {   //成功
            mCallBack.onSuccess();
        } else if (baseResp.errCode == ERR_COMM) {   //错误
            mCallBack.onError(ERROR_CODE_PAY);
        } else if (baseResp.errCode == ERR_USER_CANCEL) {   //取消
            mCallBack.onCancel();
        }
        mCallBack.onFinish();
    }

    public interface WXPayResultCallBack extends PayResultCallBack {
    }

}
