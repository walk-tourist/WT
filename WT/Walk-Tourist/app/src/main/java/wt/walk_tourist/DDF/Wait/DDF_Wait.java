package wt.walk_tourist.DDF.Wait;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;
import wt.walk_tourist.wt_fragment.WT_DialogDisplayFragment;

/**
 * Created by ikeda on 2015/06/24.
 */
public class DDF_Wait extends WT_DialogDisplayFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.dialog_wait_layout, container, false);

        return v;
    }
}