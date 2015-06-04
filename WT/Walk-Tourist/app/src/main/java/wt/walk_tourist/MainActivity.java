package wt.walk_tourist;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;

import wt.walk_tourist.base.MDF_Base;
import wt.walk_tourist.game.MDF_Game_Contents;
import wt.walk_tourist.help.MDF_Help;
import wt.walk_tourist.point_management.MDF_PointManagement;
import wt.walk_tourist.tourist_spot.MDF_TouristSpot;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

public class MainActivity extends Activity implements WT_MainDisplayFragment.MainFragmentListener {

    public enum BUNDLE_KEY{
        BUNDLE_KEY_MDF;

        public String getKey(){
            return name();
        }
    }

    protected WT_MainDisplayFragment.MDF_NAME m_MDF_Name;

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
            m_MDF_Name = WT_MainDisplayFragment.MDF_NAME.MDF_BASE;
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();

        changeMDF(m_MDF_Name);
    }
    public void changeMDF(WT_MainDisplayFragment.MDF_NAME name)
    {
        m_MDF_Name = name;

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        int transactionType = 0;

        switch(name)
        {
            case MDF_BASE:
                MDF_Base baseFragment = new MDF_Base();
                fragmentTransaction.replace(R.id.main_fragment, baseFragment, "baseFragment");
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;
                break;
            case MDF_SPOT:
                MDF_TouristSpot touristSpotForPrefFragment = new MDF_TouristSpot();
                fragmentTransaction.replace(R.id.main_fragment, touristSpotForPrefFragment, "touristSpotForPrefFragment");
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            case MDF_GAME:
                MDF_Game_Contents gameContentsFragment = new MDF_Game_Contents();
                fragmentTransaction.replace(R.id.main_fragment, gameContentsFragment, "gameContentsFragment");
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            case MDF_MAP:
                // TODO MAP用の処理が必要 (今はほぼHELP処理のコピペ)
                MDF_Help mapFragment = new MDF_Help();
                fragmentTransaction.replace(R.id.main_fragment, mapFragment, "helpFragment");
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            case MDF_POINT:
                MDF_PointManagement pointManagementFragment = new MDF_PointManagement();
                fragmentTransaction.replace(R.id.main_fragment, pointManagementFragment, "pointManagementFragment");
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            case MDF_HELP:
                MDF_Help helpFragment = new MDF_Help();
                fragmentTransaction.replace(R.id.main_fragment, helpFragment, "helpFragment");
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
        }

        fragmentTransaction.setTransition(transactionType);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if(keyCode != KeyEvent.KEYCODE_BACK){
            return super.onKeyDown(keyCode, event);
        }else{
            if (m_MDF_Name != WT_MainDisplayFragment.MDF_NAME.MDF_BASE)
            {
                changeMDF(WT_MainDisplayFragment.MDF_NAME.MDF_BASE);
            }
            else
            {
                // TODO アプリケーション終了確認ダイアログを表示する
                Log.d("hoge","huge");
            }
            return false;
        }
    }
}