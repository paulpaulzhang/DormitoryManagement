package cn.paulpaulzhang.dormitorymanagement;


import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.adapter.BottomNavViewPagerAdapter;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    @BindView(R2.id.bottom_navigation)
    AHBottomNavigation mBottomNavigation;

    @BindView(R2.id.view_pager)
    AHBottomNavigationViewPager mViewPager;

    private BottomNavViewPagerAdapter mViewPagerAdapter;
    private List<AHBottomNavigationItem> items = new ArrayList<>();

    @Override
    public int setLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initBottomNavigation();
        requestPermissions();
    }

    private void initBottomNavigation() {

        AHBottomNavigationItem manage = new AHBottomNavigationItem
                ("管理", R.drawable.ic_manage);
        AHBottomNavigationItem notification = new AHBottomNavigationItem
                ("通知", R.drawable.ic_circle);
        AHBottomNavigationItem mine = new AHBottomNavigationItem
                ("我", R.drawable.ic_user);

        items.add(manage);
        items.add(notification);
        items.add(mine);

        mBottomNavigation.addItems(items);
        mBottomNavigation.setTranslucentNavigationEnabled(true);
        mBottomNavigation.setBehaviorTranslationEnabled(false);
        mBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        mBottomNavigation.setAccentColor(getColor(R.color.colorAccent));

        mBottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            mViewPager.setCurrentItem(position, false);
            return true;
        });

        mViewPager.setOffscreenPageLimit(3);
        mViewPagerAdapter = new BottomNavViewPagerAdapter
                (getSupportFragmentManager(),
                        BottomNavViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setCurrentItem(0);
        mBottomNavigation.setCurrentItem(0);
    }

    private void requestPermissions() {
        EasyPermissions.requestPermissions(this, "应用需要存取图片", 1000,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "拒绝权限可能会影响使用，请前往设置开启所需权限", Toast.LENGTH_SHORT).show();
    }
}
