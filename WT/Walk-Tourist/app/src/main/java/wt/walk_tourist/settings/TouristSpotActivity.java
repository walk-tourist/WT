package wt.walk_tourist.settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wt.walk_tourist.R;


public class TouristSpotActivity extends Activity implements View.OnClickListener {

    // 選択した観光地（県）
    private int selectedSpotNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_tourist_spot_activity_main);


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

        TouristSpotListAdapter touristSpotListAdapter = new TouristSpotListAdapter(this, 0, touristSpotDatas);

        // リストビューにデータを設定
        ListView prefectureListView = (ListView)findViewById(R.id.listView1);
        prefectureListView.setAdapter(touristSpotListAdapter);

        prefectureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックしたリストの情報を格納 リクエスト送信用
                ListView listView = (ListView)parent;

                // タップした観光地IDをセットする
                selectedSpotNo = ((TouristSpotData)listView.getItemAtPosition(position)).getSpotId();

                // 動作確認ダイアログを表示
                Toast.makeText(getApplicationContext(), ((TouristSpotData)listView.getItemAtPosition(position)).getSpotName(), Toast.LENGTH_SHORT).show();

                onClick(listView);

            }
        });
    }

    @Override
    public void onClick(View view) {

    }

}
