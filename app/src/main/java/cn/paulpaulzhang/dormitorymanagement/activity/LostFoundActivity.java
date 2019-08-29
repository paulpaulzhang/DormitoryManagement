package cn.paulpaulzhang.dormitorymanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.adapter.LostFoundAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.LostAndFound;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class LostFoundActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;

    LostFoundAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_lost_found;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        mToolbar.setTitle("失物招领");

        Box<LostAndFound> lostAndFoundBox = ObjectBox.get().boxFor(LostAndFound.class);

        adapter = new LostFoundAdapter(R.layout.item_lost_found, lostAndFoundBox.getAll());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            LostAndFound item = (LostAndFound) adapter.getItem(position);
            if (item != null) {
                lostAndFoundBox.remove(item.getId());
                adapter.remove(position);
            }
            return true;
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setNewData(lostAndFoundBox.getAll());
            swipeRefreshLayout.setRefreshing(false);
        });
        fab.setOnClickListener(v -> startActivity(new Intent(this, LostFoundCreateActivity.class)));
    }
}
