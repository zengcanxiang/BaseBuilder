package cn.zengcanxiang.fastshare;


import cn.zengcanxiang.baseBuilder.share.ShareOptions;
import cn.zengcanxiang.fastshare.option.qq.QQShareOptions;
import cn.zengcanxiang.fastshare.option.wx.WxShareOptions;
import cn.zengcanxiang.fastshare.share.QQShare;
import cn.zengcanxiang.fastshare.share.WxShare;

public class FastShare {

    private int shareType;

    private ShareOptions mOptions;

    public <S extends ShareOptions> FastShare(S options) {
        this.mOptions = options;
    }

    public FastShare() {
    }

    public FastShare QQ() {
        shareType = 0;
        return this;
    }

    public FastShare Wx() {
        shareType = 1;
        return this;
    }

    public FastShare Sina() {
        shareType = 2;
        return this;
    }

    public <S extends ShareOptions> FastShare options(S options) {
        this.mOptions = options;
        return this;
    }

    public void doShare(ShareCallback callback) {
        switch (shareType) {
            case 0:
                QQShare share = new QQShare((QQShareOptions) mOptions);
                share.doShare(callback);
                break;
            case 1:
                WxShare wxShare = new WxShare((WxShareOptions) mOptions);
                wxShare.doShare(callback);
                break;
            case 2:
                break;
        }
    }


}
