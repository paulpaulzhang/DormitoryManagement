package cn.paulpaulzhang.dormitorymanagement.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Visitor;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间：8/28/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class VisitorEditActivity extends BaseActivity {

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.et_tel)
    AppCompatEditText mTel;

    @BindView(R2.id.et_reason)
    AppCompatEditText mReason;

    @BindView(R2.id.et_visitor)
    AppCompatEditText mVisitor;

    @Override
    public int setLayout() {
        return R.layout.activity_visitor_edit;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        toolbar.setTitle("访客登记");

        fab.setOnClickListener(v -> {
            String tel = Objects.requireNonNull(mTel.getText()).toString().trim();
            String reason = Objects.requireNonNull(mReason.getText()).toString();
            String visitor = Objects.requireNonNull(mVisitor.getText()).toString();

            if (tel.isEmpty() || reason.isEmpty() || visitor.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_LONG).show();
                return;
            }

            Box<Visitor> visitorBox = ObjectBox.get().boxFor(Visitor.class);
            visitorBox.put(new Visitor(visitor, reason, tel));

            Toasty.success(this, "登记成功", Toasty.LENGTH_SHORT).show();
            new Handler().postDelayed(this::finish, 500);
        });
    }
}
