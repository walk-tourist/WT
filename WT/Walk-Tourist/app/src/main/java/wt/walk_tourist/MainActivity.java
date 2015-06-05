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

        // PDF_Remove処理
        WT_MainDisplayFragment oldMainDisplayFragment =  (WT_MainDisplayFragment)fragmentManager.findFragmentByTag("mainFragment");
        if( null != oldMainDisplayFragment)
        {
            oldMainDisplayFragment.removeChildFragment();
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        int transactionType = 0;

        WT_MainDisplayFragment MDF_Fragment = null;

        switch(name)
        {
            case MDF_BASE:
                MDF_Fragment = new MDF_Base();
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;
                break;
            case MDF_SPOT:
                MDF_Fragment = new MDF_TouristSpot();
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            case MDF_GAME:
                MDF_Fragment = new MDF_Game_Contents();
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            case MDF_MAP:
                // TODO MAP用の処理が必要 (今はほぼHELP処理のコピペ)
                MDF_Fragment = new MDF_Help();
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            case MDF_POINT:
                MDF_Fragment = new MDF_PointManagement();
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            case MDF_HELP:
                MDF_Fragment = new MDF_Help();
                transactionType = FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
                break;
            default:
                break;
        }

        if (  null != MDF_Fragment ) {
            fragmentTransaction.replace(R.id.main_fragment, MDF_Fragment, "mainFragment");
            fragmentTransaction.setTransition(transactionType);
            fragmentTransaction.commit();
        }
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