package cn.paulpaulzhang.dormitorymanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.paulpaulzhang.dormitorymanagement.base.BaseFragment;
import cn.paulpaulzhang.dormitorymanagement.fragment.NotificationFragment;
import cn.paulpaulzhang.dormitorymanagement.fragment.ManageFragment;
import cn.paulpaulzhang.dormitorymanagement.fragment.UserFragment;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.adapter
 * 创建时间: 8/4/2019
 * 创建人: zlm31
 * 描述:
 */
public class BottomNavViewPagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments = new ArrayList<>();

    public BottomNavViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);

        fragments.add(new ManageFragment());
        fragments.add(new NotificationFragment());
        fragments.add(new UserFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
