package cn.paulpaulzhang.dormitorymanagement.base;

import java.util.ArrayList;
import java.util.HashMap;



/**
 * 项目名：   FairFest
 * 包名：     com.paulpaulzhang.fair.app
 * 文件名：   Configurator
 * 创建者：   PaulZhang
 * 创建时间： 2019/5/18 18:50
 * 描述：     配置文件
 */
public class Configurator {
    private static final HashMap<Object, Object> FAIR_CONFIGS = new HashMap<>();

    private Configurator() {
        FAIR_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
    }

    /**
     * 单例模式
     *
     * @return Configurator实例
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    final HashMap<Object, Object> getFairConfigs() {
        return FAIR_CONFIGS;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        FAIR_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    public final Configurator withApiHost(String host) {
        FAIR_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) FAIR_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        return (T) FAIR_CONFIGS.get(key);
    }
}
