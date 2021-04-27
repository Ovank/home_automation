package com.example.thehive.device_setup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.thehive.R;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter extends ArrayAdapter<Device> {
    private Context mContext;
    private List<Device> DeviceList = new ArrayList<>();

    public DeviceAdapter(@NonNull Context context, ArrayList<Device> list) {
        super(context, 0, list);
        mContext = context;
        DeviceList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.devices_view, parent, false);

        Device currentDevice = DeviceList.get(position);
        TextView display_ip = listItem.findViewById(R.id.display_ip);
        display_ip.setText(currentDevice.getIp());

        TextView display_gpio = listItem.findViewById(R.id.display_gpio);
        display_gpio.setText(Integer.toString(currentDevice.getGpio()));

        TextView display_name = listItem.findViewById(R.id.display_name);
        display_name.setText(currentDevice.get_Name());

        ImageView ofdevice = listItem.findViewById(R.id.deviceImage);
        ofdevice.setImageResource(currentDevice.getImageid());

        return listItem;
    }
}
