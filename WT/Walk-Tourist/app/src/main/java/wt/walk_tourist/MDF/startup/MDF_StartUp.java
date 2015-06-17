package wt.walk_tourist.MDF.startup;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wt.walk_tourist.R;
import wt.walk_tourist.wt_fragment.WT_MainDisplayFragment;

/**
 * Created by taguchi on 2015/06/16.
 */
public class MDF_StartUp extends WT_MainDisplayFragment implements Runnable{

    private Thread mLoop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.startup_fragment, container, false);

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();

        //スレッドの開始
        mLoop = new Thread(this);
        mLoop.start();

    }

    public void run() {

        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
        }

        mListener.changeMDF(MDF_NAME.MDF_BASE, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

    }

    public int getBGMResId()
    {
        return 0;
    }
}