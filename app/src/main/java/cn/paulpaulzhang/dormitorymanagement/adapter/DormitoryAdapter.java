package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.model.Dormitory;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class DormitoryAdapter extends BaseQuickAdapter<Dormitory, BaseViewHolder> {
    public DormitoryAdapter(int layoutResId, @Nullable List<Dormitory> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Dormitory item) {
        helper.setText(R.id.tv_building, item.getBuilding())
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_type, item.getType())
                .setText(R.id.tv_number, item.getNumber() + "")
                .setText(R.id.tv_tel, item.getTel());
    }
}
