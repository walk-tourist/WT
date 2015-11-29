package wt.walk_tourist;

/**
 * Created by taguchi on 2015/11/26.
 */

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.user.commonfragmentlib.CommonActivity;

import wt.walk_tourist.MDF.WTMainDisplayFragment;

public abstract class WTCommonActivity extends CommonActivity implements WTMainDisplayFragment.MainFragmentListener {

    protected WTMainDisplayFragment.MDF_NAME m_MDF_Name;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState )
    {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_KEY.BUNDLE_KEY_MDF.getKey(), m_MDF_Name.name());
    }
}