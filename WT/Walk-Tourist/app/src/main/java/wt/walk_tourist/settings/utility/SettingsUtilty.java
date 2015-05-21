package wt.walk_tourist.settings.utility;

import android.util.Log;

/**
 * Created by matsuguma on 2015/05/21.
 */
public class SettingsUtilty {
    /**
     * 操作ログを出力する
     * @param operation 操作名
     */
    public static void outputOperationLog(String operation) {
        Log.v("LookLife", "Fragment-LifeCycle-OPE：" + operation);
    }
}
