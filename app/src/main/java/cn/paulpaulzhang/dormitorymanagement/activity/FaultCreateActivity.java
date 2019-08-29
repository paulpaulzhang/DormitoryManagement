package cn.paulpaulzhang.dormitorymanagement.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.LostAndFound;
import cn.paulpaulzhang.dormitorymanagement.model.TroubleShooting;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间：8/26/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class FaultCreateActivity extends BaseActivity {

    @BindView(R2.id.et_pusher)
    EditText mRegister;

    @BindView(R2.id.et_content)
    EditText mDetail;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int setLayout() {
        return R.layout.activity_fault_create;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mToolbar.setTitle("故障报修");
        fab.setOnClickListener(v -> {
            String register = mRegister.getText().toString();
            String detail = mDetail.getText().toString();

            if (register.isEmpty() || detail.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }

            Box<TroubleShooting> troubleShootingBox = ObjectBox.get().boxFor(TroubleShooting.class);
            troubleShootingBox.put(new TroubleShooting(register, detail, false));

            Toasty.success(this, "发布成功", Toasty.LENGTH_SHORT).show();

            new Handler().postDelayed(this::finish, 500);
        });
    }
}
