package com.example.send;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public TextView tv;
    public TextView tv_output;
    public Button btn;
    public static String name;
    private static final String urls = "http://0.0.0.0:5000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tv=(TextView)findViewById(R.id.tv);
        tv_output=(TextView)findViewById(R.id.tv_output);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                name = tv.getText().toString();
                String url = "http://0.0.0.0:5000/";

                // AsyncTask를 통해 HttpURLConnection 수행.
                NetworkTask networkTask = new NetworkTask(url, null);
                networkTask.execute();
            }
        });

    }
        public class NetworkTask extends AsyncTask<Void, Void, String> {

            private String url;
            private ContentValues values;

            public NetworkTask(String url, ContentValues values) {

                this.url = url;
                this.values = values;
            }

            @Override
            protected String doInBackground(Void... params) {

                String result; // 요청 결과를 저장할 변수.
                RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
                result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
                tv_output.setText(s);
            }
        }
    }

