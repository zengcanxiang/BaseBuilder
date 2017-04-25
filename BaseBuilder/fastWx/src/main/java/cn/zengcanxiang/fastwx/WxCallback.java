package cn.zengcanxiang.fastwx;

import com.tencent.mm.opensdk.modelbase.BaseResp;


public interface WxCallback {
    void callback(BaseResp baseResp);
}
