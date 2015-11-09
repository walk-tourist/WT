package com.example.user.commonfragmentlib.sound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

/**
 * Created by taguchi on 2015/06/09.
 * BGM再生
 */
public class BGM_Player_Service extends Service {

    final IBinder binder = new MyBinder();

    private MediaPlayer mMp;
    private int mPlayBGMResId = 0;

    public class MyBinder extends Binder {
        public BGM_Player_Service getService(){
            return BGM_Player_Service.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId )
    {
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        releaseMp();
        super.onDestroy();
    }

    private void releaseMp()
    {
        if( null != mMp )
        {
            mMp.release();
            mMp = null;
        }
    }

    private void changeBGM(int bgmResId)
    {
        releaseMp();
        if( 0 != bgmResId ) {
            mMp = MediaPlayer.create(this, bgmResId);
            mMp.setLooping(true);
        }
        mPlayBGMResId = bgmResId;
    }

    public void startBGM(int bgmResId)
    {
        if( mPlayBGMResId != bgmResId )
        {
            changeBGM(bgmResId);
        }
        if ( null != mMp )
        {
            mMp.start();
        }
    }

    public void stopBGM()
    {
        if( null != mMp )
        {
            mMp.stop();
            try {
                mMp.prepare();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e("stopBGM", "IllegalStateException");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e("stopBGM", "IOException");
                e.printStackTrace();
            }
        }
    }
}