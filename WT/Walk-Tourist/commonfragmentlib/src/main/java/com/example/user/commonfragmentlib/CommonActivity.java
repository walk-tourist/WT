package com.example.user.commonfragmentlib;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;

import com.example.user.commonfragmentlib.sound.BGM_Player_Service;

public abstract class CommonActivity extends Activity implements MainDisplayFragment.MainFragmentListener, DialogDisplayFragment.DialogFragmentListener {

    public enum BUNDLE_KEY{
        BUNDLE_KEY_MDF;

        public String getKey(){
            return name();
        }
    }

    public int m_LayoutWidth;
    public int m_LayoutHeight;


    protected MainDisplayFragment.MDF_NAME m_MDF_Name;
    protected int mReservationBGMResId = 0;
    protected Intent serviceIntent;
    protected BGM_Player_Service myService;
    protected ServiceConnection serviceConnection = new ServiceConnection(){
        @Override public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((BGM_Player_Service.MyBinder)service).getService();
            if( 0 != mReservationBGMResId)
            {
                myService.startBGM(mReservationBGMResId);
                mReservationBGMResId = 0;
            }
        }
        @Override public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState )
    {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_KEY.BUNDLE_KEY_MDF.getKey(), m_MDF_Name.name());
    }

}