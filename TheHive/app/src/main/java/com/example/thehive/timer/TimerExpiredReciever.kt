package com.example.thehive.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //TODO: show notification

        PrefUtil.setTimerState(Timer.TimerState.Stopped, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}