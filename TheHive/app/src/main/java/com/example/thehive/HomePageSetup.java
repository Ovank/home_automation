package com.example.thehive;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thehive.device_setup.ApplianceMenu;
import com.example.thehive.device_setup.Device;
import com.example.thehive.device_setup.DeviceAdd;
import com.example.thehive.device_setup.DeviceShow;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class HomePageSetup extends ListActivity {

    ArrayList< RoomData > mRoomList=new ArrayList<>();
    ArrayList<String> roomList=new ArrayList<String>();
    int imageid=0;
    String name="";
    ListView l1;
    SharedPreferences check;
    final String file="state";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagesetup);




        //run for the first time.

        check=getSharedPreferences(file, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=check.edit();

        if(!check.getBoolean(file,false))
        {
            editor.putBoolean(file,true);
            editor.commit();


            Intent intent=new Intent(this,RoomAdd.class);
            startActivity(intent);

            Log.d("log-info","\ninside the override\n" );
        }
        else {
            String filename = "ROOM.tex";

            String line;
            int number = 0;

                try {

                    FileInputStream read = openFileInput(filename);
                    int sizea = read.available();
                    byte[] buffer = new byte[sizea];
                    read.read(buffer);
                    line = new String(buffer);

                    Log.d("log-info", "  " + line + "\n");
                    String mess = "";
                    int length = line.length(), k = 0;

                    String lengtha = Integer.toString(length);

                    Log.d("log-info + length", lengtha + "\n");

                    while (k != length) {

                        String q = Character.toString(line.charAt(k));
                        final String p = "$";

                       // Log.d("log-info + char", Integer.toString(k) + " " + q + "\n");
                        k++;
                        if (p.equals(q)) {
                            Log.d("log-info", "  " + mess + "\n");
                            number++;
                            roomList.add(mess);
                            mess = "";
                        } else {
                            mess = mess + q;
                        }
                    }

                } catch (IOException e) {
                    Log.d("Error", "not found");
                    e.printStackTrace();

                }
            Log.d("log-info","list of room " +Integer.toString(number)+"\n");
                //load image.
            l1=getListView();
            int i=0;
            for (i=0;i<number;i++)
            {
                name=roomList.get(i);

               // Log.d("log-info",name +" "+roomList.get(i+1)+" "+roomList.get(i+2)+" "+ Integer.toString(i) + "\n");

                Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
                if(name.contains("bedroom")||name.contains("Bedroom"))
                    imageid=R.drawable.bed;
                if(name.contains("bathroom")||name.contains("Bathroom"))
                    imageid=R.drawable.shower;
                if(name.contains("kitchen")||name.contains("Kitchen"))
                    imageid=R.drawable.cooking;
                if(name.contains("living room")||name.contains("Living Room"))
                    imageid=R.drawable.room;
                if(name.contains("dining area")||name.contains("Dining Area"))
                    imageid=R.drawable.dinner;
                mRoomList.add(new RoomData(name,imageid));
                Log.d("log-info","enter");
            }

            //add device in menu.

            RoomAdapter myAdapter = new RoomAdapter(HomePageSetup.this, mRoomList);
            // mRecyclerView.setAdapter(myAdapter);
            l1.setAdapter(myAdapter);

            l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {

                    String selectedroom = mRoomList.get(position).getRoomName();

                    Log.d("room name",selectedroom);

                    Intent intent = new Intent(HomePageSetup.this, DeviceAdd.class);
                    Bundle args = new Bundle();
                    args.putSerializable("Room Data", mRoomList.get(position));
                    intent.putExtra("Room BUNDLE",args);
                    intent.putExtra("roomname",selectedroom);
                    startActivity(intent);
                }
            });
        }



    }
}

