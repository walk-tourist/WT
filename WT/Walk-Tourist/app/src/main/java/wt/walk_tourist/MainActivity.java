package wt.walk_tourist;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import wt.walk_tourist.game.GameContentsFragment;
import wt.walk_tourist.tourist_spot.TouristSpotForPrefFragment;
import wt.walk_tourist.tourist_spot.utility.SettingsUtilty;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.findViewById(R.id.button_tourist_spot).setOnClickListener(this);
        this.findViewById(R.id.button_start_game).setOnClickListener(this);
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
    public void onClick(View view) {
        if (view.getId() == R.id.button_tourist_spot) {
            /*
            Intent intent = new Intent();
            // ここで設定するパッケージ名（wt.walk_tourist）はAvdroidManifest.xmlで設定しているアプリケーションのパッケージ名であることに注意！！
            intent.setClassName("wt.walk_tourist","wt.walk_tourist.settings.TouristSpotActivity");
            startActivity(intent);
            */

            SettingsUtilty.outputOperationLog("観光地Listを押下しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            TouristSpotForPrefFragment touristSpotForPrefFragment = new TouristSpotForPrefFragment();

            fragmentTransaction.add(R.id.setting_tourist_spot_fragment, touristSpotForPrefFragment, "touristSpotForPrefFragment");
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

            GameContentsFragment gameContentsFragment = new GameContentsFragment();

            fragmentTransaction.add(R.id.setting_tourist_spot_fragment, gameContentsFragment, "gameContentsFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentTransaction.commit();
        }
    }
}
