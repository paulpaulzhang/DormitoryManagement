package cn.paulpaulzhang.dormitorymanagement.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
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
    LinearLayout mManage;

    @BindView(R2.id.ll_student)
    LinearLayout mStudent;

    @BindView(R2.id.ll_teacher)
    LinearLayout mTeacher;

    @BindView(R2.id.ll_visitor)
    LinearLayout mVisitor;

    @Override
    public void initView(Bundle savedInstanceState, View rootView) {
        initBanner(rootView);

        mBuilding.setOnClickListener(v -> {

        });

        mFault.setOnClickListener(v -> {

        });

        mHome.setOnClickListener(v -> {

        });

        mLostFound.setOnClickListener(v -> {

        });

        mManage.setOnClickListener(v -> {

        });

        mOut.setOnClickListener(v -> {

        });

        mStudent.setOnClickListener(v -> {

        });

        mTeacher.setOnClickListener(v -> {

        });

        mVisitor.setOnClickListener(v -> {

        });
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_manage;
    }

    private void initBanner(View view) {
        List<Integer> data = new ArrayList<>();
        data.add(R.drawable.ic_launcher_background);
        data.add(R.drawable.ic_launcher_foreground);
        data.add(R.drawable.ic_launcher_background);

        ConvenientBanner<Integer> mConvenientBanner = view.findViewById(R.id.banner);
        mConvenientBanner.setPages(new BannerHolderCreator(), data)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .startTurning(5000)
                .setOnItemClickListener(position -> Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show());
    }

}
