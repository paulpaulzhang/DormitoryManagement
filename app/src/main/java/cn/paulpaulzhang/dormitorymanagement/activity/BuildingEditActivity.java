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
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class BuildingEditActivity extends BaseActivity {

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.et_name)
    AppCompatEditText mName;

    @BindView(R2.id.et_introduction)
    AppCompatEditText mIntroduction;

    @Override
    public int setLayout() {
        return R.layout.activity_buliding_edit;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        toolbar.setTitle("宿舍楼");

        fab.setOnClickListener(v -> {
            String name = Objects.requireNonNull(mName.getText()).toString().trim();
            String introduction = Objects.requireNonNull(mIntroduction.getText()).toString();


            if (name.isEmpty() || introduction.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_LONG).show();
                return;
            }

            Box<Building> buildingBox = ObjectBox.get().boxFor(Building.class);
            buildingBox.put(new Building(name, introduction));

            Toasty.success(this, "登记成功", Toasty.LENGTH_SHORT).show();
            new Handler().postDelayed(this::finish, 500);
        });
    }
}
