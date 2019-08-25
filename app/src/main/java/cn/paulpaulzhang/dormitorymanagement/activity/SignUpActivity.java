package cn.paulpaulzhang.dormitorymanagement.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.button.MaterialButton;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Objects;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Admin;
import cn.paulpaulzhang.dormitorymanagement.model.Admin_;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class SignUpActivity extends BaseActivity {

    @BindView(R2.id.et_username)
    AppCompatEditText mUsername;

    @BindView(R2.id.et_password)
    AppCompatEditText mPassword;

    @BindView(R2.id.et_name)
    AppCompatEditText mName;

    @BindView(R2.id.et_gender)
    AppCompatEditText mGender;

    @BindView(R2.id.et_tel)
    AppCompatEditText mTel;

    @BindView(R2.id.btn_sign_up)
    MaterialButton mButton;

    @Override
    public int setLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        mButton.setOnClickListener(v -> {
            String username = Objects.requireNonNull(mUsername.getText()).toString().trim();
            String password = Objects.requireNonNull(mPassword.getText()).toString().trim();
            String name = Objects.requireNonNull(mName.getText()).toString().trim();
            String gender = Objects.requireNonNull(mGender.getText()).toString().trim();
            String tel = Objects.requireNonNull(mTel.getText()).toString().trim();

            if (username.isEmpty() || password.isEmpty() || name.isEmpty() || gender.isEmpty() || tel.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }

            Box<Admin> adminBox = ObjectBox.get().boxFor(Admin.class);
            if (adminBox.query().equal(Admin_.username, username).build().find().size() != 0) {
                Toasty.error(this, "用户名已存在，请登录", Toasty.LENGTH_SHORT).show();
                return;
            }
            adminBox.put(new Admin(username, password, name, gender, tel));

            Toasty.success(this, "注册成功", Toasty.LENGTH_SHORT).show();

            new Handler().postDelayed(this::finish, 500);
        });
    }
}
