package cn.paulpaulzhang.dormitorymanagement.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Out;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class OutEditActivity extends BaseActivity {

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.et_sid)
    AppCompatEditText mSid;

    @BindView(R2.id.et_remark)
    AppCompatEditText mRemark;

    @Override
    public int setLayout() {
        return R.layout.activity_out_edit;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        toolbar.setTitle("出入记录");

        fab.setOnClickListener(v -> {
            String sid = Objects.requireNonNull(mSid.getText()).toString().trim();
            String remark = Objects.requireNonNull(mRemark.getText()).toString();


            if (sid.isEmpty() || remark.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_LONG).show();
                return;
            }

            Box<Out> outBox = ObjectBox.get().boxFor(Out.class);
            outBox.put(new Out(Long.parseLong(sid), System.currentTimeMillis(), remark));

            Toasty.success(this, "登记成功", Toasty.LENGTH_SHORT).show();
            new Handler().postDelayed(this::finish, 500);
        });
    }
}
