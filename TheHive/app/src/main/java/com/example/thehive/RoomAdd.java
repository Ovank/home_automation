package com.example.thehive;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class RoomAdd extends ListActivity {


    private ListView listView;

    private ArrayAdapter<String> sAdapter;

    EditText room_name;

    public ArrayList<String> roomList = new ArrayList<String>();

    String Rname,filename="ROOM.tex";

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_add);


        room_name = findViewById(R.id.room_name);
        listView = getListView();
        sAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, roomList);
        listView.setAdapter(sAdapter);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Rname = room_name.getText().toString();
                if(Rname!="") {
                    roomList.add(Rname);
                    sAdapter.notifyDataSetChanged();
                    room_name.setText(null);
                    Toast.makeText(getBaseContext(), "Room Added", Toast.LENGTH_SHORT).show();

                    //saving in the file.
                    FileOutputStream outputStream;
                    Rname=Rname+"$";
                    try {
                        outputStream = openFileOutput(filename, Context.MODE_APPEND);
                        outputStream.write(Rname.getBytes());
                        outputStream.close();

                        // Toast.makeText(MainActivity.this,"saved",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();

                        //Toast.makeText(MainActivity.this,"not saved",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void next(View view){

        Intent i = new Intent(RoomAdd.this,HomePageSetup.class);
        //i.putExtra("array_list",roomList);
        startActivity(i);
    }
    }


