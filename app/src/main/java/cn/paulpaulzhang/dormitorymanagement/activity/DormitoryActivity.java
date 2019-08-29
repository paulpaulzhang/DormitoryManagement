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
import cn.paulpaulzhang.dormitorymanagement.adapter.DormitoryAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Dormitory;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class DormitoryActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private DormitoryAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_dormitory;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mToolbar.setTitle("宿舍");
        Box<Dormitory> dormitoryBox = ObjectBox.get().boxFor(Dormitory.class);

        adapter = new DormitoryAdapter(R.layout.item_dormitory, dormitoryBox.getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setNewData(dormitoryBox.getAll());
            swipeRefreshLayout.setRefreshing(false);
        });

        fab.setOnClickListener(v -> startActivity(new Intent(this, DormitoryEditActivity.class)));

        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            Dormitory dormitory = (Dormitory) adapter.getItem(position);
            if (dormitory != null) {
                dormitoryBox.remove(dormitory.getId());
                adapter.remove(position);
            }
            return true;
        });
    }
}
