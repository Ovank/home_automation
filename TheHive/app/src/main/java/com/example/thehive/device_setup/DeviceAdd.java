package com.example.thehive.device_setup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.thehive.R;
import com.example.thehive.RoomAdd;
import com.example.thehive.RoomData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DeviceAdd extends AppCompatActivity {

    //private ListView listView;
    //private DeviceAdapter dAdapter;
    EditText dev_ip;
    EditText dev_gpio;
    EditText dev_name;
    //ArrayList<Device> deviceList = new ArrayList<>();
    String ip;
    String name,roomfile,namea,ipa;
    int gpio,imageid,gpioa,imagea;
    Button show;
    RoomData room;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_device_add);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("Room BUNDLE");
        room = (RoomData) args.getSerializable("Room Data");

        Bundle sec=getIntent().getExtras();
        roomfile=sec.getString("roomname");
        Log.d("device-page",roomfile);


        show= findViewById(R.id.addBtn);
        dev_ip = findViewById(R.id.dev_ip);
        dev_gpio = findViewById(R.id.dev_gpio);
        dev_name = findViewById(R.id.dev_name);


        String line;
        int number = 0;

        try {

            FileInputStream read = openFileInput(roomfile);
            int sizea = read.available();
            byte[] buffer = new byte[sizea];
            read.read(buffer);
            line = new String(buffer);

            Log.d("device-page", "  " + line + "\n");
            String mess = "";
            int length = line.length(), k = 0;

            String lengtha = Integer.toString(length);

            Log.d("device-page + length", line + "\n");

            while (k != length) {

                String q = Character.toString(line.charAt(k));
                final String p = "$",x="#";

                Log.d("device-page + char", Integer.toString(k) + " " + q + "\n");
                k++;
                if (p.equals(q)) {
                    Log.d("device-page", "  " + mess + "\n");

                    if(number%4==0){
                        namea=mess;
                    }
                    else if(number%4==1){
                        ipa=mess;
                    }
                    else if(number%4==2)
                    {
                        gpioa=Integer.parseInt(mess);
                    }
                    else
                    {
                        imagea=Integer.parseInt(mess);
                    }

                    mess = "";
                    number++;
                }
                else if(x.equals(q))
                {
                    room.deviceList.add(new Device(namea,ipa,gpioa,imagea));
                    mess="";
                }else {
                    mess = mess + q;
                }
            }

        } catch (IOException e) {
            Log.d("Error", "not found");
            e.printStackTrace();

        }


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("system","button preseed");
                ip = dev_ip.getText().toString();
                name = dev_name.getText().toString();
                gpio = Integer.parseInt(dev_gpio.getText().toString());

                if(name.contains("fan")||name.contains("Fan"))
                    imageid=R.drawable.fan;
                if(name.contains("AC")||name.contains("A.C.")||name.contains("air conditioner")||name.contains("Air Conditioner")||name.contains("ac"))
                    imageid=R.drawable.air_conditioner;
                if(name.contains("bulb")||name.contains("Bulb"))
                    imageid=R.drawable.led;
                if(name.contains("Overhead Lamp")||name.contains("Ceiling Lamp"))
                    imageid=R.drawable.lamp;
                if(name.contains("free socket")||name.contains("Free Socket"))
                    imageid=R.drawable.plug;
                if(name.contains("Table Lamp")||name.contains("table lamp"))
                    imageid=R.drawable.table_lamp_clipart;

              room.deviceList.add(new Device(name,ip,gpio,imageid));
                //dAdapter.notifyDataSetChanged();

                Log.d("device-page image",Integer.toString(imageid));

                //saving in the file.
                FileOutputStream outputStream;
                String Rname=name+"$"+ip+"$"+Integer.toString(gpio)+"$"+Integer.toString(imageid)+"$"+"#";
                try {
                    outputStream = openFileOutput(roomfile, Context.MODE_APPEND);
                    outputStream.write(Rname.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();

                }

                dev_gpio.setText(null);
                dev_name.setText(null);
                Toast.makeText(getBaseContext(),"Device Added",Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void proceed(View view)
    {

        Intent intent = new Intent(DeviceAdd.this, DeviceShow.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", room.deviceList);
        intent.putExtra("BUNDLE",args);
        startActivity(intent);
    }
}
