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
import cn.paulpaulzhang.dormitorymanagement.adapter.BuildingAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Building;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class BuildingActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private BuildingAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_building;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mToolbar.setTitle("宿舍楼");
        Box<Building> buildingBox = ObjectBox.get().boxFor(Building.class);

        adapter = new BuildingAdapter(R.layout.item_building, buildingBox.getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setNewData(buildingBox.getAll());
            swipeRefreshLayout.setRefreshing(false);
        });

        fab.setOnClickListener(v -> startActivity(new Intent(this, BuildingEditActivity.class)));

        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            Building building = (Building) adapter.getItem(position);
            if (building != null) {
                buildingBox.remove(building.getId());
                adapter.remove(position);
            }
            return true;
        });
    }
}
