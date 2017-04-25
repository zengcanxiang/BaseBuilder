package cn.zengcanxiang.baseBuilderSample.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import cn.zengcanxiang.baseBuilderSample.R;


public class ShareExample extends Activity {

    Button wx, qq, sina;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_main);
        wx = (Button) findViewById(R.id.share_wx);
        qq = (Button) findViewById(R.id.share_qq);
        sina = (Button) findViewById(R.id.share_sina);
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShareExample.this, WxShareExample.class);
                startActivity(i);
            }
        });
        qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShareExample.this, QQShareExample.class);
                startActivity(i);
            }
        });
        sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShareExample.this, SinaShareExample.class);
                startActivity(i);
            }
        });
    }

}
