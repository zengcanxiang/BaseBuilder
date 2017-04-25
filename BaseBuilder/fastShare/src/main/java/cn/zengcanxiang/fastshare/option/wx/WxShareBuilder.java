package cn.zengcanxiang.fastshare.option.wx;

import android.content.Context;
import android.graphics.Bitmap;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import cn.zengcanxiang.baseBuilder.share.ShareBuilder;
import cn.zengcanxiang.baseBuilder.share.ShareType;


public class WxShareBuilder extends ShareBuilder {

    private String wxAppId;
    private Context context;
    private Bitmap valueImg;
    private String valueText;

    private String valueMusicUrl;
    private String valueMusicTitle = "分享音乐标题";
    private String valueMusicDescription = "分享音乐详情";
    private Bitmap valueMusicCover;

    private String valueWebUrl;
    private String valueWebUrlTitle = "分享网页标题";
    private String valueWebUrlDescription = "分享网页详情";
    private Bitmap valueWebUrlCover;

    private String valueVideoUrl;
    private String valueVideoUrlTitle = "分享视频标题";
    private String valueVideoUrlDescription = "分享视频详情";
    private Bitmap valueVideoUrlCover;

    private String valueEmojiPath;
    private byte[] valueEmojiData;
    private String valueEmojiTitle = "分享表情标题";
    private String valueEmojiDescription = "分享表情详情";
    private byte[] valueEmojiThumb;

    private String valueMiniProgramUrl;
    private String valueMiniProgramName;
    private String valueMiniProgramPath;
    private String valueMiniProgramTitle = "分享小程序标题";
    private String valueMiniProgramDescription = "分享小程序详情";
    private Bitmap valueMiniProgramCover;


    private int shareType = SendMessageToWX.Req.WXSceneSession;

    @Override
    public WxShareOptions builder() {
        return new WxShareOptions(this);
    }

    String getValueText() {
        return valueText;
    }

    Bitmap getValueImg() {
        return valueImg;
    }

    String getValueMusicUrl() {
        return valueMusicUrl;
    }

    String getValueMusicTitle() {
        return valueMusicTitle;
    }

    String getValueMusicDescription() {
        return valueMusicDescription;
    }

    Bitmap getValueMusicCover() {
        return valueMusicCover;
    }

    String getValueWebUrl() {
        return valueWebUrl;
    }

    String getValueWebUrlTitle() {
        return valueWebUrlTitle;
    }

    String getValueWebUrlDescription() {
        return valueWebUrlDescription;
    }

    Bitmap getValueWebUrlCover() {
        return valueWebUrlCover;
    }

    String getValueVideoUrl() {
        return valueVideoUrl;
    }

    String getValueVideoUrlTitle() {
        return valueVideoUrlTitle;
    }

    String getValueVideoUrlDescription() {
        return valueVideoUrlDescription;
    }

    Bitmap getValueVideoUrlCover() {
        return valueVideoUrlCover;
    }

    String getValueEmojiPath() {
        return valueEmojiPath;
    }

    byte[] getValueEmojiData() {
        return valueEmojiData;
    }

    String getValueEmojiTitle() {
        return valueEmojiTitle;
    }

    String getValueEmojiDescription() {
        return valueEmojiDescription;
    }

    byte[] getValueEmojiThumb() {
        return valueEmojiThumb;
    }

    String getValueMiniProgramUrl() {
        return valueMiniProgramUrl;
    }

    String getValueMiniProgramName() {
        return valueMiniProgramName;
    }

    String getValueMiniProgramPath() {
        return valueMiniProgramPath;
    }

    String getValueMiniProgramTitle() {
        return valueMiniProgramTitle;
    }

    String getValueMiniProgramDescription() {
        return valueMiniProgramDescription;
    }

    Bitmap getValueMiniProgramCover() {
        return valueMiniProgramCover;
    }

    String getWxAppId() {
        return wxAppId;
    }

    Context getContext() {
        return context;
    }

    int getShareType() {
        return shareType;
    }

    public WxShareBuilder context(Context context) {
        this.context = context;
        return this;
    }

    public WxShareBuilder wxAppId(String appId) {
        this.wxAppId = appId;
        return this;
    }

    public WxShareBuilder shareType(int shareType) {
        if (shareType != SendMessageToWX.Req.WXSceneSession
                && shareType != SendMessageToWX.Req.WXSceneTimeline
                && shareType != SendMessageToWX.Req.WXSceneFavorite) {
            throw new IllegalArgumentException("分享类别参数不对");
        }
        this.shareType = shareType;
        return this;
    }

