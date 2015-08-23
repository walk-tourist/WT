package wt.walk_tourist;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

import wt.walk_tourist.DDF.Ok.DDF_Ok;
import wt.walk_tourist.DDF.Wait.DDF_Wait;
import wt.walk_tourist.DDF.Yes_Or_No.DDF_Yes_Or_No;
import wt.walk_tourist.MDF.base.MDF_Base;
import wt.walk_tourist.MDF.game.MDF_Game_Contents;
import wt.walk_tourist.MDF.help.MDF_Help;
import wt.walk_tourist.MDF.map.MDF_Map;
import wt.walk_tourist.MDF.point_management.DDF_ItemDetail;
import wt.walk_tourist.MDF.point_management.MDF_ItemSelect;
import wt.walk_tourist.MDF.point_management.MDF_PointManagement;
import wt.walk_tourist.MDF.tourist_spot.MDF_TouristSpot_ForBor;
import wt.walk_tourist.MDF.tourist_spot.MDF_TouristSpot_ForPref;
import wt.walk_tourist.MDF.tourist_spot.MDF_TouristSpot_ForSpot;
import wt.walk_tourist.define.Define;
import wt.walk_tourist.sound.BGM_Player_Service;
import wt.walk_tourist.MDF.startup.MDF_StartUp;
import wt.walk_tourist.MDF.tourist_spot.MDF_TouristSpot;
import wt.walk_tourist.wt_fragment.WT_DialogDisplayFragment;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

public class MainActivity extends Activity implements WT_MainDisplayFragment.MainFragmentListener, WT_DialogDisplayFragment.DialogFragmentListener {

    public enum BUNDLE_KEY{
        BUNDLE_KEY_MDF;

        public String getKey(){
            return name();
        }
    }

    public int m_LayoutWidth;
    public int m_LayoutHeight;

    protected WT_MainDisplayFragment.MDF_NAME m_MDF_Name;
    protected int mReservationBGMResId = 0;
    protected Intent serviceIntent;
    BGM_Player_Service myService;
    ServiceConnection serviceConnection = new ServiceConnection(){
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (  null != savedInstanceState && null != savedInstanceState.getString(BUNDLE_KEY.BUNDLE_KEY_MDF.getKey()) )
        {
            m_MDF_Name = WT_MainDisplayFragment.MDF_NAME.valueOf(savedInstanceState.getString(BUNDLE_KEY.BUNDLE_KEY_MDF.getKey()));
        }
        else
        {
            m_MDF_Name = WT_MainDisplayFragment.MDF_NAME.MDF_START;
        }

        serviceIntent = new Intent( getBaseContext(), BGM_Player_Service.class);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        changeMDF(m_MDF_Name);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        if ( null != myService )
        {
            myService.stopBGM();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.main_activity);
        m_LayoutWidth = rl.getWidth();
        m_LayoutHeight = rl.getHeight();

    }

    private void removePDF(FragmentManager fragmentManager)
    {
        // PDF_Remove処理
        WT_MainDisplayFragment oldMainDisplayFragment =  (WT_MainDisplayFragment)fragmentManager.findFragmentByTag("mainFragment");
        if( null != oldMainDisplayFragment)
        {
            oldMainDisplayFragment.removeChildFragment();
        }
    }

    public void changeMDF(WT_MainDisplayFragment.MDF_NAME name)
    {
        changeMDF(name, FragmentTransaction.TRANSIT_NONE);
    }

    public void changeMDF(WT_MainDisplayFragment.MDF_NAME name, int transaction)
    {
        m_MDF_Name = name;

        FragmentManager fragmentManager = getFragmentManager();

        removePDF(fragmentManager);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        WT_MainDisplayFragment MDF_Fragment = null;

        switch(m_MDF_Name)
        {
            case MDF_START:
                MDF_Fragment = new MDF_StartUp();
                break;
            case MDF_BASE:
                MDF_Fragment = new MDF_Base();
                break;
            case MDF_SPOT_FOR_PREF:
                MDF_Fragment = new MDF_TouristSpot_ForPref();
                break;
            case MDF_SPOT_FOR_BOR:
                MDF_Fragment = new MDF_TouristSpot_ForBor();
                break;
            case MDF_SPOT_FOR_SPOT:
                MDF_Fragment = new MDF_TouristSpot_ForSpot();
                break;
            case MDF_GAME:
                MDF_Fragment = new MDF_Game_Contents();
                break;
            case MDF_MAP:
                MDF_Fragment = new MDF_Map();
                break;
            case MDF_POINT:
                MDF_Fragment = new MDF_ItemSelect();
                break;
            case MDF_HELP:
                MDF_Fragment = new MDF_Help();
                break;
            default:
                break;
        }

        if (  null != MDF_Fragment ){//&& 0!= bgmResId ) {
            fragmentTransaction.replace(R.id.main_fragment, MDF_Fragment, "mainFragment");
            fragmentTransaction.setTransition(transaction);
            fragmentTransaction.commit();

            // openDialog(Define.DIALOG_TYPE.WAIT, FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            setOrientation(MDF_Fragment.getScreenOrientation());

            if ( null != myService )
            {
                myService.startBGM(MDF_Fragment.getBGMResId());
            }
            else
            {
                mReservationBGMResId = MDF_Fragment.getBGMResId();
            }
        }
    }

    public void openDialog(Define.DIALOG_TYPE type, int transaction){

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        WT_DialogDisplayFragment DDF_Fragment = null;

        switch(type)
        {
            case OK:
                DDF_Fragment  = new DDF_Ok();
                break;
            case OK_ITEM_DETAIL:
                DDF_Fragment = new DDF_ItemDetail();
                break;
            case WAIT:
                DDF_Fragment   = new DDF_Wait();
                break;
            case YES_OR_NO:
                DDF_Fragment  = new DDF_Yes_Or_No();
                break;
            default:
                break;
        }

        fragmentTransaction.setTransition(transaction);
        fragmentTransaction.replace(R.id.ddf_fragment, DDF_Fragment, "DDF_mainFragment");

        fragmentTransaction.commit();
    }

    private void setOrientation(int screenOrientation)
    {
        switch(screenOrientation)
        {
            case ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            case ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
            case ActivityInfo.SCREEN_ORIENTATION_SENSOR:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
                break;
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        unbindService(serviceConnection);
    }


    public void closeDialog(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WT_DialogDisplayFragment dialog = (WT_DialogDisplayFragment) fragmentManager.findFragmentByTag("DDF_mainFragment");
        if( null != dialog )
        {
            fragmentTransaction.remove(dialog);
        }
        fragmentTransaction.commit();


    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if(keyCode != KeyEvent.KEYCODE_BACK){
            return super.onKeyDown(keyCode, event);
        }else{
            FragmentManager fragmentManager = getFragmentManager();
            WT_DialogDisplayFragment fragment = (WT_DialogDisplayFragment) fragmentManager.findFragmentByTag("DDF_mainFragment");
            if( null != fragment )
            {
                fragment.callBackKey();
            }
            else {

                if (m_MDF_Name != WT_MainDisplayFragment.MDF_NAME.MDF_BASE) {
                    changeMDF(WT_MainDisplayFragment.MDF_NAME.MDF_BASE, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                } else {
                    // TODO アプリケーション終了確認ダイアログを表示する
                    Log.d("hoge", "huge");
                    stopService(new Intent(getBaseContext(), BGM_Player_Service.class));
                }
            }
            return false;
        }
    }
}