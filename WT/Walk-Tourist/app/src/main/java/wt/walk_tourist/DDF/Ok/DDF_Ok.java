package wt.walk_tourist.DDF.Ok;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.commonfragmentlib.DialogDisplayFragment;

import wt.walk_tourist.R;

/**
 * Created by ikeda on 2015/06/24.
 */
public class DDF_Ok extends DialogDisplayFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.dialog_ok_layout, container, false);

        return v;
    }
    @Override
    public void callBackKey(){
        mListener.closeDialog();
    }
}