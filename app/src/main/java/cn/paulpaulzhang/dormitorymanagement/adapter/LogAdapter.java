package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.model.Log;
import cn.paulpaulzhang.dormitorymanagement.util.DateUtil;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class LogAdapter extends BaseQuickAdapter<Log, BaseViewHolder> {
    public LogAdapter(int layoutResId, @Nullable List<Log> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Log item) {
        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_date, DateUtil.getTime(item.getDate()));
    }
}
