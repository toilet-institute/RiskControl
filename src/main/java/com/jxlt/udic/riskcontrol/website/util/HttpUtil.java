package com.jxlt.udic.riskcontrol.website.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HttpUtil {

    private static int connectime;

    @Value("${connectsetting.time}")
    public void setConnectime(int time) {
        connectime = time;
    }

    //	 public static String sendPost(String url, String param) {
    //	        PrintWriter out = null;
    //	        BufferedReader in = null;
    //	        String result = "";
    //	        try {
    //	            URL realUrl = new URL(url);
    //	            // 打开和URL之间的连接
    //	            URLConnection conn = realUrl.openConnection();
    //	            // 设置通用的请求属性
    //	            conn.setRequestProperty("Content-Type", "multipart/form-data");
    //	            conn.setRequestProperty("connection", "Keep-Alive");
    //	            conn.setRequestProperty("Charset", "UTF-8");
    //	            // 发送POST请求必须设置如下两行
    //	            conn.setDoOutput(true);
    //	            conn.setDoInput(true);
    //	            // 获取URLConnection对象对应的输出流
    //	            out = new PrintWriter(conn.getOutputStream());
    //	            // 发送请求参数
    //	            out.print(param);
    //	            // flush输出流的缓冲
    //	            out.flush();
    //	            // 定义BufferedReader输入流来读取URL的响应
    //	            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    //	            String line;
    //	            while ((line = in.readLine()) != null) {
    //	                result += line;
    //	            }
    //	        } catch (Exception e) {
    //	            log.info("发送 POST 请求出现异常！"+e);
    //	            e.printStackTrace();
    //	        }
    //	        //使用finally块来关闭输出流、输入流
    //	        finally{
    //	            try{
    //	                if(out!=null){
    //	                    out.close();
    //	                }
    //	                if(in!=null){
    //	                    in.close();
    //	                }
    //	            }
    //	            catch(IOException ex){
    //	                ex.printStackTrace();
    //	            }
    //	        }
    //	        return result;
    //	    }

    public static String sendPost(String url, Map<String, Object> params) throws Exception {
        URL realUrl = new URL(url);
        // 开始访问
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString()
            .getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        log.info("设置等待时间为：" + connectime + "S");
        conn.setConnectTimeout(connectime * 1000);//连接超时时间30s
        conn.setReadTimeout(connectime * 1000);//读取数据超时时间30s
        conn.getOutputStream()
            .write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0; ){
            sb.append((char)c);
        }
        String response = sb.toString();
        return response;

    }

    public static String sendPostByjson(String url, String jsonparams) throws Exception {
        URL realUrl = new URL(url);
        byte[] postDataBytes = jsonparams.getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        log.info("设置等待时间为：" + connectime + "S");
        conn.setConnectTimeout(connectime * 1000);//连接超时时间30s
        conn.setReadTimeout(connectime * 1000);//读取数据超时时间30s
        conn.getOutputStream()
            .write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0; ){
            sb.append((char)c);
        }
        String response = sb.toString();
        return response;

    }

    /**
     * 通过HTTPCLIENT发送GET请求
     * @param url
     * @param params
     * @@author:NCPLT
     */
    public static String sendGet(String url,Map<String, String> params) {
//        HttpClient httpclient = new DefaultHttpClient();
        HttpClient httpclient = HttpUtil.createDefault();
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");
        List<Map.Entry<String, String>> mapList = new ArrayList<>();
        mapList.addAll(params.entrySet());
        mapList.stream().forEach(e -> {
            try {
                sb.append(e.getKey()).append("=").append(URLEncoder.encode(e.getValue(),"UTF-8")).append("&");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        });
        HttpGet httpget = new HttpGet(sb.toString());
        String json = null;
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                json = EntityUtils.toString(entity, "UTF-8").trim();

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpget.abort();
        }
        return json;
    }

    /**
     * post请求（用于请求json格式的参数）
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params) throws Exception {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
            else{
                log.error("请求返回:"+state+"("+url+")");
            }
        }
        finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getGetUrlString(String urlfro,Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(urlfro).append("?");
        List<Map.Entry<String, String>> mapList = new ArrayList<>();
        mapList.addAll(params.entrySet());
        mapList.stream().forEach(e -> {
            try {
                sb.append(e.getKey()).append("=").append(URLEncoder.encode(e.getValue(),"UTF-8")).append("&");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
        });
        return sb.toString();
    }

    public static HttpClient createDefault() {
        return HttpClientBuilder.create().build();
    }
}
