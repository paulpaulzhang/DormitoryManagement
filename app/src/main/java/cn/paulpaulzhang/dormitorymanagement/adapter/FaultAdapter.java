package cn.paulpaulzhang.dormitorymanagement.adapter;

import android.widget.CheckBox;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.LostAndFound;
import cn.paulpaulzhang.dormitorymanagement.model.TroubleShooting;
import io.objectbox.Box;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间：8/26/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class FaultAdapter extends BaseQuickAdapter<TroubleShooting, BaseViewHolder> {
    public FaultAdapter(int layoutResId, @Nullable List<TroubleShooting> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TroubleShooting item) {
        helper.setText(R.id.tv_register, item.getRegister())
                .setText(R.id.tv_detail, item.getDetail());

        CheckBox box = helper.getView(R.id.check);
        box.setChecked(item.isFix());

        Box<TroubleShooting> lostAndFoundBox = ObjectBox.get().boxFor(TroubleShooting.class);
        box.setOnCheckedChangeListener((compoundButton, b) -> {
            item.setFix(b);
            lostAndFoundBox.put(item);
        });
    }
}
