package wt.walk_tourist.MDF.point_management;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wt.walk_tourist.R;
import wt.walk_tourist.define.Define;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

public class MDF_ItemSelect extends WT_MainDisplayFragment implements View.OnClickListener {

    // 選択したアイテム
    private int selectedItemNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.shop_fragment, container, false);


        setViewItems(v);

        return v;
    }

    private void setViewItems(View v) {
        // フラグメントを複数重ねた時に下のフラグメントでクリックイベントに応答するのを防止
        v.findViewById(R.id.shop_fragment_layout).setOnClickListener(this);

        v.findViewById(R.id.shop_fragment_main_layout).setVisibility(View.VISIBLE);

        // バックボタンのリスナーをセットする
        v.findViewById(R.id.shop_back_button).setOnClickListener(this);

        showList(v);

    }

    private void showList(View v) {
        // 観光地Listのデータを作成
        List<D_Item> itemDatas = new ArrayList<D_Item>();

        itemDatas.add(new D_Item("高画質カメラ", "観光地を高画質の画質で思い出に残しませんか？", 100,  R.drawable.item_hightquority));
        itemDatas.add(new D_Item("コミュニケート", "登録したプレーヤーの居場所がわかります。", 100,  R.drawable.item_communicate));
        itemDatas.add(new D_Item("恵方巻き", "恵方巻きを食べる向きをおしらせいたします。", 5,  R.drawable.item_ehomaki));
        itemDatas.add(new D_Item("メモ", "旅行する旅程をメモして管理しませんか？", 5,  R.drawable.item_memo));
        itemDatas.add(new D_Item("ガチャ", "何かのアイテムを手に入れることができます。", 50,  R.drawable.item_gacya));
        itemDatas.add(new D_Item("ハンマー", "近くにいるプレイヤーを無作為に撲殺します。傷害罪で起訴される可能性がございます。", 5,  R.drawable.item_hummer));

        LA_Item itemListAdapter = new LA_Item(getActivity(), R.layout.shop_list_item, itemDatas);

        // リストビューにデータを設定
        ListView listView = (ListView)v.findViewById(R.id.item_list);
        listView.setAdapter(itemListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // クリックしたリストの情報を格納 リクエスト送信用
                ListView listView = (ListView) parent;

                // タップしたIDをセットする
                selectedItemNo = ((D_Item) listView.getItemAtPosition(position)).getItemId();

                // 動作確認ダイアログを表示
                Toast.makeText(getActivity(), ((D_Item) listView.getItemAtPosition(position)).getItemName(), Toast.LENGTH_SHORT).show();

                onClick(listView);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.shop_back_button) {
            // TODO この画面を閉じる
            mListener.changeMDF(MDF_NAME.MDF_BASE,FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

        } else if (view.getId() == R.id.item_list) {
            // TODO FragmentからFragmentへの値の渡し方はどのようにすればよいか
            // TODO アイテム詳細画面へ遷移するようにする
            mListener.openDialog(Define.DIALOG_TYPE.OK_ITEM_DETAIL, FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
    }
}