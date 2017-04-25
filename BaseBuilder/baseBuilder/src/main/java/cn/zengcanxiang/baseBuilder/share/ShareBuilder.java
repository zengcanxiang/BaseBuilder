package cn.zengcanxiang.baseBuilder.share;

import cn.zengcanxiang.baseBuilder.BaseBuilder;

public abstract class ShareBuilder extends BaseBuilder {
    /**
     * 分享内容的类别
     */
    protected int shareValueType;

    int getShareValueType() {
        return shareValueType;
    }

}
