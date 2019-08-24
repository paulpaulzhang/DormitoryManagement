package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.model.Notification;
import cn.paulpaulzhang.dormitorymanagement.util.DateUtil;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间: 8/4/2019
 * 创建人: zlm31
 * 描述:
 */
public class NotificationAdapter extends BaseQuickAdapter<Notification, BaseViewHolder> {
    public NotificationAdapter(int layoutResId, @Nullable List<Notification> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Notification item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_pusher, item.getPusher())
                .setText(R.id.tv_date, DateUtil.getTime(item.getDate()))
                .setText(R.id.tv_content, item.getContent());
    }
}
