package mobile.che.com.oddymobstar.chemobile.service;

import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import mobile.che.com.oddymobstar.chemobile.activity.handler.MessageHandler;
import mobile.che.com.oddymobstar.chemobile.database.DBHelper;
import mobile.che.com.oddymobstar.chemobile.util.Configuration;

/**
 * Created by timmytime on 29/01/16.
 */
public class CheService extends IntentService {

    private final DBHelper dbHelper = new DBHelper(this);
    private final Configuration configuration = new Configuration(dbHelper.getConfigs());
    private final CheServiceBinder cheServiceBinder = new CheServiceBinder();
    private android.os.Handler handler = new android.os.Handler();
    private final CheServiceSocket cheServiceSocket = new CheServiceSocket(this, configuration);


    /*
     ideally should improve the below....but it works.  need a socket class
     */


    public CheService() {
        super("CheService");
    }

    @Override
    public void onCreate() {
        if (cheServiceSocket.connect == null) {
            //st up our socket.
            cheServiceSocket.connect = new Thread(new Runnable() {
                public void run() {
                    cheServiceSocket.connectSocket();
                }
            });

            cheServiceSocket.connect.start();
        }
    }


    @Override
    public void onDestroy() {


        super.onDestroy();

        if (cheServiceSocket.dIn != null) {
            try {
                cheServiceSocket.dIn.close();
            } catch (Exception e) {
                Log.d("dIn error", e.toString());
                cheServiceSocket.dIn = null;
            }
        }

        if (cheServiceSocket.dOut != null) {
            try {
                cheServiceSocket.dOut.close();
            } catch (Exception e) {
                Log.d("dOut error", e.toString());
                cheServiceSocket.dOut = null;
            }
        }

        if (cheServiceSocket.socket != null) {
            try {
                cheServiceSocket.socket.close();
            } catch (Exception e) {
                Log.d("socket error", e.toString());
                cheServiceSocket.socket = null;
            }
        }

        cheServiceSocket.write = null;
        cheServiceSocket.read = null;
        cheServiceSocket.connect = null;


        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        dbHelper.setMessageHandler(messageHandler);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return cheServiceBinder;
    }




    public void clearBacklog() {
        cheServiceSocket.messageBuffer.clear();
    }

    public void resetConnection() {
        cheServiceSocket.connect = new Thread(new Runnable() {
            @Override
            public void run() {
                cheServiceSocket.reConnect();
            }
        });

        cheServiceSocket.connect.start();
    }




    @Override
    public ComponentName startService(Intent intent) {
        return super.startService(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //to review this.
        return START_STICKY;
        //  return super.onStartCommand(intent,flags,startId);
    }



    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public class CheServiceBinder extends Binder {
        public CheService getCheServiceInstance() {
            return CheService.this;
        }
    }
}