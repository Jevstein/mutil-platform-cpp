package com.im.sdk;

import com.example.sdkdemo.MainActivity;

public class ImsdkCallback {
    public ImsdkCallback(ImsdkNative imsdk_native, MainActivity activity){
        imsdk_native_ = imsdk_native;
        activity_ = activity;
    }

    private ImsdkNative imsdk_native_;
    private MainActivity activity_;

    /*以下由底层调用，api回调、通知、获取设备、app等相关信息*/

    //sdk api接口调用的回调入口
    public void onResponse(String jstr_key, String jstr_ack, String ext) {
        final String log = "imsdklog-: " + jstr_key + "-" + jstr_ack + " - " + ext;
        activity_.runOnUiThread(new Runnable() {
            public void run() {
                activity_.addText(log);
            }
        });
    }

    //获取设备imei
    public String getMobilePhoneImei() {
        return "mobile_phtone_imei_112233";
    }

    //获取设备型号
    public String getMobilePhoneModel() {
        return "mobile_phtone_model_112233";
    }

    //获取app版本号
    public String getAppVersion() {
        return "22.33.44_112233";
    }

    //获取网络类型
    /*
        0: 未知的网络类型
        1: 没有网络连接
        2: wifi
        3: 2G
        4: 3G
        5: 4G
        6: 5G
    */
    public int getNetType() {
        return 2;
    }

    //获取网络运营商名称
    public String getNetOperatorName(){
        return "中国联不通";
    }

    /*以下接口处理完成时，需要调用sdk的OnHttpResponse接口，将返回数据回传给sdk*/
    //http Get请求
    public boolean httpGet(long reqid, String url, String jstr_append_header, long timeout) {
        imsdk_native_.OnHttpResponse(0, reqid, 200, "HttpGet响应");
        return true;
    }

    //http Post请求
    public boolean httpPost(long reqid, String url, byte[] post_data, String jstr_append_header, long timeout) {
//        String s = new String(post_data);
//        System.out.println("My_data : " + s);
//        imsdk_native_.OnHttpResponse(1, reqid, 200, "HttpPost响应");

        String data = "{\"actionMsg\":{\"code\":0,\"message\":\"成功\"},\"data\":{\"ableFlag\":0,\"cacheSetting\":{\"1\":\"4320\"},\"cid\":\"1103234746952974336\",\"corpAccount\":\"txlyb5h1i4\",\"gcid\":\"833629691783938048\",\"heartTime\":30,\"host\":\"203.110.166.58\",\"port\":21203,\"sak\":\"WEAVER2016QWM10RG14LJY09HDK52ETEAMS\",\"seq\":\"1562666235\",\"sessionTimeOut\":3600,\"tokenStr\":\"jhRwNdZZp5xirQkcKAGkoWQdZAyAdfd8ZFfyg+Y/lJJhQaneNCWev44UcDXWWaecoQm5cGrQeyKigaq5/x05R6IZm9nNG5JIuimzRHVX/RcAlPYBwm1dXuZv5ziGXzGZoeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4qHhm8lnQCrioeGbyWdAKuKh4ZvJZ0Aq4g==\",\"uid\":\"5815186667656411846\",\"uuid\":\"5815186667842733628\"}}";
//        {
//            final String log = "httpPost-: " + data;
//            activity_.runOnUiThread(new Runnable() {
//                public void run() {
//                    activity_.addText(log);
//                }
//            });
//        }

        imsdk_native_.OnHttpResponse(1, reqid, 200, data);
        return true;
    }

    //http 上传文件请求
    public boolean httpUploadFile(long reqid, String url, String file_path, String jstr_append_header, long timeout) {
        imsdk_native_.OnHttpResponse(2, reqid, 200, "HttpUploadFile响应");
        return true;
    }
}
