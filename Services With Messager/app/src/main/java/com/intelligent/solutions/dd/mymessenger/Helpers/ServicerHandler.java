package com.intelligent.solutions.dd.mymessenger.Helpers;

import android.os.Bundle;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.intelligent.solutions.dd.mymessenger.BindService;
import com.intelligent.solutions.dd.mymessenger.Models.ServiceWorkToDo;
import android.os.Handler;


/**
 * Created by florin on 5/11/2015.
 */
public class ServicerHandler extends Handler {

    BindService service;

    public ServicerHandler(BindService service){
        this.service = service;
    }

    public void handleMessage(Message msg) {
        ServiceWorkToDo work = ServiceWorkToDo.values()[msg.what];
        String responseData = null;
        ServiceWorkToDo response = null;

        switch(work) {
            case TO_UPPER_CASE:
                String data = msg.getData().getString("data");
                responseData = service.toUpperCase(data);
                response = ServiceWorkToDo.TO_UPPER_CASE;
            break;
            case SERVICE_ENABLE:
                    responseData = "Service is enabled";
                    response = ServiceWorkToDo.SERVICE_ENABLE;
                break;
            default:
                super.handleMessage(msg);
        }

        Bundle bResponse = new Bundle();
        bResponse.putString("responseData", responseData);

        Message mResponse = Message.obtain(null, response.ordinal());
        mResponse.setData(bResponse);

        try {
            msg.replyTo.send(mResponse);
        } catch (RemoteException e) {
            Log.i(ServicerHandler.class.getName(), "Error WPM(" + e.getMessage() + ")");
        }
    }
}
