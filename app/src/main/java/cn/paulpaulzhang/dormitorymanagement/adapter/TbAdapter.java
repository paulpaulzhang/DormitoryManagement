package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.model.TeacherBuilding;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class TbAdapter extends BaseQuickAdapter<TeacherBuilding, BaseViewHolder> {
    public TbAdapter(int layoutResId, @Nullable List<TeacherBuilding> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TeacherBuilding item) {
        helper.setText(R.id.tv_teacher, item.getTeacher())
                .setText(R.id.tv_building, item.getBuilding());
    }
}
