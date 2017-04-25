package cn.zengcanxiang.fastwx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 微信回调指定Activity
 * <br/>作者：zengcanxiang<br/>
 * 时间：2017/3/10
 */
public class WXCallbackActivity extends Activity implements IWXAPIEventHandler {

    private static HashMap<String, WxCallback> mCallback = new HashMap<>();

    public static final String WX_CALLBACK_SHARE = "share";
    public static final String WX_CALLBACK_PAY = "pay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IWXAPI iwxapi = WxChat.getIWXAPIInstance();
        if (iwxapi != null) {
            iwxapi.handleIntent(getIntent(), this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        IWXAPI iwxapi = WxChat.getIWXAPIInstance();
        if (iwxapi != null) {
            iwxapi.handleIntent(getIntent(), this);
        }
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Iterator i = mCallback.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry entry = (Map.Entry) i.next();
            WxCallback callback = (WxCallback) entry.getValue();
            callback.callback(baseResp);
        }
        finish();
    }

    public static void addCallback(String key, WxCallback callback) {
        if (WX_CALLBACK_PAY.equals(key) || WX_CALLBACK_SHARE.equals(key)) {
            mCallback.put(key, callback);
        } else {
            throw new IllegalArgumentException("注册微信回调错误.");
        }
    }

    public static void removeCallback(String key) {
        mCallback.remove(key);
    }
}