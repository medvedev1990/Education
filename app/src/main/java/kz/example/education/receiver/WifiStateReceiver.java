package kz.example.education.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WifiStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("I RECEIVED MESSAGE");
    }
}
