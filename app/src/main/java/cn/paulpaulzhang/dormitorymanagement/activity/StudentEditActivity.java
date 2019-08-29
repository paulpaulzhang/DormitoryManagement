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
import cn.paulpaulzhang.dormitorymanagement.model.Student;
import cn.paulpaulzhang.dormitorymanagement.model.Student_;
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
public class StudentEditActivity extends BaseActivity {
    @BindView(R2.id.et_username)
    AppCompatEditText mUsername;

    @BindView(R2.id.et_password)
    AppCompatEditText mPassword;

    @BindView(R2.id.et_sid)
    AppCompatEditText mSid;

    @BindView(R2.id.et_name)
    AppCompatEditText mName;

    @BindView(R2.id.et_gender)
    AppCompatEditText mGender;

    @BindView(R2.id.et_dormitory)
    AppCompatEditText mDormitory;

    @BindView(R2.id.btn_confirm)
    MaterialButton mButton;

    @Override
    public int setLayout() {
        return R.layout.activity_student_edit;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true).fitsSystemWindows(true).init();

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);

        Box<Student> studentBox = ObjectBox.get().boxFor(Student.class);
        Student student = studentBox.query().equal(Student_.id, id).build().findUnique();

        if (id != -1) {
            mSid.setEnabled(false);
        }

        if (id != -1 && student != null) {
            mUsername.setText(student.getUsername());
            mPassword.setText(student.getPassword());
            mName.setText(student.getName());
            mGender.setText(student.getGender());
            mDormitory.setText(student.getDormitory());
            mSid.setText(String.valueOf(student.getId()));
        }

        mButton.setOnClickListener(v -> {
            String username = Objects.requireNonNull(mUsername.getText()).toString().trim();
            String password = Objects.requireNonNull(mPassword.getText()).toString().trim();
            String name = Objects.requireNonNull(mName.getText()).toString().trim();
            String gender = Objects.requireNonNull(mGender.getText()).toString().trim();
            String dormitory = Objects.requireNonNull(mDormitory.getText()).toString().trim();
            String sid = Objects.requireNonNull(mSid.getText()).toString().trim();

            if (username.isEmpty() || password.isEmpty() || name.isEmpty() || gender.isEmpty() || dormitory.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }

            if (id == -1) {
                Student s = new Student(Long.parseLong(sid), dormitory, username, password, name, gender);
                studentBox.put(s);
                Toasty.success(this, "操作成功", Toasty.LENGTH_SHORT).show();
            } else if (student != null) {
                student.setGender(gender);
                student.setName(name);
                student.setPassword(password);
                student.setDormitory(dormitory);
                studentBox.put(student);
                Toasty.success(this, "操作成功", Toasty.LENGTH_SHORT).show();
            }
        });
    }
}
