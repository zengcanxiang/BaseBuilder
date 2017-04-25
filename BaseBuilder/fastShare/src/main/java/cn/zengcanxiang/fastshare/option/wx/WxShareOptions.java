package cn.zengcanxiang.fastshare.option.wx;

import android.content.Context;
import android.graphics.Bitmap;

import cn.zengcanxiang.baseBuilder.share.ShareOptions;
import cn.zengcanxiang.baseBuilder.share.ShareType;


public class WxShareOptions extends ShareOptions<WxShareBuilder> {

    public String wxAppId;
    public Context context;
    public int shareType;

    public String valueText;

    public Bitmap valueImg;

    public String valueMusicUrl;
    public String valueMusicTitle;
    public String valueMusicDescription;
    public Bitmap valueMusicCover;

    public String valueWebUrl;
    public String valueWebUrlTitle;
    public String valueWebUrlDescription;
    public Bitmap valueWebUrlCover;

    public String valueVideoUrl;
    public String valueVideoUrlTitle;
    public String valueVideoUrlDescription;
    public Bitmap valueVideoUrlCover;

    public String valueEmojiPath;
    public byte[] valueEmojiData;
    public String valueEmojiTitle;
    public String valueEmojiDescription;
    public byte[] valueEmojiThumb;


    public String valueMiniProgramUrl;
    public String valueMiniProgramName;
    public String valueMiniProgramPath;
    public String valueMiniProgramTitle;
    public String valueMiniProgramDescription;
    public Bitmap valueMiniProgramCover;

    WxShareOptions(WxShareBuilder builder) {
        super(builder);
        this.wxAppId = builder.getWxAppId();
        this.context = builder.getContext();
        this.shareType = builder.getShareType();
        //这样不会同时存在多个数据，只会留下需要的
        switch (shareValueType) {
            case ShareType.SHARE_VALUE_TEXT:
                this.valueText = builder.getValueText();
                break;
            case ShareType.SHARE_VALUE_IMG:
                this.valueImg = builder.getValueImg();
                break;
            case ShareType.SHARE_VALUE_MUSIC_URL:
                this.valueMusicUrl = builder.getValueMusicUrl();
                this.valueMusicTitle = builder.getValueMusicTitle();
                this.valueMusicDescription = builder.getValueMusicDescription();
                this.valueMusicCover = builder.getValueMusicCover();
                break;
            case ShareType.SHARE_VALUE_WEB_URL:
                this.valueWebUrl = builder.getValueWebUrl();
                this.valueWebUrlTitle = builder.getValueWebUrlTitle();
                this.valueWebUrlDescription = builder.getValueWebUrlDescription();
                this.valueWebUrlCover = builder.getValueWebUrlCover();
                break;
            case ShareType.SHARE_VALUE_VIDEO_URL:
                this.valueVideoUrl = builder.getValueVideoUrl();
                this.valueVideoUrlTitle = builder.getValueVideoUrlTitle();
                this.valueVideoUrlDescription = builder.getValueVideoUrlDescription();
                this.valueVideoUrlCover = builder.getValueVideoUrlCover();
                break;
            case ShareType.Wx.SHARE_VALUE_EMOJI:
                this.valueEmojiTitle = builder.getValueEmojiTitle();
                this.valueEmojiDescription = builder.getValueEmojiDescription();
                this.valueEmojiThumb = builder.getValueEmojiThumb();
                this.valueEmojiData = builder.getValueEmojiData();
                this.valueEmojiPath = builder.getValueEmojiPath();
                break;
            case ShareType.Wx.SHARE_VALUE_MINI_PROGRAM:
                this.valueMiniProgramUrl = builder.getValueMiniProgramUrl();
                this.valueMiniProgramName = builder.getValueMiniProgramName();
                this.valueMiniProgramPath = builder.getValueMiniProgramPath();
                this.valueMiniProgramTitle = builder.getValueMiniProgramTitle();
                this.valueMiniProgramDescription = builder.getValueMiniProgramDescription();
                this.valueMiniProgramCover = builder.getValueMiniProgramCover();
                break;
        }

    }
}
