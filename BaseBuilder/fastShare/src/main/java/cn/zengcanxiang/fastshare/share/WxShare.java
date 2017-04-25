package cn.zengcanxiang.fastshare.share;


import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXEmojiObject;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXMusicObject;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXVideoObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import cn.zengcanxiang.baseBuilder.share.ShareType;
import cn.zengcanxiang.fastshare.ShareCallback;
import cn.zengcanxiang.fastshare.option.wx.WxShareOptions;
import cn.zengcanxiang.fastwx.WXCallbackActivity;
import cn.zengcanxiang.fastwx.WxCallback;
import cn.zengcanxiang.fastwx.WxChat;

public class WxShare {
    private final String TAG = getClass().getSimpleName();

    private IWXAPI mWXApi;

    private WxShareOptions mOptions;

    private static ShareCallback mCallback;

    private SendMessageToWX.Req mReq;

    public static int OTHER_THUMB_SIZE = 150;
    public static int VIDEO_THUMB_SIZE = 150;


    public WxShare(WxShareOptions options) {
        this.mOptions = options;
        mWXApi = WxChat.getIWXAPIInstance(mOptions.context, mOptions.wxAppId);
        WXCallbackActivity.addCallback(WXCallbackActivity.WX_CALLBACK_SHARE, new WxCallback() {
            @Override
            public void callback(BaseResp baseResp) {
                onResp(baseResp);
            }
        });

        Logger.init("WxShare").methodCount(0).hideThreadInfo();

        if (mOptions.isDebug) {
            Log.i(TAG, "------------- 微信分享初始完成 -------------");
        }
    }

    private void onResp(BaseResp resp) {
        if (mOptions.isDebug) {
            Log.i(TAG, "------------- 微信分享回调所有参数 -------------");
            Logger.json(JSON.toJSONString(resp));
        }
        if (null != mCallback) {
            switch (resp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    mCallback.onSuccess();
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL:
                    mCallback.onCancel();
                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED:
                default:
                    mCallback.onFailure(new Exception("BaseResp.ErrCode.ERR_AUTH_DENIED"));
                    break;
            }
        }
    }

