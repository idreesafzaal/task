package e.visiontech.luncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import e.visiontech.luncher.DataModels.WeatherDataModel;
import e.visiontech.luncher.Services.BatteryReciever;


public class MainActivity extends AppCompatActivity {

    private BatteryReciever batteryReciever=new BatteryReciever();
    private IntentFilter mIntentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);


    String data;

    WeatherDataModel weatherDataModel=new WeatherDataModel();
    TextView tvCity,tvCountry,tvTem,tvDescp;
    ImageView ivApps;

    Thread thread;


    List<String> stringList=new ArrayList<>();

    String[] datas ;
    int count=0;

    Gson gson=new Gson();


    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //here is the urls of the weather
        stringList.add("https://weather.bfsah.com/beijing");
        stringList.add("https://weather.bfsah.com/berlin");
        stringList.add("https://weather.bfsah.com/cardiff");
        stringList.add("https://weather.bfsah.com/edinburgh");
        stringList.add("https://weather.bfsah.com/london");
        datas=new String[6];


        seViews();

        setDataInViews();






    }

    private void setDataInViews()
    {


        // run thread here to get data from urls
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    handler.post(new Runnable() {
                        public void run()
                        {
                            if(count==5)
                            {
                                count=0;
                            }
                            else
                                {


                 Log.d("idrees",String.valueOf(count));

                 StringRequest MyStringRequest = new StringRequest(Request.Method.GET,stringList.get(count) , new Response.Listener<String>()
                 {
                     @Override
                     public void onResponse(String response)
                     {

                         data=response.toString();
                         Gson gson=new Gson();
                         weatherDataModel=gson.fromJson(response,WeatherDataModel.class);

                         tvCity.setText(weatherDataModel.getCity());
                         tvCountry.setText(weatherDataModel.getCountry());
                         tvTem.setText(weatherDataModel.getTemperature());
                         tvDescp.setText(weatherDataModel.getDescription());

                         count++;

                     }
                 }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                     @Override
                     public void onErrorResponse(VolleyError error) {
                         //This code is executed if there is an error.
                         if (null != error.networkResponse)
                         {
                             Log.d("Error.Response" + ": ", "Error Response code: " + error.networkResponse.statusCode);

                         }


                     }
                 }) ;
                 MyStringRequest.setRetryPolicy(new DefaultRetryPolicy(20000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                 RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
                 rQueue.add(MyStringRequest);



             }

                        }
                    });
                }
            };
            timer.scheduleAtFixedRate(task, 0, 20000);

    }

    private void seViews()
    {
        tvCity=findViewById(R.id.tvCityNmae);
        tvCountry=findViewById(R.id.tvCountryNmae);
        tvTem=findViewById(R.id.tvTemp);
        tvDescp=findViewById(R.id.tvDesp);
        ivApps=findViewById(R.id.appIv);
        ivApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent=new Intent(MainActivity.this,AppContainer.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume()
    {
       registerReceiver(batteryReciever,mIntentFilter);
        super.onResume();

    }

    @Override
    protected void onPause()
    {
      unregisterReceiver(batteryReciever);
        super.onPause();
    }






}