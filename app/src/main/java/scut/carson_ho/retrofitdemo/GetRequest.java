package scut.carson_ho.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetRequest extends AppCompatActivity implements View.OnClickListener{
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();

    }

    private void setView(){
        tv = findViewById(R.id.textView);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.button:
                 request( 0);
                 break;
             case R.id.button2:
                 request( 1);
                 break;
             case R.id.button3:
                 request( 2);
                 break;
             case R.id.button4:
                 request( 3);
                 break;
             case R.id.button5:
                 request( 4);
                 break;
             case R.id.button6:
                 request( 5);
                 break;
             case R.id.button7:
                 request( 6);
                 break;
             case R.id.button8:
                 request( 7);
                 break;
             case R.id.button9:
                 request( 8);
                 break;


         }
    }

    public void request(int mySwitch) {

        //步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<Translation2> call = request.getCall(mySwitch);


        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation2>() {
            //请求成功时候的回调
            @Override
            public void onResponse(Call<Translation2> call, Response<Translation2> response) {
                //请求处理,输出结果

                Log.d("testRt", "yeah" + response.body().getMyResponse());
                tv.setText(response.body().getMyResponse());
            }

            //请求失败时候的回调
            @Override
            public void onFailure(Call<Translation2> call, Throwable throwable) {
                Log.d("testRt", "连接失败");
            }
        });
    }
}

