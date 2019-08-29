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
import cn.paulpaulzhang.dormitorymanagement.model.Building;
import cn.paulpaulzhang.dormitorymanagement.model.TeacherBuilding;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class TbEditActivity extends BaseActivity {

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.et_teacher)
    AppCompatEditText mTeacher;

    @BindView(R2.id.et_building)
    AppCompatEditText mBuilding;

    @Override
    public int setLayout() {
        return R.layout.activity_tb_edit;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        toolbar.setTitle("宿舍管理");

        fab.setOnClickListener(v -> {
            String teacher = Objects.requireNonNull(mTeacher.getText()).toString().trim();
            String building = Objects.requireNonNull(mBuilding.getText()).toString();


            if (teacher.isEmpty() || building.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_LONG).show();
                return;
            }

            Box<TeacherBuilding> teacherBuildingBox = ObjectBox.get().boxFor(TeacherBuilding.class);
            teacherBuildingBox.put(new TeacherBuilding(teacher, building));

            Toasty.success(this, "登记成功", Toasty.LENGTH_SHORT).show();
            new Handler().postDelayed(this::finish, 500);
        });
    }
}
