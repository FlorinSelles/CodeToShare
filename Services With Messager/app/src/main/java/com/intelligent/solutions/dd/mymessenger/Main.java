package com.intelligent.solutions.dd.mymessenger;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.intelligent.solutions.dd.mymessenger.Helpers.ClientHandler;
import com.intelligent.solutions.dd.mymessenger.Helpers.ServiceConnectionListener;
import com.intelligent.solutions.dd.mymessenger.Models.ServiceWorkToDo;


public class Main extends ActionBarActivity implements View.OnClickListener {

    private Messenger messenger;
    private ClientHandler clientHandler;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView upperCaseLetters = (TextView) this.findViewById(R.id.upperCaseLetters);

        this.clientHandler = new ClientHandler(upperCaseLetters);
        this.connection = new ServiceConnectionListener(this.messenger);

        findViewById(R.id.convert).setOnClickListener(this);
    }

    public void onStart() {
        super.onStart();
        bindService(new Intent(this, BindService.class), connection, Context.BIND_AUTO_CREATE);
    }

    public void onStop() {
        super.onStop();
        unbindService(connection);
        connection = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.convert:
                EditText content = ((EditText)findViewById(R.id.myInput));
                String input = content.getText().toString().trim();


                Bundle dataToSend = new Bundle();
                dataToSend.putString("data", input);

                Message msg = Message.obtain(null, ServiceWorkToDo.TO_UPPER_CASE.ordinal());
                msg.setData(dataToSend);
                msg.replyTo = new Messenger(this.clientHandler);

                try {
                    messenger.send(msg);
                } catch (RemoteException re) {
                    Log.i(Main.class.getName(), "Unable to send the message");
                } catch(Exception ge) {
                    Log.i(Main.class.getName(), "Error: "  + ge);
                }
                break;
        }
    }
}
