package cn.zengcanxiang.fastshare.share;

import android.os.Bundle;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import cn.zengcanxiang.baseBuilder.share.ShareType;
import cn.zengcanxiang.fastshare.ShareCallback;
import cn.zengcanxiang.fastshare.option.qq.QQShareOptions;

import static android.content.ContentValues.TAG;
import static com.tencent.connect.share.QzoneShare.SHARE_TO_QZONE_TYPE_IMAGE_TEXT;


public class QQShare {

    private QQShareOptions mOptions;
    private ShareCallback mCallback;
    private Bundle mParams = new Bundle();
    private IUiListener mListener = new IUiListener() {

        @Override
        public void onComplete(Object o) {
            mCallback.onSuccess();
        }

        @Override
        public void onError(UiError e) {
            mCallback.onFailure(new Exception(e.errorMessage));
        }

        @Override
        public void onCancel() {
            mCallback.onCancel();
        }
    };

    private Tencent mTencent;

    public QQShare(QQShareOptions options) {
        this.mOptions = options;
        mTencent = Tencent.createInstance(mOptions.appId, mOptions.context);
    }

    public void doShare(ShareCallback c) {
        mCallback = c;
        if (mOptions.isDebug) {
            Log.i(TAG, "------------- QQ分享开始调用 -------------");
        }
        if (mOptions.shareType == ShareType.QQ.SHARE_TYPE_QZONE) {
            shareToQzone();
        } else {
            shareToQQ();
        }
    }

    private void shareToQQ() {
        if (mOptions.isDebug) {
            Logger.i("此次分享到QQ");
        }
        int shareType = mOptions.shareValueType;
        switch (shareType) {
            case ShareType.SHARE_VALUE_WEB_URL:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:链接");
                }
                doUpParamsWebUrl();
                break;
            case ShareType.SHARE_VALUE_IMG:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:图片");
                }
                doUpParamsImg();
                break;
            case ShareType.SHARE_VALUE_MUSIC_URL:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:音乐url");
                }
                doUpParamsMusicUrl();
                break;
            case ShareType.QQ.SHARE_VALUE_APP:
                if (mOptions.isDebug) {
                    Logger.i("此次分享类别:App消息");
                }
                doUpParamsApp();
                break;
        }
        if (mOptions.isOpenQZone) {
            mParams.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_EXT_INT,
                    com.tencent.connect.share.QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        } else {
            mParams.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_EXT_INT,
                    com.tencent.connect.share.QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
        }
        mTencent.shareToQQ(mOptions.context, mParams, mListener);
    }

    private void shareToQzone() {
        if (mOptions.isDebug) {
            Logger.i("此次分享到QQ空间");
        }
        doUpParamsQzone();
        mTencent.shareToQzone(mOptions.context, mParams, mListener);
    }

    private void doUpParamsQzone() {
        mParams.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_KEY_TYPE, SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
        mParams.putString(QzoneShare.SHARE_TO_QQ_TITLE, mOptions.valueTitleQzone);
        mParams.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, mOptions.valueSummaryQzone);
        mParams.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, mOptions.valueTargetUrlQzone);
        mParams.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, mOptions.valueImgUrlsQzone);
    }

    private void doUpParamsWebUrl() {
        mParams.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_KEY_TYPE,
                com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TITLE,
                mOptions.valueTitle);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_SUMMARY,
                mOptions.valueSummary);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TARGET_URL,
                mOptions.valueTargetUrl);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_IMAGE_URL,
                mOptions.valueImg);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_APP_NAME,
                mOptions.valueAppName);

    }

    private void doUpParamsImg() {
        mParams.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_KEY_TYPE,
                com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_IMAGE);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,
                mOptions.valueImg);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_APP_NAME,
                mOptions.valueAppName);
    }

    private void doUpParamsMusicUrl() {
        mParams.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_KEY_TYPE,
                com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_AUDIO);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TITLE,
                mOptions.valueTitle);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_SUMMARY,
                mOptions.valueSummary);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TARGET_URL,
                mOptions.valueTargetUrl);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_IMAGE_URL,
                mOptions.valueImg);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_AUDIO_URL,
                mOptions.valueMusicUrl);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_APP_NAME,
                mOptions.valueAppName);
    }

    private void doUpParamsApp() {
        mParams.putInt(com.tencent.connect.share.QQShare.SHARE_TO_QQ_KEY_TYPE,
                com.tencent.connect.share.QQShare.SHARE_TO_QQ_TYPE_APP);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_TITLE,
                mOptions.valueTitle);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_SUMMARY,
                mOptions.valueSummary);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_IMAGE_URL,
                mOptions.valueImg);
        mParams.putString(com.tencent.connect.share.QQShare.SHARE_TO_QQ_APP_NAME,
                mOptions.valueAppName);
    }


}
