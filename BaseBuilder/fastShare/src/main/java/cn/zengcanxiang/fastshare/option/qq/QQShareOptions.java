package cn.zengcanxiang.fastshare.option.qq;

import android.app.Activity;

import java.util.ArrayList;

import cn.zengcanxiang.baseBuilder.share.ShareOptions;
import cn.zengcanxiang.baseBuilder.share.ShareType;


public class QQShareOptions extends ShareOptions<QQShareBuilder> {

    public int shareType;
    public Activity context;
    public String appId;
    public String valueTitle;
    public String valueSummary;
    public String valueAppName;

    public String valueTargetUrl;
    public String valueImg;
    public String valueMusicUrl;

    public String valueTitleQzone;
    public String valueSummaryQzone;
    public String valueTargetUrlQzone;
    public ArrayList<String> valueImgUrlsQzone;

    public boolean isOpenQZone;

    protected QQShareOptions(QQShareBuilder builder) {
        super(builder);
        this.appId = builder.getAppId();
        this.context = builder.getContext();
        this.shareType = builder.getShareType();
        if (shareType == ShareType.QQ.SHARE_TYPE_QQ) {
            this.valueTitle = builder.getValueTitle();
            this.valueSummary = builder.getValueSummary();
            this.valueAppName = builder.getValueAppName();
            this.isOpenQZone = builder.getIsOpenQZone();
            this.valueTargetUrl = builder.getValueTargetUrl();
            this.valueImg = builder.getValueImg();
            this.valueMusicUrl = builder.getValueMusicUrl();
        } else {
            this.valueTitleQzone = builder.getValueTitleQzone();
            this.valueSummaryQzone = builder.getValueSummaryQzone();
            this.valueTargetUrlQzone = builder.getValueTargetUrlQzone();
            this.valueImgUrlsQzone = builder.getValueImgUrlsQzone();
        }
    }
}