package com.godzilla.cn.godzilla.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class HttpClient {
    private static Logger log = LoggerFactory.getLogger(HttpClient.class);

    private HttpClient() {

    }

    /**
     * post请求
     *
     * @param jobj
     * @return
     */
    public static String postRequest(String url, JSONObject jobj) {
        String smsUrl = url;
        String strResult = "";
        CloseableHttpClient httpclient = null;

        try {
            //创建httpclient对象
            httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(smsUrl);
            StringEntity s = new StringEntity(jobj.toString(),Charset.forName("UTF-8"));  //对参数进行编码，防止中文乱码
            s.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            s.setContentType("application/json");
            httppost.setEntity(s);
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                /*读返回数据*/
                String conResult = EntityUtils.toString(response.getEntity());
                strResult = conResult;
//                System.out.println(conResult);
//                JSONObject sobj = new JSONObject();
//                sobj = JSONObject.parseObject(conResult);
//                String result = sobj.getString("result");
//                String code = sobj.getString("code");
//                if (code.equals("500")) {
//                    System.out.println(result);
//                    strResult += "发送成功";
//                } else {
//                    strResult += "发送失败，" + code;
//                }

            } else {
                strResult = "fail";
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                log.info(e.getMessage());
            }
        }

        return strResult;
    }


    /**
     * 发送 get请求
     */
    public static String getRequest(String url) {

        String content = "";
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            RequestConfig requestConfig = RequestConfig.custom()
                    //设置连接超时时间
                    .setConnectTimeout(3000)
                    // 设置请求超时时间
                    .setConnectionRequestTimeout(3000)
                    .setSocketTimeout(3000)
                    //默认允许自动重定向
                    .setRedirectsEnabled(true)
                    .build();
            // 创建httpget.
            HttpGet httpget = new HttpGet(url);
            httpget.setConfig(requestConfig);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 打印响应状态
            if (entity != null) {
                content = EntityUtils.toString(entity, "UTF-8");
            }
            response.close();
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            // 关闭连接,释放资源
            try {
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        return content;
    }


    /**
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    /*
    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }*/

}
