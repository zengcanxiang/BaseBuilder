package cn.zengcanxiang.fastshare.option.qq;

import android.app.Activity;

import java.util.ArrayList;

import cn.zengcanxiang.baseBuilder.share.ShareBuilder;
import cn.zengcanxiang.baseBuilder.share.ShareType;


public class QQShareBuilder extends ShareBuilder {

    private String appId;
    private Activity context;

    private String valueTitle;
    private String valueSummary;
    private String valueAppName;

    private String valueTargetUrl;
    private String valueImg;
    private String valueMusicUrl;

    private String valueTitleQzone;
    private String valueSummaryQzone;
    private String valueTargetUrlQzone;
    private ArrayList<String> valueImgUrlsQzone;

    private boolean isOpenQZone;
    private int shareType = ShareType.QQ.SHARE_TYPE_QQ;

    @Override
    public QQShareOptions builder() {
        return new QQShareOptions(this);
    }

    public QQShareBuilder valueType(@ShareType.QQ.QQValueType int type) {
        this.shareValueType = type;
        return this;
    }

    String getAppId() {
        return appId;
    }

    Activity getContext() {
        return context;
    }

    int getShareType() {
        return shareType;
    }

    String getValueTitle() {
        return valueTitle;
    }

    String getValueSummary() {
        return valueSummary;
    }

    String getValueAppName() {
        return valueAppName;
    }

    String getValueTargetUrl() {
        return valueTargetUrl;
    }

    String getValueImg() {
        return valueImg;
    }

    String getValueMusicUrl() {
        return valueMusicUrl;
    }

    String getValueTitleQzone() {
        return valueTitleQzone;
    }

    String getValueSummaryQzone() {
        return valueSummaryQzone;
    }

    String getValueTargetUrlQzone() {
        return valueTargetUrlQzone;
    }

    ArrayList<String> getValueImgUrlsQzone() {
        return valueImgUrlsQzone;
    }

    boolean getIsOpenQZone() {
        return isOpenQZone;
    }

    public QQShareBuilder appId(String appId) {
        this.appId = appId;
        return this;
    }

    public QQShareBuilder context(Activity context) {
        this.context = context;
        return this;
    }

    public QQShareBuilder QQ() {
        this.shareType = ShareType.QQ.SHARE_TYPE_QQ;
        return this;
    }

    public QzoneShareBuilder QZone() {
        this.shareType = ShareType.QQ.SHARE_TYPE_QZONE;
        return new QzoneShareBuilder(this);
    }

    public QQShareBuilder shareType(@ShareType.QQ.QQType int shareType) {
        this.shareType = shareType;
        return this;
    }

    /**
     * 分享网页链接,QQ会自动生成没有传入的数据
     *
     * @param webUrl 网页url
     * @param title  分享标题
     */
    public QQShareBuilder valueWebUrl(String webUrl, String title) {
        this.valueTargetUrl = webUrl;
        this.valueTitle = title;
        this.shareValueType = ShareType.SHARE_VALUE_WEB_URL;
        return this;
    }

    /**
     * 分享网页链接,QQ会自动生成没有传入的数据
     *
     * @param webUrl      网页url
     * @param title       分享标题
     * @param summary     消息摘要,为空字符,QQ会抓取网页字符(如果上一次分享传入的摘要，这次分享传入为空，那么相同网址的摘要会使用上一次的摘要)
     * @param img         缩略图 ,为空字符,QQ会抓取网页logo
     * @param appName     app名字
     * @param isOpenQZone 是否同时分享到QQ空间
     */
    public QQShareBuilder valueWebUrl(String webUrl, String title, String summary, String img, String appName, boolean isOpenQZone) {
        this.valueTargetUrl = webUrl;
        this.valueTitle = title;
        this.valueSummary = summary;
        this.valueImg = img;
        this.valueAppName = appName;
        this.isOpenQZone = isOpenQZone;
        this.shareValueType = ShareType.SHARE_VALUE_WEB_URL;
        return this;
    }

    /**
     * 分享本地图片
     *
     * @param img 本独图片路径
     */
    public QQShareBuilder valueLocalImg(String img) {
        this.valueImg = img;
        this.shareValueType = ShareType.SHARE_VALUE_IMG;
        return this;
    }

    /**
     * 分享本地图片
     *
     * @param img         本独图片路径
     * @param appName     app名字
     * @param isOpenQZone 是否同时分享到QQ空间
     */
    public QQShareBuilder valueLocalImg(String img, String appName, boolean isOpenQZone) {
        this.valueImg = img;
        this.valueAppName = appName;
        this.isOpenQZone = isOpenQZone;
        this.shareValueType = ShareType.SHARE_VALUE_IMG;
        return this;
    }

    /**
     * 分享音乐链接,QQ会自动生成没有传入的数据
     *
     * @param musicUrl  音乐链接
     * @param targetUrl 音乐链接
     * @param title     标题
     */
    public QQShareBuilder valueMusicUrl(String musicUrl, String targetUrl, String title) {
        this.valueTargetUrl = targetUrl;
        this.valueMusicUrl = musicUrl;
        this.valueTitle = title;
        this.shareValueType = ShareType.SHARE_VALUE_MUSIC_URL;
        return this;
    }

    /**
     * 分享音乐链接,QQ会自动生成没有传入的数据
     *
     * @param musicUrl    音乐链接
     * @param targetUrl   音乐链接
     * @param title       标题
     * @param summary     消息摘要,为空字符,QQ会抓取网页字符(如果上一次分享传入的摘要，这次分享传入为空，那么相同网址的摘要会使用上一次的摘要)
     * @param img         缩略图 ,为空字符,QQ会抓取网页logo
     * @param appName     app名字
     * @param isOpenQZone 是否同时分享到QQ空间
     */
    public QQShareBuilder valueMusicUrl(String musicUrl, String targetUrl, String title, String summary, String img, String appName, boolean isOpenQZone) {
        this.valueTargetUrl = targetUrl;
        this.valueMusicUrl = musicUrl;
        this.valueTitle = title;
        this.valueSummary = summary;
        this.valueImg = img;
        this.valueAppName = appName;
        this.isOpenQZone = isOpenQZone;
        this.shareValueType = ShareType.SHARE_VALUE_MUSIC_URL;
        return this;
    }

    /**
     * 分享app消息
     *
     * @param title       标题
     * @param summary     消息摘要
     * @param img         缩略图,分享图片的URL或者本地路径。
     * @param appName     app名字
     * @param isOpenQZone 是否同时分享到QQ空间
     */
    public QQShareBuilder valueApp(String title, String summary, String img, String appName, boolean isOpenQZone) {
        this.valueTitle = title;
        this.valueSummary = summary;
        this.valueImg = img;
        this.valueAppName = appName;
        this.isOpenQZone = isOpenQZone;
        this.shareValueType = ShareType.QQ.SHARE_VALUE_APP;
        return this;
    }

    /**
     * 分享到QQ空间
     */
    void valueQzone(String title, String summary, String targetUrl, ArrayList<String> imgUrls) {
        this.valueTitleQzone = title;
        this.valueSummaryQzone = summary;
        this.valueTargetUrlQzone = targetUrl;
        this.valueImgUrlsQzone = imgUrls;
    }
}
