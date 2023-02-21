package me.zhangjh.wx.program;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.zhangjh.wx.program.vo.Response;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author njhxzhangjihong@126.com
 * @date 2:56 PM 2022/12/12
 * @Description
 */
@Slf4j
public class HttpClientUtil {

    private static final RequestConfig DEFAULT_REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(5000)
            .setSocketTimeout(60000)
            .setConnectionRequestTimeout(5000)
            .build();

    /** 每个域名10个，总共100个 */
    private static final CloseableHttpClient HTTPCLIENT = HttpClients.custom()
            .setDefaultRequestConfig(DEFAULT_REQUEST_CONFIG)
            .setMaxConnPerRoute(10)
            .setMaxConnTotal(100)
            .build();

    /**
     * @param url ：请求地址
     * @param body ：请求内容，json字符串
     */
    public static void sendHttpPost(String url, String body) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(body,
                ContentType.create(ContentType.APPLICATION_JSON.getMimeType(),
                        Charset.defaultCharset()));
        httpPost.setEntity(entity);
        HttpResponse response = null;
        log.info("sendHttpPost: {}", JSONObject.toJSONString(httpPost));
        try {
            response = HTTPCLIENT.execute(httpPost);
            log.info("http response: {}", JSONObject.toJSONString(response));
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject jo = JSONObject.parseObject(result);
                Boolean success = jo.getBoolean("success");
                String errorMsg = jo.getString("errorMsg");
                Assert.isTrue(success, "post失败:" + errorMsg);
            }
        } finally {
            if(null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static Response<String> sendHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;
        try {
            response = HTTPCLIENT.execute(httpGet);
            log.info("http response: {}", JSONObject.toJSONString(response));
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String res = EntityUtils.toString(response.getEntity());
                JSONObject jsonObject = JSONObject.parseObject(res);
                if(jsonObject.get("errcode") != null) {
                    return Response.fail(jsonObject.get("errmsg").toString());
                }
                return Response.success(res);
            }
            throw new RuntimeException("get失败");
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }finally {
            if(null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ignored) {
                }
            }
        }
    }
}
