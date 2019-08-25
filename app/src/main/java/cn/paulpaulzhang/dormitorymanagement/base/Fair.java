package cn.paulpaulzhang.dormitorymanagement.base;

import android.content.Context;

import cn.paulpaulzhang.dormitorymanagement.base.ConfigKeys;
import cn.paulpaulzhang.dormitorymanagement.base.Configurator;


/**
 * 项目名：   FairFest
 * 包名：     com.paulpaulzhang.fair.app
 * 文件名：   Fair
 * 创建者：   PaulZhang
 * 创建时间： 2019/5/18 18:49
 * 描述：     工具
 */
public final class Fair {
    public static Configurator init(Context context) {
        Configurator
                .getInstance()
                .getFairConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }
}
