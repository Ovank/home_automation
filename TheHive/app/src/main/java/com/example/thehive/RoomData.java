package com.example.thehive;




import com.example.thehive.device_setup.Device;
import java.io.Serializable;
import java.util.ArrayList;

public class RoomData implements Serializable {
        private String roomName;
        private int roomImage;
        public ArrayList<Device> deviceList;

        public RoomData(String flowerName,int flowerImage) {
            this.roomName = flowerName;
            this.roomImage = flowerImage;
            deviceList= new ArrayList<>();
        }

        public String getRoomName()
        {

            return roomName;
        }

        public int getRoomImage()
        {
            return roomImage;
        }

    }
