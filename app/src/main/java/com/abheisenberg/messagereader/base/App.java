package com.abheisenberg.messagereader.base;

import android.app.Application;
import android.content.IntentFilter;
import android.provider.Telephony;

import com.abheisenberg.messagereader.broadcasts.SMSBroadcastReceiver;

public class App extends Application {
    private SMSBroadcastReceiver smsBroadcastReceiver;

    /*
    A base application to register broadcast listener.
     */

    @Override
    public void onCreate() {
        super.onCreate();
        smsBroadcastReceiver = new SMSBroadcastReceiver();
        registerReceiver(smsBroadcastReceiver, new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION));
    }

    @Override
    public void onTerminate() {
        unregisterReceiver(smsBroadcastReceiver);
        super.onTerminate();
    }
}
