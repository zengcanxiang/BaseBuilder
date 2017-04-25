package cn.zengcanxiang.baseBuilder.share;

import cn.zengcanxiang.baseBuilder.BaseOptions;


public class ShareOptions<S extends ShareBuilder> extends BaseOptions {
    /**
     * 分享内容的类别
     */
    public int shareValueType;


    protected ShareOptions(S builder) {
        super(builder);
        this.shareValueType = builder.getShareValueType();
    }
}
