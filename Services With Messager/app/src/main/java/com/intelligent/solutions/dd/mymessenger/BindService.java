package com.intelligent.solutions.dd.mymessenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

import com.intelligent.solutions.dd.mymessenger.Helpers.ServicerHandler;

public class BindService extends Service {

    private Messenger messenger =  new Messenger(new ServicerHandler(this));

    public BindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    public String toUpperCase(String data){
        return  data.toUpperCase();
    }
}
