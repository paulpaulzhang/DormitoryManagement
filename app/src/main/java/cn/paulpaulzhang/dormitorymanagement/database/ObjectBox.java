package cn.paulpaulzhang.dormitorymanagement.database;

import android.content.Context;

import cn.paulpaulzhang.dormitorymanagement.model.MyObjectBox;
import cn.paulpaulzhang.dormitorymanagement.util.FairLogger;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import io.objectbox.android.BuildConfig;

public class ObjectBox {
    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();

        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(context.getApplicationContext());
            FairLogger.i("ObjectBrowser", "Started: " + started);
        }
    }

    public static BoxStore get() {
        return boxStore;
    }
}
