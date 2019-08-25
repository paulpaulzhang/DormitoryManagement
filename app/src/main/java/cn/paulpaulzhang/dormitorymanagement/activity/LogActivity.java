package cn.paulpaulzhang.dormitorymanagement.activity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.adapter.LogAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Log;
import cn.paulpaulzhang.dormitorymanagement.model.Log_;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class LogActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    private LogAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_log;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mToolbar.setTitle("登录日志");
        Box<Log> logBox = ObjectBox.get().boxFor(Log.class);
        adapter = new LogAdapter(R.layout.item_log, logBox.query().orderDesc(Log_.date).build().find());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
