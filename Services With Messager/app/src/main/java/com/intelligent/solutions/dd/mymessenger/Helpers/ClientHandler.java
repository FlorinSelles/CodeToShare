package com.intelligent.solutions.dd.mymessenger.Helpers;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.intelligent.solutions.dd.mymessenger.Models.ServiceWorkToDo;

/**
 * Created by florin on 5/11/2015.
 */
public class ClientHandler extends Handler {

    private TextView soucerComponent;

    public ClientHandler(TextView soucerComponent){
        this.soucerComponent = soucerComponent;
    }

    @Override
    public void handleMessage(Message msg) {
        ServiceWorkToDo response = ServiceWorkToDo.values()[msg.what];
        String responseData = msg.getData().getString("responseData");

        switch (response){
            case TO_UPPER_CASE:
                this.soucerComponent.setText(responseData);
                break;
            case SERVICE_ENABLE:
                Toast.makeText(soucerComponent.getContext(), responseData, Toast.LENGTH_LONG).show();
                break;
        }
    }
}