    public WxShareBuilder shareChat() {
        this.shareType = SendMessageToWX.Req.WXSceneSession;
        return this;
    }

    public WxShareBuilder shareFriends() {
        this.shareType = SendMessageToWX.Req.WXSceneTimeline;
        return this;
    }

    public WxShareBuilder shareCollection() {
        this.shareType = SendMessageToWX.Req.WXSceneFavorite;
        return this;
    }

    public WxShareBuilder valueText(String shareText) {
        this.valueText = shareText;
        this.shareValueType = ShareType.SHARE_VALUE_TEXT;
        return this;
    }

    public WxShareBuilder valueImg(Bitmap img) {
        this.valueImg = img;
        this.shareValueType = ShareType.SHARE_VALUE_IMG;
        return this;
    }

    public WxShareBuilder valueMusicUrl(String musicUrl, String title, String description, Bitmap cover) {
        this.valueMusicUrl = musicUrl;
        this.valueMusicTitle = title;
        this.valueMusicDescription = description;
        this.valueMusicCover = cover;
        this.shareValueType = ShareType.SHARE_VALUE_MUSIC_URL;
        return this;
    }

    public WxShareBuilder valueWebUrl(String url, String title, String description, Bitmap cover) {
        this.valueWebUrl = url;
        this.valueWebUrlTitle = title;
        this.valueWebUrlDescription = description;
        this.valueWebUrlCover = cover;
        this.shareValueType = ShareType.SHARE_VALUE_WEB_URL;
        return this;
    }


    public WxShareBuilder valueVideoUrl(String url, String title, String description, Bitmap cover) {
        this.valueVideoUrl = url;
        this.valueVideoUrlTitle = title;
        this.valueVideoUrlDescription = description;
        this.valueVideoUrlCover = cover;
        this.shareValueType = ShareType.SHARE_VALUE_VIDEO_URL;
        return this;
    }

    public WxShareBuilder valueEmoji(String emojiPath, byte[] emojiThumb) {
        this.valueEmojiPath = emojiPath;
        this.valueEmojiThumb = emojiThumb;
        this.valueEmojiTitle = "";
        this.valueEmojiDescription = "";
        this.shareValueType = ShareType.Wx.SHARE_VALUE_EMOJI;
        return this;
    }

    public WxShareBuilder valueEmoji(String emojiPath, String title, String description, byte[] emojiThumb) {
        this.valueEmojiPath = emojiPath;
        this.valueEmojiThumb = emojiThumb;
        this.valueEmojiTitle = title;
        this.valueEmojiDescription = description;
        this.shareValueType = ShareType.Wx.SHARE_VALUE_EMOJI;
        return this;
    }

    public WxShareBuilder valueEmoji(byte[] emojiData, byte[] emojiThumb) {
        this.valueEmojiData = emojiData;
        this.valueEmojiThumb = emojiThumb;
        this.valueEmojiTitle = "";
        this.valueEmojiDescription = "";
        this.shareValueType = ShareType.Wx.SHARE_VALUE_EMOJI;
        return this;
    }

    public WxShareBuilder valueEmoji(byte[] emojiData, String title, String description, byte[] emojiThumb) {
        this.valueEmojiData = emojiData;
        this.valueEmojiThumb = emojiThumb;
        this.valueEmojiTitle = title;
        this.valueEmojiDescription = description;
        this.shareValueType = ShareType.Wx.SHARE_VALUE_EMOJI;
        return this;
    }

    public WxShareBuilder valueMiniProgram(String url, String name, String path, Bitmap cover) {
        this.valueMiniProgramUrl = url;
        this.valueMiniProgramName = name;
        this.valueMiniProgramPath = path;
        this.valueMiniProgramCover = cover;
        this.shareValueType = ShareType.Wx.SHARE_VALUE_MINI_PROGRAM;
        return this;
    }

    public WxShareBuilder valueMiniProgram(String url, String name, String path, String title, String description, Bitmap cover) {
        this.valueMiniProgramUrl = url;
        this.valueMiniProgramName = name;
        this.valueMiniProgramPath = path;
        this.valueMiniProgramTitle = title;
        this.valueMiniProgramDescription = description;
        this.valueMiniProgramCover = cover;
        this.shareValueType = ShareType.Wx.SHARE_VALUE_MINI_PROGRAM;
        return this;
    }

    public WxShareBuilder valueType(@ShareType.Wx.WxValueType int type) {
        this.shareValueType = type;
        return this;
    }
}
