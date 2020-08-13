package com.abheisenberg.messagereader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.abheisenberg.messagereader.api.API;
import com.abheisenberg.messagereader.api.MessagesAPI;
import com.abheisenberg.messagereader.base.BaseActivity;
import com.abheisenberg.messagereader.broadcasts.SMSBroadcastReceiver;
import com.abheisenberg.messagereader.sms.SMS;
import com.abheisenberg.messagereader.sms.SMSAdapter;
import com.abheisenberg.messagereader.sms.SampleJSON;
import com.abheisenberg.messagereader.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener, SMSBroadcastReceiver.SMSListener {

    public static final String TAG = "MainActivity";

    public static final int READ_SMS_PERMISSION = 123;

    //Views
    RecyclerView rv_sms_list;
    ArrayList<SMS> inboxSMS;
    SMSAdapter smsAdapter;
    RelativeLayout rl_no_permission;

    //For sending and receiving data to/from server
    MessagesAPI messagesAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messagesAPI = API.getInstance().getMessagesAPI();

        setViews();
        checkPermission();
    }

    private void setViews() {
        rv_sms_list = findViewById(R.id.rv_sms_list);
        rl_no_permission = findViewById(R.id.layout_no_perm);

        rl_no_permission.setOnClickListener(this);
    }

    //If permission if denied, show "give permission" view
    private void permissionDeniedView(){
        rl_no_permission.setVisibility(View.VISIBLE);
        rv_sms_list.setVisibility(View.INVISIBLE);
    }

    //If permission if granted, show list of messages
    private void permissionGrantedView(){
        rl_no_permission.setVisibility(View.INVISIBLE);
        rv_sms_list.setVisibility(View.VISIBLE);
    }

    //Checking and asking for permission
    private void checkPermission(){
        boolean hasPermission = true;
        String[] permissions = { Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS};
        for(String permission: permissions){
            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                hasPermission = false;
            }
        }
        if(!hasPermission){
            ActivityCompat.requestPermissions(this, permissions, READ_SMS_PERMISSION);
        } else{
            permissionGrantedView();
            parseInboxSMS();
        }
    }

    //Results of permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case READ_SMS_PERMISSION:
                if(permissions.length == 0) return;

                boolean allGranted = true;
                for(int i=0; i<permissions.length; i++){
                    if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                        allGranted = false;
                    }
                }

                if(!allGranted){
                    permissionDeniedView();
                } else {
                    permissionGrantedView();
                    parseInboxSMS();
                }

                break;
        }
    }

    /*
     Listen to live incoming SMS, and add them into the list.
    */
    @Override
    public void onSMSReceived(SMS message) {
        initListAndAdapter();

        TransitionManager.beginDelayedTransition(rv_sms_list);
        inboxSMS.add(0, message);
        smsAdapter.updateList(inboxSMS);
    }

    /*
    This method iteretes through the user's sms inbox and finds non-personal transactional messages,
    parse out the amount from them (these methods are written in Utility.java class), and
    creates a list of such messages. Then, sends them to the server.

    Since the APIs are not in place, the method is left with a dummy boilerplate.
     */
    private void parseInboxSMS() {
        SMSBroadcastReceiver.setListener(this);

        Uri uriSms = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(uriSms, new String[]{"_id", "address", "date", "body"},null,null,null);

        initListAndAdapter();

        //Reads upto 1000 sms from the user's inbox.
        int i = 0;
        cursor.moveToFirst();
        while  (cursor.moveToNext() && i < 1000)
        {
            String sender = cursor.getString(1);
            String epoch = cursor.getString(2);
            String body = cursor.getString(3);

            SMS sms = Utility.parseCommercialSMSOnly(sender, body, epoch);
            if(sms != null){
                inboxSMS.add(sms);
            }
            i++;
        }
        cursor.close();

        /*
        Load dummy data after the local data, in case there is no sms present in the user's phone.
         */
        loadDataFromServer();

        smsAdapter.updateList(inboxSMS);

        /*
        After loading and parsing the data, save the processed data to the server.
         */
        sendDataToServer();
    }

    private void loadDataFromServer() {
        /*
        Here we hit the API end point to bring the data,
        but currently we are DISPLAYING THE SAMPLE DATA ONLY.

        Callback<ArrayList<SMS>> msgCallback = new Callback<ArrayList<SMS>>() {
            @Override
            public void onResponse(Call<ArrayList<SMS>> call, Response<ArrayList<SMS>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<SMS>> call, Throwable t) {
            }
        };
         */

        //Display sample data in place of API data
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<SMS>>(){}.getType();
        ArrayList<SMS> smsList = gson.fromJson(SampleJSON.sample, type);

        initListAndAdapter();
        inboxSMS.addAll(smsList);
    }

    private void sendDataToServer() {
        /*
        Here we hit the API endpoint to send  data to the server .

        Callback<ArrayList<SMS>> msgCallback = new Callback<ArrayList<SMS>>() {
            @Override
            public void onResponse(Call<ArrayList<SMS>> call, Response<ArrayList<SMS>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<SMS>> call, Throwable t) {
            }
        };
         */
    }

    private void initListAndAdapter(){
        if(inboxSMS == null)
            inboxSMS = new ArrayList<>();

        if(smsAdapter == null){
            smsAdapter = new SMSAdapter(this, inboxSMS);
            rv_sms_list.setAdapter(smsAdapter);
            rv_sms_list.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_no_perm:
                checkPermission();
                break;
        }
    }

}
