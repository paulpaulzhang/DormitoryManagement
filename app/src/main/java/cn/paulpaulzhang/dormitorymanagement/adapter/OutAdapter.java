package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.model.Out;
import cn.paulpaulzhang.dormitorymanagement.util.DateUtil;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class OutAdapter extends BaseQuickAdapter<Out, BaseViewHolder> {
    public OutAdapter(int layoutResId, @Nullable List<Out> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Out item) {
        helper.setText(R.id.tv_sid, item.getStudentId() + "")
                .setText(R.id.tv_date, DateUtil.getTime(item.getDate()))
                .setText(R.id.tv_remark, item.getRemark());
    }
}
