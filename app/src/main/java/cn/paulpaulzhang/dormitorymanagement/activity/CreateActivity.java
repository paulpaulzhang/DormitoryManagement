package cn.paulpaulzhang.dormitorymanagement.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Notification;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/5/2019
 * 创建人: zlm31
 * 描述:
 */
public class CreateActivity extends BaseActivity {

    @BindView(R2.id.et_title)
    EditText mTitle;

    @BindView(R2.id.et_pusher)
    EditText mPusher;

    @BindView(R2.id.et_content)
    EditText mContent;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @Override
    public int setLayout() {
        return R.layout.activity_create;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initToolbar(mToolbar);

        fab.setOnClickListener(v -> {
            String title = mTitle.getText().toString();

            String content = mContent.getText().toString();

            String pusher = mPusher.getText().toString();
            Notification notification = new Notification(title, content, new Date(), pusher);
            Box<Notification> circleBox = ObjectBox.get().boxFor(Notification.class);
            circleBox.put(notification);

            Toasty.success(this, "发布成功", Toasty.LENGTH_SHORT).show();

            new Handler().postDelayed(this::finish, 500);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
