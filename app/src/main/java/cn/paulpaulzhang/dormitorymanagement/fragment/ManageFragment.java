package cn.paulpaulzhang.dormitorymanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

import com.bigkoo.convenientbanner.ConvenientBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.activity.BuildingActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.DormitoryActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.FaultActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.LostFoundActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.OutActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.SearchActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.StudentActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.TbActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.TeacherActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.VisitorActivity;
import cn.paulpaulzhang.dormitorymanagement.banner.BannerHolderCreator;
import cn.paulpaulzhang.dormitorymanagement.base.BaseFragment;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.fragment
 * 创建时间: 8/4/2019
 * 创建人: zlm31
 * 描述:
 */
public class ManageFragment extends BaseFragment {
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.ll_building)
    LinearLayout mBuilding;

    @BindView(R2.id.ll_fault)
    LinearLayout mFault;

    @BindView(R2.id.ll_home)
    LinearLayout mHome;

    @BindView(R2.id.ll_lost_found)
    LinearLayout mLostFound;

    @BindView(R2.id.ll_out)
    LinearLayout mOut;

    @BindView(R2.id.ll_manage)
    LinearLayout mTb;

    @BindView(R2.id.ll_student)
    LinearLayout mStudent;

    @BindView(R2.id.ll_teacher)
    LinearLayout mTeacher;

    @BindView(R2.id.ll_visitor)
    LinearLayout mVisitor;

    @BindView(R2.id.ll_search)
    LinearLayout mSearch;

    @Override
    public void initView(Bundle savedInstanceState, View rootView) {
        initBanner(rootView);

        mBuilding.setOnClickListener(v -> startActivity(new Intent(getContext(), BuildingActivity.class)));

        mFault.setOnClickListener(v -> startActivity(new Intent(getContext(), FaultActivity.class)));

        mHome.setOnClickListener(v -> startActivity(new Intent(getContext(), DormitoryActivity.class)));

        mLostFound.setOnClickListener(v -> startActivity(new Intent(getContext(), LostFoundActivity.class)));

        mTb.setOnClickListener(v -> startActivity(new Intent(getContext(), TbActivity.class)));

        mOut.setOnClickListener(v -> startActivity(new Intent(getContext(), OutActivity.class)));

        mStudent.setOnClickListener(v -> startActivity(new Intent(getContext(), StudentActivity.class)));

        mTeacher.setOnClickListener(v -> startActivity(new Intent(getContext(), TeacherActivity.class)));

        mVisitor.setOnClickListener(v -> startActivity(new Intent(getContext(), VisitorActivity.class)));

        mSearch.setOnClickListener(v -> startActivity(new Intent(getContext(), SearchActivity.class), ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity()).toBundle()));

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_manage;
    }

    private void initBanner(View view) {
        List<Integer> data = new ArrayList<>();
        data.add(R.mipmap.sysbg1);
        data.add(R.mipmap.sysbg2);
        data.add(R.mipmap.sysbg3);
        data.add(R.mipmap.sysbg5);

        ConvenientBanner<Integer> mConvenientBanner = view.findViewById(R.id.banner);
        mConvenientBanner.setPages(new BannerHolderCreator(), data)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .startTurning(5000)
                .setOnItemClickListener(position -> Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show());
    }

}
