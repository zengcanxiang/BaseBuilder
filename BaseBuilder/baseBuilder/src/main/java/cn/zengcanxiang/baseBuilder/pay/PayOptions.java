package cn.zengcanxiang.baseBuilder.pay;

import android.content.Context;

import cn.zengcanxiang.baseBuilder.BaseOptions;

public class PayOptions<P extends PayBuilder> extends BaseOptions {

    public Context mContext;
    public String mParams;
    public boolean isSandbox;

    protected PayOptions(P builder) {
        super(builder);
        mContext = builder.getContext();
        mParams = builder.getParams();
        isSandbox = builder.isSandbox();
    }

}
