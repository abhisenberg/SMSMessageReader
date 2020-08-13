package com.abheisenberg.messagereader.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.abheisenberg.messagereader.sms.SMS;
import com.abheisenberg.messagereader.utils.Utility;

public class SMSBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "SMSBroadcastRec";

    private static SMSListener smsListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

            String sender = "", body = "", epoch = "";

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                for (SmsMessage smsMessage: Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                    sender =  smsMessage.getDisplayOriginatingAddress();
                    body = smsMessage.getMessageBody();
                    epoch = String.valueOf(smsMessage.getTimestampMillis());
                }
            } else {
                Bundle smsBundle = intent.getExtras();
                if(smsBundle != null){
                    Object[] pdus = (Object[]) smsBundle.get("pdus");
                    if(pdus == null){
                        Log.d(TAG, "onReceive: No PDU");
                        return;
                    }

                    SmsMessage[] smsMessages = new SmsMessage[pdus.length];
                    for(int i=0; i< smsMessages.length; i++){
                        smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        body = smsMessages[i].getMessageBody();
                    }
                    sender = smsMessages[0].getOriginatingAddress();
                    epoch = String.valueOf(smsMessages[0].getTimestampMillis());

                }
            }

            SMS message = Utility.parseCommercialSMSOnly(sender, body, epoch);

            if(smsListener != null && message != null){
                smsListener.onSMSReceived(message);
            }
        }
    }

    public interface SMSListener {
        void onSMSReceived(SMS message);
    }

    public static void setListener(SMSListener listener){
        smsListener = listener;
    }
}
