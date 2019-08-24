package cn.paulpaulzhang.dormitorymanagement.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.base
 * 创建时间: 8/3/2019
 * 创建人: zlm31
 * 描述:
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

        rootView = inflater.inflate(setLayout(), container, false);

        mUnbinder = ButterKnife.bind(this, rootView);
        initView(savedInstanceState, rootView);
        return rootView;
    }

    public abstract void initView(Bundle savedInstanceState, View rootView);

    public abstract int setLayout();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
