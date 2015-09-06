package wt.walk_tourist.MDF.point_management;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import wt.walk_tourist.DDF.Ok.DDF_Ok;
import wt.walk_tourist.MDF.tourist_spot.D_TouristSpot;
import wt.walk_tourist.R;
import wt.walk_tourist.define.Define;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

/**
 * Created by User on 2015/08/08.
 */
public class DDF_ItemDetail extends DDF_Ok implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.item_detail_dialog_ok_layout, container, false);

        // フラグメントを複数重ねた時に下のフラグメントでクリックイベントに応答するのを防止
        v.findViewById(R.id.item_detail_dialog_ok_layout).setOnClickListener(this);

        // OKボタンのリスナーをセットする
        v.findViewById(R.id.OK_button).setOnClickListener(this);

        // 購入ボタンのリスナーをセットする
        v.findViewById(R.id.buy_button).setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.OK_button) {
            // ダイアログを閉じる。
            mListener.closeDialog();
        } else if(view.getId() == R.id.buy_button) {
            // 動作確認ダイアログを表示
            Toast.makeText(getActivity(), "購入していただき・・・", Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(), "\n\n\n\n\n\n\nあざ～～～っす！！\n\n\n\n\n\n", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.item_detail_dialog_ok_layout) {
            // TODO 購入ボタンを押したときの動作
            mListener.closeDialog();
        }
    }
}
