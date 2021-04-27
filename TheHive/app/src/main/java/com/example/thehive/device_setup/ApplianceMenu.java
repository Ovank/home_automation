package com.example.thehive.device_setup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thehive.R;
import com.example.thehive.timer.Timer;

import java.util.ArrayList;


public class ApplianceMenu extends AppCompatActivity {

    ToggleButton OnOFF;
    String urls1=null,qreply,murls;
     Device device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance_menu);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        device = (Device) args.getSerializable("Device Data");
        ImageView dev_image=findViewById(R.id.dev_im);
        dev_image.setImageResource(device.getImageid());
        TextView dev_name= findViewById(R.id.devnm);
        dev_name.setText(device.get_Name());

        OnOFF = findViewById(R.id.toggleButton);
        communicate(sendURL_status());
        setstate(qreply);

        OnOFF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                communicate(sendURL_toggle());
                setstate(qreply);//check
            }
        });
    }

    void onTimer(View view)
    {
        Intent i = new Intent(this, Timer.class);
        startActivity(i);//Passing appliance data left
    }

    String sendURL_status()
    {
        urls1=device.getIp() + "/?0"+Integer.toString(device.getGpio());
        return urls1;
    }
    String sendURL_toggle()
    {
        urls1=device.getIp() + "/?1"+Integer.toString(device.getGpio());
        return urls1;
    }

    public void communicate(String s) {
        Log.i("Info","Changing state of bulb");
        murls="http://"+urls1;
        talkNODEmcu(murls);
    }

    public void setstate (String responce)
        {
            if(responce=="404")
            {

                Toast.makeText(ApplianceMenu.this,"Network Error...",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(ApplianceMenu.this,responce, Toast.LENGTH_SHORT).show();
//                if(responce.equalsIgnoreCase("on"))
  //                  OnOFF.setChecked(true);
    //            if(responce.equalsIgnoreCase("off"))
      //              OnOFF.setChecked(false);
            }
        }

    public void talkNODEmcu(String urls) { //make tcp communication with nodemcu

        if (urls != null) {

            Log.i("Info","url:" + urls);
            final RequestQueue requestQueue = Volley.newRequestQueue(ApplianceMenu.this);
            StringRequest stringRequest = new StringRequest(Request.Method.GET, urls,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            qreply = response;
                            Log.i("Server message", qreply);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    qreply = "404";
                    error.printStackTrace();
                }
            });
            requestQueue.add(stringRequest);
        }
    }//talk nodemcu ends

}
