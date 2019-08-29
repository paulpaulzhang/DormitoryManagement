package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.model.Student;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class StudentAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {
    public StudentAdapter(int layoutResId, @Nullable List<Student> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Student item) {
        helper.setText(R.id.tv_username, item.getUsername())
                .setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_gender, item.getGender())
                .setText(R.id.tv_dormitory, item.getDormitory())
                .setText(R.id.tv_password, item.getPassword())
                .setText(R.id.tv_sid, String.valueOf(item.getId()));
    }
}
