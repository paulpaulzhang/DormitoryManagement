package cn.paulpaulzhang.dormitorymanagement.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;


/**
 * 项目名：   FairFest
 * 包名：     cn.paulpaulzhang.fair.util
 * 文件名：   DimenUtil
 * 创建者：   PaulZhang
 * 创建时间： 2019/5/21 21:23
 * 描述：     测量工具类
 */
public class DimenUtil {

    public static int getScreenWidth(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getScreenWidthByDp(Context context) {
        final Resources resources = context.getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.densityDpi;
    }
}
