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
import cn.paulpaulzhang.dormitorymanagement.adapter.FaultAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.TroubleShooting;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class FaultActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;

    FaultAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_fault;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mToolbar.setTitle("故障报修");

        Box<TroubleShooting> troubleShootingBox = ObjectBox.get().boxFor(TroubleShooting.class);

        adapter = new FaultAdapter(R.layout.item_fault, troubleShootingBox.getAll());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            TroubleShooting item = (TroubleShooting) adapter.getItem(position);
            if (item != null) {
                troubleShootingBox.remove(item.getId());
                adapter.remove(position);
            }
            return true;
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setNewData(troubleShootingBox.getAll());
            swipeRefreshLayout.setRefreshing(false);
        });
        fab.setOnClickListener(v -> startActivity(new Intent(this, FaultCreateActivity.class)));
    }
}
