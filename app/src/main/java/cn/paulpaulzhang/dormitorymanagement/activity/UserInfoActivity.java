package cn.paulpaulzhang.dormitorymanagement.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.button.MaterialButton;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Objects;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.Constant;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Admin;
import cn.paulpaulzhang.dormitorymanagement.model.Admin_;
import cn.paulpaulzhang.dormitorymanagement.util.FairPreference;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class UserInfoActivity extends BaseActivity {

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

    @BindView(R2.id.btn_save)
    MaterialButton mButton;

    @Override
    public int setLayout() {
        return R.layout.activity_user_info;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).init();

        Box<Admin> adminBox = ObjectBox.get().boxFor(Admin.class);
        Admin admin = adminBox.query().equal(Admin_.username, FairPreference.getCustomAppProfile(Constant.USERNAME)).build().findUnique();

        mUsername.setEnabled(false);

        if (admin != null) {
            mGender.setText(admin.getGender());
            mName.setText(admin.getName());
            mTel.setText(admin.getTel());
            mPassword.setText(admin.getPassword());
            mUsername.setText(admin.getUsername());
        }

        mButton.setOnClickListener(v -> {
            String password = Objects.requireNonNull(mPassword.getText()).toString().trim();
            String name = Objects.requireNonNull(mName.getText()).toString().trim();
            String gender = Objects.requireNonNull(mGender.getText()).toString().trim();
            String tel = Objects.requireNonNull(mTel.getText()).toString().trim();

            if (password.isEmpty() || name.isEmpty() || gender.isEmpty() || tel.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }


            if (admin != null) {
                admin.setGender(gender);
                admin.setName(name);
                admin.setTel(tel);
                admin.setPassword(password);
                adminBox.put(admin);
            }
            Toasty.success(this, "修改成功", Toasty.LENGTH_SHORT).show();
        });
    }
}
