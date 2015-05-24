package wt.walk_tourist;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import wt.walk_tourist.base.MDF_Base;
import wt.walk_tourist.game.MDF_Game_Contents;
import wt.walk_tourist.help.MDF_Help;
import wt.walk_tourist.point_management.MDF_PointManagement;
import wt.walk_tourist.tourist_spot.MDF_TouristSpot;
import wt.walk_tourist.tourist_spot.utility.SettingsUtilty;

public class MainActivity extends Activity implements View.OnClickListener{

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


    public void move(View view)
    {
        this.onClick(view);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_tourist_spot) {
            SettingsUtilty.outputOperationLog("観光地Listを押下しました。");
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            fragmentManager.popBackStack();
            fragmentTransaction.commit();
            /*
            Intent intent = new Intent();
            // ここで設定するパッケージ名（wt.walk_tourist）はAvdroidManifest.xmlで設定しているアプリケーションのパッケージ名であることに注意！！
            intent.setClassName("wt.walk_tourist","wt.walk_tourist.settings.TouristSpotActivity");
            startActivity(intent);
            */


            MDF_TouristSpot touristSpotForPrefFragment = new MDF_TouristSpot();

            fragmentTransaction.add(R.id.main_fragment, touristSpotForPrefFragment, "touristSpotForPrefFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            // TODO 標準のバックキーを押下すると、アクティビティ自体が終了してしまう・・・・
            // TODO バックキーを押したときは現在表示中のフラグメントを閉じて、アクティビティは閉じないようにしたい。
            // TODO ActionBarActivityをActivityに変更すると正常に動作する

            fragmentTransaction.commit();
        } else if (view.getId() == R.id.button_start_game) {
            SettingsUtilty.outputOperationLog("ゲームを押下しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            fragmentManager.popBackStack();
            fragmentTransaction.commit();

            MDF_Game_Contents gameContentsFragment = new MDF_Game_Contents();

            fragmentTransaction.add(R.id.main_fragment, gameContentsFragment, "gameContentsFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentTransaction.commit();
        } else if (view.getId() == R.id.button_point_management) {
            SettingsUtilty.outputOperationLog("ポイントを押下しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            fragmentManager.popBackStack();
            fragmentTransaction.commit();

            MDF_PointManagement pointManagementFragment = new MDF_PointManagement();

            fragmentTransaction.add(R.id.main_fragment, pointManagementFragment, "pointManagementFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentTransaction.commit();
        } else if (view.getId() == R.id.button_help) {
            SettingsUtilty.outputOperationLog("Helpを押下しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            fragmentManager.popBackStack();
            fragmentTransaction.commit();

            MDF_Help helpFragment = new MDF_Help();

            fragmentTransaction.add(R.id.main_fragment, helpFragment, "helpFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentTransaction.commit();
        }
    }
}
