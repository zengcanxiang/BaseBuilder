package cn.zengcanxiang.fastpay.helper.Ali;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>帮助客户端在没有服务器配合下快速完成支付参数的构建</p>
 * <p color='red'>本功能将加密过程在客户端实现,存在反编译、二次打包、debug密钥等风险。</p>
 * <p>请斟酌风险后使用，概不负责</p>
 * <a href="https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.hMTyUb&treeId=204&articleId=105300&docType=1">支付宝文档地址</a>
 */
public class AliHelper {

    private static String APP_ID = "";

    private static String NOTIFY_URL = "";

    private static String RSA2_PRIVATE = "";

    private static String RSA_PRIVATE = "";

    private static String TIME_STYLE = "yyyy-MM-dd HH:mm:ss";

    private static String PRODUCT_CODE = "QUICK_MSECURITY_PAY";

    private static String SELLER_ID = "";


    /**
     * 构建支付宝最终支付参数,当需要使用其他参数时，方便可选配置
     *
     * @param params 支付参数实体类
     */
    public static String buildPayParams(AliPayParams params) {

        return buildPayParams(params.getOutTradeNo(),
                params.getSubject(),
                params.getTotalMoney(),
                params.getTimeOut(),
                params.getGoodsType(),
                params.getBackParams(),
                params.getExtendParams(),
                params.getEnablePayChannels(),
                params.getDisablePayChannels(),
                params.getStoreId(),
                params.getPromoParams());
    }

    /**
     * 构建支付宝最终支付参数
     *
     * @param outTradeNo 商户唯一订单号
     * @param subject    商品的标题/交易标题/订单标题/订单关键字等
     * @param totalMoney 支付价格
     */
    public static String buildPayParams(String outTradeNo, String subject, String totalMoney) {
        return buildPayParams(outTradeNo, subject, totalMoney, "", "", "", "", "", "", "", "");
    }

