package cn.zengcanxiang.baseBuilderSample.share;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

import cn.zengcanxiang.baseBuilderSample.R;
import cn.zengcanxiang.fastshare.option.qq.QQShareBuilder;
import cn.zengcanxiang.fastshare.option.qq.QQShareOptions;
import cn.zengcanxiang.fastshare.share.QQShare;
import cn.zengcanxiang.fastshare.ShareCallback;

public class QQShareExample extends Activity {

    QQShareBuilder builder = new QQShareBuilder()
            .context(this)
            .appId("1106023719");
    ShareCallback callback = new ShareCallback() {
        @Override
        public void onSuccess() {
            Toast.makeText(QQShareExample.this, "成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(QQShareExample.this, "取消", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(QQShareExample.this, "失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qq);
        builder.openDebug();
    }

    public void shareImg(View view) {
        QQShareOptions options = builder
                .QQ()
                .valueLocalImg("http://www.baidu.com")
                .builder();
        QQShare share = new QQShare(options);
        share.doShare(callback);
    }

    public void shareWebUrl(View view) {
        QQShareOptions options = builder
                .QQ()
                .valueWebUrl("http://www.baidu.com", "标题")
                .builder();
        QQShare share = new QQShare(options);
        share.doShare(callback);
    }

    public void shareMusic(View view) {
        QQShareOptions options = builder
                .QQ()
                .valueMusicUrl("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3", "http://www.baidu.com", "标题")
                .builder();
        QQShare share = new QQShare(options);
        share.doShare(callback);
    }

    public void shareApp(View view) {
        QQShareOptions options = builder
                .QQ()
                .valueApp("标题", "摘要", "http://i1.sanwen8.cn/doc/1609/857-160923101002344.jpg", "appname", false)
                .builder();
        QQShare share = new QQShare(options);
        share.doShare(callback);
    }

    public void shareQzone(View view) {
        QQShareOptions options = builder
                .QZone()
                .valueQzone("标题", "摘要", "http://www.baidu.com", new ArrayList<String>())
                .builder();
        QQShare share = new QQShare(options);
        share.doShare(callback);
    }

    public void onRadioButtonClicked(View view) {
        if (!(view instanceof RadioButton)) {
            return;
        }
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.target_scene_session:
                if (checked) {
                    findViewById(R.id.send_Qzone).setVisibility(View.GONE);
                    findViewById(R.id.send_img).setVisibility(View.VISIBLE);
                    findViewById(R.id.send_music).setVisibility(View.VISIBLE);
                    findViewById(R.id.send_webpage).setVisibility(View.VISIBLE);
                    findViewById(R.id.send_appbrand).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.target_scene_timeline:
                if (checked) {
                    findViewById(R.id.send_Qzone).setVisibility(View.VISIBLE);
                    findViewById(R.id.send_img).setVisibility(View.GONE);
                    findViewById(R.id.send_music).setVisibility(View.GONE);
                    findViewById(R.id.send_webpage).setVisibility(View.GONE);
                    findViewById(R.id.send_appbrand).setVisibility(View.GONE);
                }
                break;
        }
    }

}
