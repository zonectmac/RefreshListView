package com.example.administrator.listviewrefresh.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.listviewrefresh.R;
import com.example.administrator.listviewrefresh.engine.Engine;
import com.example.administrator.listviewrefresh.model.LoginModel;
import com.example.administrator.listviewrefresh.model.LoginModelCommit;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Login extends BaseActiivty
{
    private Button button;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_post);
    }

    @Override
    protected void setListener() {
        button = getViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                LoginModelCommit lmn=  new LoginModelCommit("hot","123456","1");
                Gson gson=new Gson();
               String route= gson.toJson(lmn);
                Log.i("TAG3",route);
                //增加日志信息
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .addInterceptor(httpLoggingInterceptor)
                        .build();
                mEngine = new Retrofit.Builder()
                        .baseUrl("http://190.168.1.19/")
                        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                        .build().create(Engine.class);
//                RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
                mEngine.getData(route).enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        LoginModel lm=response.body();
                        Log.i("TAG",lm.toString());
                        button.setText(lm.toString());
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        Log.e("TAG2",t.getMessage());
                    }
                });
                break;
        }
    }
}
