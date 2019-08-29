package cn.paulpaulzhang.dormitorymanagement.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.adapter.TbAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.TeacherBuilding;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class TbActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private TbAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_tb;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mToolbar.setTitle("宿舍管理");
        Box<TeacherBuilding> teacherBuildingBox = ObjectBox.get().boxFor(TeacherBuilding.class);

        adapter = new TbAdapter(R.layout.item_tb, teacherBuildingBox.getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setNewData(teacherBuildingBox.getAll());
            swipeRefreshLayout.setRefreshing(false);
        });

        fab.setOnClickListener(v -> startActivity(new Intent(this, TbEditActivity.class)));

        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            TeacherBuilding tb = (TeacherBuilding) adapter.getItem(position);
            if (tb != null) {
                teacherBuildingBox.remove(tb.getId());
                adapter.remove(position);
            }
            return true;
        });
    }
}
