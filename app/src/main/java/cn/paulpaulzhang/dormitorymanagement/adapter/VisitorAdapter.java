package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.model.Visitor;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间：8/28/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class VisitorAdapter extends BaseQuickAdapter<Visitor, BaseViewHolder> {
    public VisitorAdapter(int layoutResId, @Nullable List<Visitor> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Visitor item) {
        helper.setText(R.id.tv_visitor, item.getVisitor())
                .setText(R.id.tv_tel, item.getTel())
                .setText(R.id.tv_reason, item.getReason());
    }
}
