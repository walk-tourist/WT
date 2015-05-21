package wt.walk_tourist.tourist_spot;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wt.walk_tourist.R;
import wt.walk_tourist.tourist_spot.utility.SettingsUtilty;


public class TouristSpotForPrefFragment extends TouristSpotFragment {

    // 選択した観光地（県）
    private int selectedSpotNo;

    @Override
    public void setViewItems(View v) {
        // フラグメントを複数重ねた時に下のフラグメントでクリックイベントに応答するのを防止
        v.findViewById(R.id.tourist_spot_fragment_layout).setOnClickListener(this);

        v.findViewById(R.id.tourist_spot_fragment_main_layout).setVisibility(View.VISIBLE);

        // バックボタンの名称をセットする
        ((TextView)v.findViewById(R.id.tourist_spot_back_button)).setText(getResources().getString(R.string.tourist_spot_back_button_home));

        // バックボタンのリスナーをセットする
        v.findViewById(R.id.tourist_spot_back_button).setOnClickListener(this);

    }

    @Override
    public void showList(View v) {
        // 観光地Listのデータを作成
        List<TouristSpotData> touristSpotDatas = new ArrayList<TouristSpotData>();

        touristSpotDatas.add(new TouristSpotData("北海道２", "50/100"));
        touristSpotDatas.add(new TouristSpotData("青森県","50/101"));
        touristSpotDatas.add(new TouristSpotData("岩手県","50/102"));
        touristSpotDatas.add(new TouristSpotData("宮城県","50/103"));
        touristSpotDatas.add(new TouristSpotData("秋田県","50/104"));
        touristSpotDatas.add(new TouristSpotData("山形県","50/105"));
        touristSpotDatas.add(new TouristSpotData("福島県","50/106"));
        touristSpotDatas.add(new TouristSpotData("茨城県","50/107"));
        touristSpotDatas.add(new TouristSpotData("栃木県","50/108"));
        touristSpotDatas.add(new TouristSpotData("群馬県","50/109"));
        touristSpotDatas.add(new TouristSpotData("埼玉県","50/110"));
        touristSpotDatas.add(new TouristSpotData("千葉県","50/111"));
        touristSpotDatas.add(new TouristSpotData("東京都","50/112"));
        touristSpotDatas.add(new TouristSpotData("神奈川県","50/113"));
        touristSpotDatas.add(new TouristSpotData("新潟県","50/114"));
        touristSpotDatas.add(new TouristSpotData("富山県","50/115"));
        touristSpotDatas.add(new TouristSpotData("石川県","50/116"));
        touristSpotDatas.add(new TouristSpotData("福井県","50/117"));
        touristSpotDatas.add(new TouristSpotData("山梨県","50/118"));
        touristSpotDatas.add(new TouristSpotData("長野県","50/119"));
        touristSpotDatas.add(new TouristSpotData("岐阜県","50/120"));
        touristSpotDatas.add(new TouristSpotData("静岡県","50/121"));
        touristSpotDatas.add(new TouristSpotData("愛知県","50/122"));
        touristSpotDatas.add(new TouristSpotData("三重県","50/123"));
        touristSpotDatas.add(new TouristSpotData("滋賀県","50/124"));
        touristSpotDatas.add(new TouristSpotData("京都府","50/125"));
        touristSpotDatas.add(new TouristSpotData("大阪府","50/126"));
        touristSpotDatas.add(new TouristSpotData("兵庫県","50/127"));
        touristSpotDatas.add(new TouristSpotData("奈良県","50/128"));
        touristSpotDatas.add(new TouristSpotData("和歌山県","50/129"));
        touristSpotDatas.add(new TouristSpotData("鳥取県","50/130"));
        touristSpotDatas.add(new TouristSpotData("島根県","50/131"));
        touristSpotDatas.add(new TouristSpotData("岡山県","50/132"));
        touristSpotDatas.add(new TouristSpotData("広島県","50/133"));
        touristSpotDatas.add(new TouristSpotData("山口県","50/134"));
        touristSpotDatas.add(new TouristSpotData("徳島県","50/135"));
        touristSpotDatas.add(new TouristSpotData("香川県","50/136"));
        touristSpotDatas.add(new TouristSpotData("愛媛県","50/137"));
        touristSpotDatas.add(new TouristSpotData("高知県","50/138"));
        touristSpotDatas.add(new TouristSpotData("福岡県","50/139"));
        touristSpotDatas.add(new TouristSpotData("佐賀県","50/140"));
        touristSpotDatas.add(new TouristSpotData("長崎県","50/141"));
        touristSpotDatas.add(new TouristSpotData("熊本県","50/142"));
        touristSpotDatas.add(new TouristSpotData("大分県","50/143"));
        touristSpotDatas.add(new TouristSpotData("宮崎県","50/144"));
        touristSpotDatas.add(new TouristSpotData("鹿児島県","50/145"));
        touristSpotDatas.add(new TouristSpotData("沖縄県","50/146"));

        TouristSpotListAdapter touristSpotListAdapter = new TouristSpotListAdapter(getActivity(), 0, touristSpotDatas);

        // リストビューにデータを設定
        ListView prefectureListView = (ListView)v.findViewById(R.id.listView1);
        prefectureListView.setAdapter(touristSpotListAdapter);

        prefectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックしたリストの情報を格納 リクエスト送信用
                ListView listView = (ListView)parent;

                // タップした観光地IDをセットする
                selectedSpotNo = ((TouristSpotData)listView.getItemAtPosition(position)).getSpotId();

                // 動作確認ダイアログを表示
                Toast.makeText(getActivity(), ((TouristSpotData)listView.getItemAtPosition(position)).getSpotName(), Toast.LENGTH_SHORT).show();

                onClick(listView);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tourist_spot_back_button) {
            // TODO この画面を閉じる
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // 1つ前のフラグメントを取り出す
            fragmentManager.popBackStack();
            fragmentTransaction.commit();

        } else if (view.getId() == R.id.listView1) {
            // TODO FragmentからFragmentへの値の渡し方はどのようにすればよいか
            SettingsUtilty.outputOperationLog("観光地を選択しました。");

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            TouristSpotForBorFragment touristSpotForBorFragment = new TouristSpotForBorFragment();

            fragmentTransaction.add(R.id.setting_tourist_spot_fragment, touristSpotForBorFragment, "touristSpotForBorFragment");
            fragmentTransaction.addToBackStack(null);

            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

            fragmentTransaction.commit();
        }
    }
}
