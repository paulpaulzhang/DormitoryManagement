package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.model.Teacher;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class TeacherAdapter extends BaseQuickAdapter<Teacher, BaseViewHolder> {
    public TeacherAdapter(int layoutResId, @Nullable List<Teacher> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Teacher item) {
        helper.setText(R.id.tv_username, item.getUsername())
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_gender, item.getGender())
                .setText(R.id.tv_tel, item.getTel())
                .setText(R.id.tv_password, item.getPassword())
                .setText(R.id.tv_tid, String.valueOf(item.getId()));
    }
}