    /**
     * 构建支付宝最终支付参数,包含所有参数.某个参数可传空，来完成不传某个参数的配置
     *
     * @param outTradeNo         商户唯一订单号
     * @param subject            商品的标题/交易标题/订单标题/订单关键字等
     * @param totalMoney         需要支付的价格
     * @param timeOut            超时时间 取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
     * @param goodsType          商品类型 0—虚拟类商品，1—实物类商品
     * @param backParams         回传参数
     * @param extendParams       <a href="https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.x7kkCI&treeId=204&articleId=105465&docType=1#kzcs">业务扩展参数</a>
     * @param enablePayChannels  <a href="https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.x7kkCI&treeId=204&articleId=105465&docType=1#qdsm">可用渠道</a>
     * @param disablePayChannels <a href="https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.x7kkCI&treeId=204&articleId=105465&docType=1#qdsm">禁用渠道</a>
     * @param storeId            商户门店编号
     * @param promoParams        优惠参数，仅与支付宝协商后可用
     */
    public static String buildPayParams(String outTradeNo, String subject, String totalMoney,
                                        String timeOut, String goodsType, String backParams,
                                        String extendParams, String enablePayChannels, String disablePayChannels, String storeId,
                                        String promoParams) {
        Map<String, String> mapValue = buildPublicMap();
        JSONObject jsonObject = buildContentValue(outTradeNo, totalMoney, subject);
        try {
            //非必填字段
            if (!TextUtils.isEmpty(timeOut)) {
                jsonObject.put("timeout_express", timeOut);
            }
            if (!TextUtils.isEmpty(SELLER_ID)) {
                jsonObject.put("SELLER_ID", SELLER_ID);
            }
            if (!TextUtils.isEmpty(goodsType)) {
                jsonObject.put("goods_type", goodsType);
            }
            if (!TextUtils.isEmpty(backParams)) {
                jsonObject.put("passback_params", URLEncoder.encode(backParams, "UTF-8"));
            }
            if (!TextUtils.isEmpty(extendParams)) {
                jsonObject.put("extend_params", extendParams);
            }
            if (!TextUtils.isEmpty(enablePayChannels)) {
                jsonObject.put("enable_pay_channels", enablePayChannels);
            }
            if (!TextUtils.isEmpty(disablePayChannels)) {
                jsonObject.put("disable_pay_channels", disablePayChannels);
            }
            if (!TextUtils.isEmpty(storeId)) {
                jsonObject.put("store_id", storeId);
            }
            if (!TextUtils.isEmpty(promoParams)) {
                jsonObject.put("promo_params", promoParams);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mapValue.put("biz_content", jsonObject.toString());
        String params = buildPayParams(mapValue);
        String sign = buildPaySign(mapValue);

        return params + "&" + sign;
    }


    /**
     * 构造公共pay参数
     */
    private static Map<String, String> buildPublicMap() {
        if (TextUtils.isEmpty(APP_ID) || (TextUtils.isEmpty(RSA_PRIVATE) && TextUtils.isEmpty(RSA2_PRIVATE))) {
            throw new IllegalArgumentException("APP_ID或者签名密钥为空");
        }
        Map<String, String> keyValues = new HashMap<>();
        keyValues.put("app_id", APP_ID);
        keyValues.put("method", "alipay.trade.app.pay");
        keyValues.put("charset", "UTF-8");
        keyValues.put("timestamp", getPayTime());
        keyValues.put("version", "1.0");
        if (!TextUtils.isEmpty(NOTIFY_URL)) {
            keyValues.put("notify_url", NOTIFY_URL);
        }
        boolean isRSA2 = RSA2_PRIVATE.length() > 0;
        keyValues.put("sign_type", isRSA2 ? "RSA2" : "RSA");
        return keyValues;
    }

    /**
     * 构造业务请求参数
     *
     * @param subject 商品的标题/交易标题/订单标题/订单关键字等。
     */
    private static JSONObject buildContentValue(String outTradeNo, String totalMoney, String subject) {
        JSONObject params = new JSONObject();
        try {
            params.put("out_trade_no", outTradeNo);
            params.put("subject", subject);
            params.put("total_amount", totalMoney.toString());
            params.put("product_code", PRODUCT_CODE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     * 将map参数生成字符参数，以&连接
     */
    private static String buildPayParams(Map<String, String> mapValue) {
        List<String> keys = new ArrayList<>(mapValue.keySet());

        StringBuilder sb = new StringBuilder();
        //拿到每一个参数
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = mapValue.get(key);
            sb.append(spliceKV(key, value, true));
            sb.append("&");
        }
        if(sb.length()>0){
            //删除最后一个&
            sb.delete(sb.length() - 1, sb.length());
        }
        return sb.toString();
    }

    /**
     * 生成支付签名
     */
    private static String buildPaySign(Map<String, String> mapValue) {

        List<String> keys = new ArrayList<>(mapValue.keySet());
        // key排序
        Collections.sort(keys);

        StringBuilder authInfo = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = mapValue.get(key);
            authInfo.append(spliceKV(key, value, false));
            authInfo.append("&");
        }
        if (authInfo.length() > 0) {
            //删除最后一个&
            authInfo.delete(authInfo.length() - 1, authInfo.length());
        }

        boolean isRSA2 = RSA2_PRIVATE.length() > 0;
        String oriSign;
        if (isRSA2) {
            oriSign = SignUtil.sign(authInfo.toString(), RSA2_PRIVATE, true);
        } else {
            oriSign = SignUtil.sign(authInfo.toString(), RSA_PRIVATE, false);
        }
        String encodedSign = "";
        try {
            encodedSign = URLEncoder.encode(oriSign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "sign=" + encodedSign;
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
     * 获取支付请求时间
     * 格式：yyyy-MM-dd HH:mm:ss
     */
    private static String getPayTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_STYLE);
        return simpleDateFormat.format(new Date());
    }

    public static void setAppId(String appId) {
        APP_ID = appId;
    }

    public static void setNotifyUrl(String notifyUrl) {
        NOTIFY_URL = notifyUrl;
    }

    public static void setRsa2Private(String rsa2Private) {
        RSA2_PRIVATE = rsa2Private;
    }

    public static void setRsaPrivate(String rsaPrivate) {
        RSA_PRIVATE = rsaPrivate;
    }

    public static void setSellerId(String sellerId) {
        SELLER_ID = sellerId;
    }
}
