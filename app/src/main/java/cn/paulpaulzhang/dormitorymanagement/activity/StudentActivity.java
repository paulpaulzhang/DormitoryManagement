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
import cn.paulpaulzhang.dormitorymanagement.adapter.StudentAdapter;
import cn.paulpaulzhang.dormitorymanagement.adapter.TeacherAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.model.Student;
import cn.paulpaulzhang.dormitorymanagement.model.Teacher;
import io.objectbox.Box;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/25/2019
 * 创建人: zlm31
 * 描述:
 */
public class StudentActivity extends BaseActivity {

    @BindView(R2.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.srl)
    SwipeRefreshLayout swipeRefreshLayout;

    private StudentAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.activity_student;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mToolbar.setTitle("管理员");
        Box<Student> studentBox = ObjectBox.get().boxFor(Student.class);
        adapter = new StudentAdapter(R.layout.item_student, studentBox.getAll());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter, view, position) -> {
            Student student = (Student) adapter.getItem(position);
            if (student != null) {
                Intent intent = new Intent(this, StudentEditActivity.class);
                intent.putExtra("id", student.getId());
                startActivity(intent);
            }

        });

        adapter.setOnItemLongClickListener((adapter, view, position) -> {
            Student student = (Student) adapter.getItem(position);
            if (student != null) {
                studentBox.remove(student.getId());
                adapter.remove(position);
            }
            return true;
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            refreshData();
            swipeRefreshLayout.setRefreshing(false);
        });

        fab.setOnClickListener(v -> startActivity(new Intent(this, StudentEditActivity.class)));
    }

    private void refreshData() {
        Box<Student> studentBox = ObjectBox.get().boxFor(Student.class);
        adapter.setNewData(studentBox.getAll());
    }
}
