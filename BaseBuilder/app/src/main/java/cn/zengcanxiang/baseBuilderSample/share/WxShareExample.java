package cn.zengcanxiang.baseBuilderSample.share;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;

import cn.zengcanxiang.baseBuilderSample.R;
import cn.zengcanxiang.fastshare.FastShare;
import cn.zengcanxiang.fastshare.option.wx.WxShareBuilder;
import cn.zengcanxiang.fastshare.option.wx.WxShareOptions;
import cn.zengcanxiang.fastshare.ShareCallback;

public class WxShareExample extends Activity {

    WxShareBuilder builder = new WxShareBuilder()
            .context(this)
            .wxAppId("wx0d94fa46fdb8ad28");

    private int mTargetScene = SendMessageToWX.Req.WXSceneSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wx);
        builder.openDebug();
    }

    public void shareMiniProgram(View view) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        WxShareOptions options = builder
                .valueMiniProgram("http://www.qq.com", "gh_d43f693ca31f", "pages/play/index?cid=fvue88y1fsnk4w2&ptag=vicyao&seek=3219", "标题", "详情", bmp)
                .shareType(mTargetScene)
                .builder();
        share(options);
    }

    public void shareEmoji(View view) {
        byte[] s = new byte[1];
        WxShareOptions options = builder
                .valueEmoji("路径", "标题", "详情", s)
                .shareType(mTargetScene)
                .builder();
        share(options);
    }

    public void shareVideoUrl(View view) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        WxShareOptions options = builder
                .valueVideoUrl("http://v.youku.com/v_show/id_XODkyNjAyMzg4.html", "视频标题", "视频详情", bmp)
                .shareType(mTargetScene)
                .builder();
        share(options);
    }

    public void shareWebUrl(View view) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        WxShareOptions options = builder
                .valueWebUrl("http://www.baidu.com", "网页标题", "网页详情", bmp)
                .shareType(mTargetScene)
                .builder();
        share(options);
    }

    public void shareMusic(View view) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sss);
        WxShareOptions options = builder
                .valueMusicUrl("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3", "音乐标题", "音乐详情", bmp)
                .shareType(mTargetScene)
                .builder();
        share(options);
    }

    public void shareImg(View view) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        WxShareOptions options = builder
                .shareType(mTargetScene)
                .valueImg(bmp)
                .builder();
        share(options);
    }

    public void shareText(View view) {
        WxShareOptions options = builder
                .shareType(mTargetScene)
                .valueText("微信分享测试文字")
                .builder();
        share(options);
    }

    private void share(WxShareOptions options) {
        FastShare fastShare = new FastShare();
        fastShare.options(options).Wx().doShare(new ShareCallback() {
            @Override
            public void onSuccess() {
                Toast.makeText(WxShareExample.this, "成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(WxShareExample.this, "取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Exception e) {
                Toast.makeText(WxShareExample.this, "失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        if (!(view instanceof RadioButton)) {
            return;
        }

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.target_scene_session:
                if (checked) {
                    mTargetScene = SendMessageToWX.Req.WXSceneSession;
                }
                break;
            case R.id.target_scene_timeline:
                if (checked) {
                    mTargetScene = SendMessageToWX.Req.WXSceneTimeline;
                }
                break;
            case R.id.target_scene_favorite:
                if (checked) {
                    mTargetScene = SendMessageToWX.Req.WXSceneFavorite;
                }
                break;
        }
    }
}
