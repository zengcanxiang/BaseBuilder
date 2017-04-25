package cn.zengcanxiang.fastshare.option.qq;


import java.util.ArrayList;

public class QzoneShareBuilder {

    private QQShareBuilder builder;

    public QzoneShareBuilder(QQShareBuilder builder) {
        this.builder = builder;
    }

    public QzoneShareBuilder valueQzone(String title, String summary, String targetUrl, ArrayList<String> imgUrls) {
        builder.valueQzone(title, summary, targetUrl, imgUrls);
        return this;
    }

    public QQShareOptions builder() {
        return builder.builder();
    }
}
