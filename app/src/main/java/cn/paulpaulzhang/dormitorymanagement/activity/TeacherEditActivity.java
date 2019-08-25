package cn.paulpaulzhang.dormitorymanagement.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.button.MaterialButton;
import com.gyf.immersionbar.ImmersionBar;

import java.util.Objects;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Teacher;
import cn.paulpaulzhang.dormitorymanagement.model.Teacher_;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class TeacherEditActivity extends BaseActivity {
    @BindView(R2.id.et_username)
    AppCompatEditText mUsername;

    @BindView(R2.id.et_password)
    AppCompatEditText mPassword;

    @BindView(R2.id.et_tid)
    AppCompatEditText mTid;

    @BindView(R2.id.et_name)
    AppCompatEditText mName;

    @BindView(R2.id.et_gender)
    AppCompatEditText mGender;

    @BindView(R2.id.et_tel)
    AppCompatEditText mTel;

    @BindView(R2.id.btn_confirm)
    MaterialButton mButton;

    @Override
    public int setLayout() {
        return R.layout.activity_teacher_edit;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).init();

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);

        Box<Teacher> teacherBox = ObjectBox.get().boxFor(Teacher.class);
        Teacher teacher = teacherBox.query().equal(Teacher_.id, id).build().findUnique();

        if (id != -1) {
            mTid.setEnabled(false);
        }

        if (id != -1 && teacher != null) {
            mUsername.setText(teacher.getUsername());
            mPassword.setText(teacher.getPassword());
            mName.setText(teacher.getName());
            mGender.setText(teacher.getGender());
            mTel.setText(teacher.getTel());
            mTid.setText(String.valueOf(teacher.getId()));
        }

        mButton.setOnClickListener(v -> {
            String username = Objects.requireNonNull(mUsername.getText()).toString().trim();
            String password = Objects.requireNonNull(mPassword.getText()).toString().trim();
            String name = Objects.requireNonNull(mName.getText()).toString().trim();
            String gender = Objects.requireNonNull(mGender.getText()).toString().trim();
            String tel = Objects.requireNonNull(mTel.getText()).toString().trim();
            String tid = Objects.requireNonNull(mTid.getText()).toString().trim();

            if (username.isEmpty() || password.isEmpty() || name.isEmpty() || gender.isEmpty() || tel.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }

            if (id == -1) {
                Teacher t = new Teacher(Long.parseLong(tid), username, password, name, gender, tel);
                teacherBox.put(t);
                Toasty.success(this, "操作成功", Toasty.LENGTH_SHORT).show();
            } else if (teacher != null) {
                teacher.setGender(gender);
                teacher.setName(name);
                teacher.setPassword(password);
                teacher.setTel(tel);
                teacherBox.put(teacher);
                Toasty.success(this, "操作成功", Toasty.LENGTH_SHORT).show();
            }
        });
    }
}