    /**
     * 去分享
     */
    public void doShare(ShareCallback c) {
        mCallback = c;
        mReq = new SendMessageToWX.Req();
        int shareValueType = mOptions.shareValueType;
        if (mOptions.isDebug) {
            Log.i(TAG, "------------- 微信分享开始调用 -------------");
            if (mOptions.shareType == SendMessageToWX.Req.WXSceneSession) {
                Logger.i("此次分享到微信聊天");
            } else if (mOptions.shareType == SendMessageToWX.Req.WXSceneTimeline) {
                Logger.i("此次分享到朋友圈");
            } else {
                Logger.i("此次分享到微信收藏");
            }
        }

        switch (shareValueType) {
            case ShareType.SHARE_VALUE_TEXT:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:文字");
                }
                doUpReqText();
                break;
            case ShareType.SHARE_VALUE_IMG:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:图片");
                }
                doUpReqImg();
                break;
            case ShareType.SHARE_VALUE_MUSIC_URL:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:音乐url");
                }
                doUpReqMusicUrl();
                break;
            case ShareType.SHARE_VALUE_WEB_URL:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:网页url");
                }
                doUpReqWebUrl();
                break;
            case ShareType.SHARE_VALUE_VIDEO_URL:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:视频url");
                }

                doUpReqVideo();
                break;
            case ShareType.Wx.SHARE_VALUE_EMOJI:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:表情");
                }
                doUpReqEmoji();
                break;
            case ShareType.Wx.SHARE_VALUE_MINI_PROGRAM:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:小程序");
                }
                doUpReqMiniProgram();
                break;
        }

        if (mOptions.isDebug) {
            Log.i(TAG, "------------- 微信分享所有参数 -------------");
            Logger.json(JSON.toJSONString(mReq));
        }
        mWXApi.sendReq(mReq);
    }

    /**
     * 包装分享参数---小程序
     */
    private void doUpReqMiniProgram() {
        WXMiniProgramObject miniProgram = new WXMiniProgramObject();
        miniProgram.webpageUrl = mOptions.valueMiniProgramUrl;
        miniProgram.userName = mOptions.valueMiniProgramName;
        miniProgram.path = mOptions.valueMiniProgramPath;
        WXMediaMessage msg = new WXMediaMessage(miniProgram);
        msg.title = mOptions.valueMiniProgramTitle;
        msg.description = mOptions.valueMiniProgramDescription;

        Bitmap bmp = mOptions.valueMiniProgramCover;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, OTHER_THUMB_SIZE, OTHER_THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        mReq.transaction = buildTransaction("webpage");
        mReq.message = msg;
        mReq.scene = mOptions.shareType;
    }

    /**
     * 包装分享参数---表情
     */
    private void doUpReqEmoji() {
        WXEmojiObject emoji = new WXEmojiObject();
        emoji.emojiPath = mOptions.valueEmojiPath;
        if (TextUtils.isEmpty(emoji.emojiPath)) {
            emoji.emojiData = mOptions.valueEmojiData;
        }
        WXMediaMessage msg = new WXMediaMessage(emoji);
        msg.title = mOptions.valueEmojiTitle;
        msg.description = mOptions.valueEmojiDescription;
        msg.thumbData = mOptions.valueEmojiThumb;

        mReq.transaction = buildTransaction("emoji");
        mReq.message = msg;
        mReq.scene = mOptions.shareType;
    }

    /**
     * 包装分享参数---视频url
     */
    private void doUpReqVideo() {
        WXVideoObject video = new WXVideoObject();
        video.videoUrl = mOptions.valueVideoUrl;

        WXMediaMessage msg = new WXMediaMessage(video);
        msg.title = mOptions.valueVideoUrlTitle;
        msg.description = mOptions.valueVideoUrlDescription;

        Bitmap bmp = mOptions.valueVideoUrlCover;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, VIDEO_THUMB_SIZE, VIDEO_THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        mReq.transaction = buildTransaction("video");
        mReq.message = msg;
        mReq.scene = mOptions.shareType;
    }

    /**
     * 包装分享参数---网页url
     */
    private void doUpReqWebUrl() {
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = mOptions.valueWebUrl;

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = webPage;
        msg.title = mOptions.valueWebUrlTitle;
        msg.description = mOptions.valueWebUrlDescription;

        Bitmap bmp = mOptions.valueWebUrlCover;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, OTHER_THUMB_SIZE, OTHER_THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);

        mReq.transaction = buildTransaction("webUrl");
        mReq.message = msg;
        mReq.scene = mOptions.shareType;
    }

    /**
     * 包装分享参数---音乐url
     */
    private void doUpReqMusicUrl() {
        WXMusicObject music = new WXMusicObject();
        music.musicUrl = mOptions.valueMusicUrl;
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = music;
        msg.title = mOptions.valueMusicTitle;
        msg.description = mOptions.valueMusicDescription;
        msg.setThumbImage(mOptions.valueMusicCover);
        mReq.transaction = buildTransaction("music");
        mReq.message = msg;
        mReq.scene = mOptions.shareType;
    }

    /**
     * 包装分享参数---图片分享
     */
    private void doUpReqImg() {
        Bitmap bmp = mOptions.valueImg;
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, OTHER_THUMB_SIZE, OTHER_THUMB_SIZE, true);
        WXImageObject imgObj = new WXImageObject(mOptions.valueImg);
        bmp.recycle();
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
        mReq.transaction = buildTransaction("img");
        mReq.message = msg;
        mReq.scene = mOptions.shareType;
    }

    /**
     * 包装分享参数---文字分享
     */
    private void doUpReqText() {
        WXTextObject textObj = new WXTextObject();
        textObj.text = mOptions.valueText;
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = textObj;
        msg.description = textObj.text;
        mReq.transaction = buildTransaction("text");
        mReq.message = msg;
        mReq.scene = mOptions.shareType;
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
