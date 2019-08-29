package cn.paulpaulzhang.dormitorymanagement.adapter;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.LostAndFound;
import io.objectbox.Box;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间：8/26/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class LostFoundAdapter extends BaseQuickAdapter<LostAndFound, BaseViewHolder> {
    public LostFoundAdapter(int layoutResId, @Nullable List<LostAndFound> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LostAndFound item) {
        helper.setText(R.id.tv_register, item.getRegister())
                .setText(R.id.tv_detail, item.getDetail());

        CheckBox box = helper.getView(R.id.check);
        box.setChecked(item.isClaim());

        Box<LostAndFound> lostAndFoundBox = ObjectBox.get().boxFor(LostAndFound.class);
        box.setOnCheckedChangeListener((compoundButton, b) -> {
            item.setClaim(b);
            lostAndFoundBox.put(item);
        });
    }
}
