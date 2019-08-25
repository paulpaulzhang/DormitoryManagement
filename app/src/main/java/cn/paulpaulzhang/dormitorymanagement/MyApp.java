package cn.paulpaulzhang.dormitorymanagement;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import cn.paulpaulzhang.dormitorymanagement.base.Fair;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import es.dmoral.toasty.Toasty;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement
 * 创建时间: 8/3/2019
 * 创建人: zlm31
 * 描述:
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fair.init(this).configure();
        ObjectBox.init(this);
        Fresco.initialize(this);
        Toasty.Config.getInstance().apply();
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
