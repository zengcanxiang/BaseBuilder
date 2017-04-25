package cn.zengcanxiang.fastwx;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WxChat {
    private static IWXAPI api;

    public static IWXAPI getIWXAPIInstance(Context context, String appId) {
        if (null == api) {
            api = WXAPIFactory.createWXAPI(context, appId);
            boolean isRegister = api.registerApp(appId);
            if (!isRegister) {
                Toast.makeText(context, "微信注册失败", Toast.LENGTH_SHORT).show();
                Log.e("FastWx", "------------- 注册微信APP失败 -------------");
                api = null;
            } else {
                Log.e("FastWx", "------------- 注册微信APP成功 -------------");
            }
        }
        return api;
    }

    public static IWXAPI getIWXAPIInstance() {
        return api;
    }
}
