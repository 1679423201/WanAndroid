package com.example.wanandroid;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * 隐藏标题
         */
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        textView = (TextView) findViewById(R.id.list_main);
        Button button = (Button)findViewById(R.id.btn_search);
        button.setOnClickListener(this);

}
    @Override
    public void onClick(View view) {
//        textView.setBackground(getDrawable(R.drawable.search));
        sendRequestWithHttpURLConnection();
        Toast.makeText(MainActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
    }
        /**
         * 从网站获取数据
         * 需要设置无法联网的情况
         */
        private void sendRequestWithHttpURLConnection() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection connection = null;
                    BufferedReader reader = null;
                    try {
                        URL url = new URL("https://www.wanandroid.com/article/list/1/json");
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setConnectTimeout(8000);
                        connection.setReadTimeout(8000);
                        InputStream in = connection.getInputStream();
//                        对获取到的输入流进行读取
                        reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();   //新建一个字符串变量
                        String line;
                        while((line = reader.readLine()) != null){
                            response.append(line);
                        }
                        showResponse(response.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        if(reader != null){
                            try {
                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(connection != null){
                            connection.disconnect();
                        }
                    }
                }
            }).start();
        }
    private void showResponse(final String response){
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                //在这里进行UI操作
            textView.setText(response);
            }
        });
    }


}
