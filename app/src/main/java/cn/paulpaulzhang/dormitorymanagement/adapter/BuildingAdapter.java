package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.model.Building;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class BuildingAdapter extends BaseQuickAdapter<Building, BaseViewHolder> {
    public BuildingAdapter(int layoutResId, @Nullable List<Building> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Building item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_introduction, item.getIntroduction());
    }
}
