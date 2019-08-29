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
import cn.paulpaulzhang.dormitorymanagement.adapter.OutAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Out;
import cn.paulpaulzhang.dormitorymanagement.model.Out_;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class OutActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private OutAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_out;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mToolbar.setTitle("出入记录");
        Box<Out> outBox = ObjectBox.get().boxFor(Out.class);

        adapter = new OutAdapter(R.layout.item_out, outBox.query().orderDesc(Out_.date).build().find());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setNewData(outBox.query().orderDesc(Out_.date).build().find());
            swipeRefreshLayout.setRefreshing(false);
        });

        fab.setOnClickListener(v -> startActivity(new Intent(this, OutEditActivity.class)));

        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            Out out = (Out) adapter.getItem(position);
            if (out != null) {
                outBox.remove(out.getId());
                adapter.remove(position);
            }
            return true;
        });
    }
}
