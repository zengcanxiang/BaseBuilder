package cn.zengcanxiang.baseBuilder.share;


import android.support.annotation.IntDef;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ShareType {
    /**
     * 文本内容
     */
    public static final int SHARE_VALUE_TEXT = 0x120;//288
    /**
     * 图片
     */
    public static final int SHARE_VALUE_IMG = 0x121;//289

    /**
     * 音乐url
     */
    public static final int SHARE_VALUE_MUSIC_URL = 0x122;
    /**
     * 网页url
     */
    public static final int SHARE_VALUE_WEB_URL = 0x123;

    /**
     * 视频url
     */
    public static final int SHARE_VALUE_VIDEO_URL = 0x124;


    @IntDef(flag = true, value = {SHARE_VALUE_TEXT})
    @Retention(RetentionPolicy.SOURCE)
    @Inherited
    public @interface SinaValueType {

    }

    public static class QQ {
        /**
         * app消息
         */
        public static final int SHARE_VALUE_APP = 0x125;

        public static final int SHARE_TYPE_QQ = 0;
        public static final int SHARE_TYPE_QZONE = 1;


        @IntDef(flag = true, value = {SHARE_VALUE_WEB_URL, SHARE_VALUE_MUSIC_URL, SHARE_VALUE_IMG, SHARE_VALUE_APP})
        @Retention(RetentionPolicy.SOURCE)
        @Inherited
        public @interface QQValueType {

        }

        @IntDef(flag = true, value = {SHARE_TYPE_QQ, SHARE_TYPE_QZONE})
        @Retention(RetentionPolicy.SOURCE)
        @Inherited
        public @interface QQType {

        }
    }

    public static class Wx {
        /**
         * 表情
         */
        public static final int SHARE_VALUE_EMOJI = 0x125;
        /**
         * 小程序
         */
        public static final int SHARE_VALUE_MINI_PROGRAM = 0x126;

        @IntDef(flag = true, value = {SHARE_VALUE_TEXT, SHARE_VALUE_IMG, SHARE_VALUE_MUSIC_URL, SHARE_VALUE_WEB_URL, SHARE_VALUE_EMOJI, SHARE_VALUE_MINI_PROGRAM})
        @Retention(RetentionPolicy.SOURCE)
        @Inherited
        public @interface WxValueType {

        }
    }

}


