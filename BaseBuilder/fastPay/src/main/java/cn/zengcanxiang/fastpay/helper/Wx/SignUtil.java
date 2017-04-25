package cn.zengcanxiang.fastpay.helper.Wx;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信签名生成Util
 */
class SignUtil {
    /**
     * 生成签名字符串
     *
     * @param jsonObject 提交参数
     * @param apiKey     商户Api密钥
     */
    static String sign(JSONObject jsonObject, String apiKey) {
        Map<String, String> map = new HashMap<>();
        try {
            map.put("appid", jsonObject.getString("appid"));
            map.put("partnerid", jsonObject.getString("partnerid"));
            map.put("prepayid", jsonObject.getString("prepayid"));
            map.put("package", jsonObject.getString("package"));
            map.put("timestamp", jsonObject.getString("timestamp"));
            map.put("noncestr", jsonObject.getString("noncestr"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String temp = spliceMap(map);
        temp = temp + "&key=" + apiKey;
        return MD5(temp).toUpperCase();
    }

    /**
     * 拼接Map key和value
     */
    private static String spliceMap(Map<String, String> map) {
        List<String> keys = new ArrayList<>(map.keySet());
        // key排序
        Collections.sort(keys);
        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = map.get(key);
            authInfo.append(spliceKV(key, value, false));
            authInfo.append("&");
        }
        if (authInfo.length()>0){
            //删除最后一个&
            authInfo.delete(authInfo.length() - 1, authInfo.length());
        }
        return authInfo.toString();
    }

    /**
     * 拼接键值对
     *
     * @param key      键
     * @param value    值
     * @param isEncode 是否进行编码转化
     */
    private static String spliceKV(String key, String value, boolean isEncode) {
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        sb.append("=");
        if (isEncode) {
            try {
                sb.append(URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(value);
            }
        } else {
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * 将字符进行MD5
     */
    private static String MD5(String temp) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(temp.getBytes("UTF-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
