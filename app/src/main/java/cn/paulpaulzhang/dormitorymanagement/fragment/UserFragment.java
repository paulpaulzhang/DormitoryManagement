package cn.paulpaulzhang.dormitorymanagement.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.Constant;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.activity.LogActivity;
import cn.paulpaulzhang.dormitorymanagement.activity.UserInfoActivity;
import cn.paulpaulzhang.dormitorymanagement.base.BaseFragment;
import cn.paulpaulzhang.dormitorymanagement.base.Fair;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.file.IUploadFileListener;
import cn.paulpaulzhang.dormitorymanagement.file.UploadUtil;
import cn.paulpaulzhang.dormitorymanagement.loader.FairLoader;
import cn.paulpaulzhang.dormitorymanagement.model.Admin;
import cn.paulpaulzhang.dormitorymanagement.model.Admin_;
import cn.paulpaulzhang.dormitorymanagement.util.FairLogger;
import cn.paulpaulzhang.dormitorymanagement.util.FairPreference;
import cn.paulpaulzhang.dormitorymanagement.util.FileUtil;
import cn.paulpaulzhang.dormitorymanagement.util.Glide4Engine;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.fragment
 * 创建时间: 8/4/2019
 * 创建人: zlm31
 * 描述:
 */
public class UserFragment extends BaseFragment {
    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.ll_user)
    LinearLayout mUser;

    @BindView(R2.id.civ_user)
    CircleImageView mUserAvatar;

    @BindView(R2.id.tv_user)
    AppCompatTextView mName;

    @BindView(R2.id.tv_gender)
    AppCompatTextView mGender;

    @BindView(R2.id.tv_username)
    AppCompatTextView mUsername;

    @BindView(R2.id.cl_log)
    ConstraintLayout mLog;

    @Override
    public void initView(Bundle savedInstanceState, View rootView) {
        mToolbar.setTitle("个人中心");

        initData();

        mUser.setOnClickListener(v -> startActivity(new Intent(getContext(), UserInfoActivity.class)));

        mLog.setOnClickListener(v -> startActivity(new Intent(getContext(), LogActivity.class)));

        mUserAvatar.setOnClickListener(v -> openAlbum());
    }

    private void initData() {
        Box<Admin> adminBox = ObjectBox.get().boxFor(Admin.class);
        Admin admin = adminBox.query().equal(Admin_.username, FairPreference.getCustomAppProfile(Constant.USERNAME)).build().findUnique();
        if (admin != null) {
            mName.setText(admin.getName());
            mUsername.setText(admin.getUsername());
            mGender.setText(admin.getGender());
            if (admin.getAvatarUrl() != null) {
                Glide.with(this).load(admin.getAvatarUrl()).into(mUserAvatar);
            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constant.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK && data != null) {
            cropPhoto(Matisse.obtainResult(data).get(0));
        } else if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK && data != null) {
            final Uri uri = UCrop.getOutput(data);
            if (uri != null) {
                Glide.with(this).load(uri).into(mUserAvatar);
                FairLoader.showLoading(getContext());
                UploadUtil util = UploadUtil.INSTANCE();
                util.uploadFile(getActivity(), new File(Objects.requireNonNull(uri.getPath())), new IUploadFileListener() {
                    @Override
                    public void onSuccess(String path) {
                        Box<Admin> adminBox = ObjectBox.get().boxFor(Admin.class);
                        Admin admin = adminBox.query().equal(Admin_.username, FairPreference.getCustomAppProfile(Constant.USERNAME)).build().findUnique();
                        if (admin != null) {
                            admin.setAvatarUrl(path);
                            adminBox.put(admin);
                        }
                        FairLoader.stopLoading();
                    }

                    @Override
                    public void onError() {
                        FairLoader.stopLoading();
                    }
                });
            }
        } else if (requestCode == UCrop.RESULT_ERROR) {
            Toasty.error(Objects.requireNonNull(getContext()), "无法裁剪图片", Toasty.LENGTH_SHORT).show();
        }
    }


    private void openAlbum() {
        if (EasyPermissions.hasPermissions(Objects.requireNonNull(getContext()), Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Matisse.from(UserFragment.this)
                    .choose(MimeType.of(MimeType.JPEG, MimeType.PNG))
                    .maxSelectable(1)
                    .countable(false)
                    .capture(true)
                    .captureStrategy(new CaptureStrategy(true, "cn.paulpaulzhang.dormitorymanagement.fileprovider"))
                    .gridExpectedSize(getResources()
                            .getDimensionPixelSize(R.dimen.grid_expected_size))
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    .thumbnailScale(0.85f)
                    .theme(R.style.Matisse_Zhihu_Custom)
                    .imageEngine(new Glide4Engine())
                    .forResult(Constant.REQUEST_CODE_CHOOSE);
        } else {
            EasyPermissions.requestPermissions(UserFragment.this, "打开图库需要存储读取权限", 1001,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void cropPhoto(Uri uri) {
        Uri destinationUri = Uri.fromFile(new File(Fair.getApplicationContext().getExternalCacheDir(),
                FileUtil.getFileNameByTime("IMG_", FileUtil.getExtension(uri.getPath()))));
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(Fair.getApplicationContext().getColor(R.color.colorAccent)); // 设置标题栏颜色
        options.setStatusBarColor(Fair.getApplicationContext().getColor(R.color.colorAccent)); //设置状态栏颜色
        options.setToolbarWidgetColor(Fair.getApplicationContext().getColor(android.R.color.white));
        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(512, 512)
                .withOptions(options)
                .start(Objects.requireNonNull(getContext()), this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_user;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
