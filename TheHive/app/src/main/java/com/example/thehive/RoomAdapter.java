package com.example.thehive;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thehive.device_setup.Device;
import com.example.thehive.device_setup.DeviceAdd;

import java.util.ArrayList;
import java.util.List;

/*public class RoomAdapter extends RecyclerView.Adapter< RoomViewHolder > {

    private Context mContext;
    private List< RoomData > mRoomList;

    RoomAdapter(Context mContext, List< RoomData > mRoomList) {
        this.mContext = mContext;
        this.mRoomList = mRoomList;
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_element, parent, false);
        return new RoomViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final RoomViewHolder holder, int position) {
        holder.mImage.setImageResource(mRoomList.get(position).getRoomImage());
        holder.mTitle.setText(mRoomList.get(position).getRoomName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), DeviceAdd.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mRoomList.size();
    }
}

class RoomViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;

    RoomViewHolder(View itemView) {
        super(itemView);
        itemView.setTag(this);
        mImage = itemView.findViewById(R.id.roomImage);
        mTitle = itemView.findViewById(R.id.roomtext);
    }
} */



   public class RoomAdapter extends ArrayAdapter<RoomData> {
    private Context mContext;
    private List<RoomData> RoomList;

    public RoomAdapter(@NonNull Context context, List<RoomData> list) {
        super(context, 0, list);
        mContext = context;
        RoomList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.homepage_element, parent, false);

        RoomData currentRoom = RoomList.get(position);
        ImageView display_room = listItem.findViewById(R.id.roomImage);
        display_room.setImageResource(currentRoom.getRoomImage());

        TextView display_rname = listItem.findViewById(R.id.roomtext);
        display_rname.setText(currentRoom.getRoomName());
        return listItem;
    }
}