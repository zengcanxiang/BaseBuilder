package cn.zengcanxiang.fastshare;


public interface ShareCallback {
    void onSuccess();

    void onCancel();

    void onFailure(Exception e);
}
