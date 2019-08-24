package cn.paulpaulzhang.dormitorymanagement.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseFragment;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.fragment
 * 创建时间: 8/4/2019
 * 创建人: zlm31
 * 描述:
 */
public class UserFragment extends BaseFragment {
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void initView(Bundle savedInstanceState, View rootView) {
        mToolbar.setTitle("个人中心");
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_user;
    }

}
