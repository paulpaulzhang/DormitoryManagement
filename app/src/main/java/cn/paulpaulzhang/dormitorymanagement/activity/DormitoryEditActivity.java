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
import cn.paulpaulzhang.dormitorymanagement.model.Dormitory;
import cn.paulpaulzhang.dormitorymanagement.model.TroubleShooting;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名：cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间：8/29/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class DormitoryEditActivity extends BaseActivity {

    @BindView(R2.id.et_building)
    AppCompatEditText mBuilding;

    @BindView(R2.id.et_name)
    AppCompatEditText mName;

    @BindView(R2.id.et_type)
    AppCompatEditText mType;

    @BindView(R2.id.et_number)
    AppCompatEditText mNumber;

    @BindView(R2.id.et_tel)
    AppCompatEditText mTel;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;


    @Override
    public int setLayout() {
        return R.layout.activity_dormitory_edit;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        mToolbar.setTitle("宿舍");
        fab.setOnClickListener(v -> {

            String building = Objects.requireNonNull(mBuilding.getText()).toString().trim();
            String name = Objects.requireNonNull(mName.getText()).toString().trim();
            String type = Objects.requireNonNull(mType.getText()).toString().trim();
            String number = Objects.requireNonNull(mNumber.getText()).toString().trim();
            String tel = Objects.requireNonNull(mTel.getText()).toString().trim();

            if (building.isEmpty() || name.isEmpty() || type.isEmpty() || number.isEmpty() || tel.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }

            Box<Dormitory> dormitoryBox = ObjectBox.get().boxFor(Dormitory.class);
            dormitoryBox.put(new Dormitory(building, name, type, Integer.parseInt(number), tel));

            Toasty.success(this, "创建成功", Toasty.LENGTH_SHORT).show();

            new Handler().postDelayed(this::finish, 500);
        });


    }
}
