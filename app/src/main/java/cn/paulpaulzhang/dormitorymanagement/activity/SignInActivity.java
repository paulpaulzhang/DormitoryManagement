package cn.paulpaulzhang.dormitorymanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.button.MaterialButton;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.Constant;
import cn.paulpaulzhang.dormitorymanagement.HomeActivity;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Admin;
import cn.paulpaulzhang.dormitorymanagement.model.Admin_;
import cn.paulpaulzhang.dormitorymanagement.model.Log;
import cn.paulpaulzhang.dormitorymanagement.util.FairPreference;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class SignInActivity extends BaseActivity {

    @BindView(R2.id.et_username)
    AppCompatEditText mUsername;

    @BindView(R2.id.et_password)
    AppCompatEditText mPassword;

    @BindView(R2.id.btn_sign_up)
    MaterialButton mSignUp;

    @BindView(R2.id.btn_sign_in)
    MaterialButton mSignIn;

    @Override
    public int setLayout() {
        return R.layout.activity_sign_in;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        mSignIn.setOnClickListener(v -> {
            String username = Objects.requireNonNull(mUsername.getText()).toString().trim();
            String password = Objects.requireNonNull(mPassword.getText()).toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }

            Box<Admin> adminBox = ObjectBox.get().boxFor(Admin.class);
            if (adminBox.query().equal(Admin_.username, username).build().find().size() == 0) {
                Toasty.error(this, "用户名不存在，请注册", Toasty.LENGTH_SHORT).show();
                return;
            }

            Admin admin = adminBox.query().equal(Admin_.username, username).build().findFirst();

            assert admin != null;
            if (TextUtils.equals(password, admin.getPassword())) {
                Box<Log> logBox = ObjectBox.get().boxFor(Log.class);
                logBox.put(new Log(admin.getName(), new Date()));
                FairPreference.removeCustomAppProfile(Constant.USERNAME);
                FairPreference.addCustomAppProfile(Constant.USERNAME, username);
                startActivity(new Intent(this, HomeActivity.class));

                new Handler().postDelayed(this::finish, 500);
            }

        });

        mSignUp.setOnClickListener(v -> startActivity(new Intent(this, SignUpActivity.class)));
    }
}
