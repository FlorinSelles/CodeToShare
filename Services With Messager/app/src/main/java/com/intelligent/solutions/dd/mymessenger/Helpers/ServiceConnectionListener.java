package com.intelligent.solutions.dd.mymessenger.Helpers;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

/**
 * Created by florin on 5/11/2015.
 */
public class ServiceConnectionListener implements ServiceConnection {

    private Messenger messenger;

    public ServiceConnectionListener(Messenger messenger){
        this.messenger = messenger;
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        this.messenger = new Messenger(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }
}
