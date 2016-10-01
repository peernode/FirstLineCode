package com.example.httpurlconnectiontest_10;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xujunyang on 16/10/1.
 */
public class MainActivity extends Activity implements View.OnClickListener{
    public static final int SHOW_RESPONSE = 0;

    private Button sendRequest;
    private TextView responseText;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response = (String)msg.obj;
                    Log.d("URLConnection", "show response");
                    Log.d("URLConnection", response);
                    responseText.setText(response);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView)findViewById(R.id.response);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.send_request){
            sendRequestWithHttpURLConnection();
        }
    }

//    private void sendRequestWithHttpURLConnection(){
//        //开启线程来发送网络请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("URLConnection", "send request");
//                HttpURLConnection connection = null;
//                try{
//                    URL url = new URL("https://www.baidu.com");
//                    connection = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//
//                    InputStream in = connection.getInputStream();
//                    //下面对获取到的输入流进行读取
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    while((line = reader.readLine()) != null){
//                        response.append(line);
//                    }
//                    Message message = new Message();
//                    message.what = SHOW_RESPONSE;
//                    //将服务器返回的结果放到message中
//                    message.obj = response.toString();
//                    handler.sendMessage(message);
//                    Log.d("URLConnection", "11show response");
//                }catch (Exception e){
//                    e.printStackTrace();
//                }finally {
//                    if(connection != null){
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }

    private void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("http://www.baidu.com");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if(httpResponse.getStatusLine().getStatusCode() == 200){
                        HttpEntity entity = httpResponse.getEntity();
                        String response = EntityUtils.toString(entity, "utf-8");
                        Message message = new Message();
                        message.what = SHOW_RESPONSE;
                        message.obj = response.toString();
                        handler.sendMessage(message);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
