package wt.walk_tourist;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import wt.walk_tourist.base.MDF_Base;
import wt.walk_tourist.game.MDF_Game_Contents;
import wt.walk_tourist.help.MDF_Help;
import wt.walk_tourist.point_management.MDF_PointManagement;
import wt.walk_tourist.tourist_spot.MDF_TouristSpot;
import wt.walk_tourist.tourist_spot.utility.SettingsUtilty;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

public class MainActivity extends Activity implements WT_MainDisplayFragment.MainFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onStart()
    {
        super.onStart();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MDF_Base baseFragment = new MDF_Base();

        fragmentTransaction.add(R.id.main_fragment, baseFragment, "baseFragment");
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        fragmentTransaction.commit();
    }


    public void changeMDF(WT_MainDisplayFragment.MDF_NAME name)
    {
        if (name == WT_MainDisplayFragment.MDF_NAME.MDF_SPOT) {
            SettingsUtilty.outputOperationLog("観光地Listを押下しました。");
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            //fragmentManager.popBackStack();
            //fragmentTransaction.commit();
            /*
            Intent intent = new Intent();
            // ここで設定するパッケージ名（wt.walk_tourist）はAvdroidManifest.xmlで設定しているアプリケーションのパッケージ名であることに注意！！
            intent.setClassName("wt.walk_tourist","wt.walk_tourist.settings.TouristSpotActivity");
            startActivity(intent);
            */


            MDF_TouristSpot touristSpotForPrefFragment = new MDF_TouristSpot();

            fragmentTransaction.replace(R.id.main_fragment, touristSpotForPrefFragment, "touristSpotForPrefFragment");

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            // TODO 標準のバックキーを押下すると、アクティビティ自体が終了してしまう・・・・
            // TODO バックキーを押したときは現在表示中のフラグメントを閉じて、アクティビティは閉じないようにしたい。
            // TODO ActionBarActivityをActivityに変更すると正常に動作する

            fragmentTransaction.commit();
        }else if (name == WT_MainDisplayFragment.MDF_NAME.MDF_GAME) {
            SettingsUtilty.outputOperationLog("ゲームを押下しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            //fragmentManager.popBackStack();
            //fragmentTransaction.commit();

            MDF_Game_Contents gameContentsFragment = new MDF_Game_Contents();

            fragmentTransaction.replace(R.id.main_fragment, gameContentsFragment, "gameContentsFragment");

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            fragmentTransaction.commit();
        }else if (name == WT_MainDisplayFragment.MDF_NAME.MDF_POINT) {
            SettingsUtilty.outputOperationLog("ポイントを押下しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            //fragmentManager.popBackStack();
            //fragmentTransaction.commit();

            MDF_PointManagement pointManagementFragment = new MDF_PointManagement();

            fragmentTransaction.replace(R.id.main_fragment, pointManagementFragment, "pointManagementFragment");

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            fragmentTransaction.commit();
        }else if (name == WT_MainDisplayFragment.MDF_NAME.MDF_HELP) {
            SettingsUtilty.outputOperationLog("Helpを押下しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            //fragmentManager.popBackStack();
            //fragmentTransaction.commit();

            MDF_Help helpFragment = new MDF_Help();

            fragmentTransaction.replace(R.id.main_fragment, helpFragment, "helpFragment");

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            fragmentTransaction.commit();
        }else if (name == WT_MainDisplayFragment.MDF_NAME.MDF_BASE) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            //fragmentManager.popBackStack();
            //fragmentTransaction.commit();

            MDF_Base baseFragment = new MDF_Base();

            fragmentTransaction.replace(R.id.main_fragment, baseFragment, "baseFragment");

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

            fragmentTransaction.commit();

        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode != KeyEvent.KEYCODE_BACK){
            return super.onKeyDown(keyCode, event);
        }else{
            // TODO 今表示中の画面がBaseの場合はアプリケーション終了確認ダイアログを表示する
            changeMDF(WT_MainDisplayFragment.MDF_NAME.MDF_BASE);
            return false;
        }
    }
}
