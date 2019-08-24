package cn.paulpaulzhang.dormitorymanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.activity.CreateActivity;
import cn.paulpaulzhang.dormitorymanagement.adapter.NotificationAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseFragment;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Notification;
import cn.paulpaulzhang.dormitorymanagement.model.Notification_;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.fragment
 * 创建时间: 8/4/2019
 * 创建人: zlm31
 * 描述:
 */
public class NotificationFragment extends BaseFragment {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private NotificationAdapter adapter;


    @Override
    public void initView(Bundle savedInstanceState, View rootView) {

        mToolbar.setTitle("通知");
        initRecyclerView();

        fab.setOnClickListener(v -> startActivity(new Intent(getContext(), CreateActivity.class)));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            Box<Notification> circleBox = ObjectBox.get().boxFor(Notification.class);
            adapter.setNewData(circleBox.query().orderDesc(Notification_.date).build().find());
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_notification;
    }

    private void initRecyclerView() {
        Box<Notification> circleBox = ObjectBox.get().boxFor(Notification.class);
        adapter = new NotificationAdapter(R.layout.notification_item, circleBox.getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
