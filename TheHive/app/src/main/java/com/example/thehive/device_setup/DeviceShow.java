package com.example.thehive.device_setup;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.thehive.R;

import java.util.ArrayList;

public class DeviceShow extends ListActivity {

    private ListView listView1;
    private DeviceAdapter dAdapter1;
    ArrayList<Device> object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_show);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        object = (ArrayList<Device>) args.getSerializable("ARRAYLIST");
        listView1 = getListView();
        dAdapter1 = new DeviceAdapter(this, object);
        listView1.setAdapter(dAdapter1);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
               Intent intent = new Intent(DeviceShow.this, ApplianceMenu.class);
                Bundle args = new Bundle();
                args.putSerializable("Device Data", object.get(position));
               intent.putExtra("BUNDLE",args);
                startActivity(intent);
            }
        });

    }

    }

