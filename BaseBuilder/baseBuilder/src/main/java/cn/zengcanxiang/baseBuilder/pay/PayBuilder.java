package cn.zengcanxiang.baseBuilder.pay;

import android.content.Context;

import cn.zengcanxiang.baseBuilder.BaseBuilder;

public abstract class PayBuilder extends BaseBuilder {
    private Context mContext;
    private String mParams;
    private boolean isSandbox = false;

    public PayBuilder() {
    }

    public PayBuilder(Context context, String params) {
        this.mContext = context;
        this.mParams = params;
    }

    public PayBuilder setContext(Context mContext) {
        this.mContext = mContext;
        return this;
    }

    public PayBuilder setParams(String params) {
        this.mParams = params;
        return this;
    }

    /**
     * 在沙箱环境操作
     */
    public PayBuilder openSandbox() {
        this.isSandbox = true;
        return this;
    }

    public PayBuilder closeSandbox() {
        this.isSandbox = false;
        return this;
    }

    public boolean isSandbox() {
        return isSandbox;
    }

    public String getParams() {
        return mParams;
    }

    public Context getContext() {
        return mContext;
    }

}
