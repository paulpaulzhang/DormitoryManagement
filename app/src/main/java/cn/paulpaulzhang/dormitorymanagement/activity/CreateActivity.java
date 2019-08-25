package cn.paulpaulzhang.dormitorymanagement.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;

import butterknife.BindView;
import cn.paulpaulzhang.dormitorymanagement.Constant;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.database.ObjectBox;
import cn.paulpaulzhang.dormitorymanagement.file.IUploadFileListener;
import cn.paulpaulzhang.dormitorymanagement.file.UploadUtil;
import cn.paulpaulzhang.dormitorymanagement.loader.FairLoader;
import cn.paulpaulzhang.dormitorymanagement.model.Notification;
import cn.paulpaulzhang.dormitorymanagement.util.FairLogger;
import cn.paulpaulzhang.dormitorymanagement.util.FileUtil;
import cn.paulpaulzhang.dormitorymanagement.util.Glide4Engine;
import es.dmoral.toasty.Toasty;
import io.objectbox.Box;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 包名: cn.paulpaulzhang.dormitorymanagement.activity
 * 创建时间: 8/5/2019
 * 创建人: zlm31
 * 描述:
 */
public class CreateActivity extends BaseActivity {

    @BindView(R2.id.et_title)
    EditText mTitle;

    @BindView(R2.id.et_pusher)
    EditText mPusher;

    @BindView(R2.id.et_content)
    EditText mContent;

    @BindView(R2.id.fab)
    FloatingActionButton fab;

    @BindView(R2.id.toolbar)
    Toolbar mToolbar;

    @BindView(R2.id.iv_add)
    AppCompatImageView mAdd;

    @BindView(R2.id.iv_delete)
    AppCompatImageView mDelete;

    private String path = null;

    @Override
    public int setLayout() {
        return R.layout.activity_create;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initToolbar(mToolbar);

        fab.setOnClickListener(v -> {
            String title = mTitle.getText().toString();
            String content = mContent.getText().toString();
            String pusher = mPusher.getText().toString();
            if (title.isEmpty() || content.isEmpty() || pusher.isEmpty()) {
                Toasty.info(this, "信息不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }
            if (path == null) {
                Toasty.info(this, "图片不能为空", Toasty.LENGTH_SHORT).show();
                return;
            }
            FairLoader.showLoading(this);
            UploadUtil util = UploadUtil.INSTANCE();
            util.uploadFile(this, new File(path), new IUploadFileListener() {
                @Override
                public void onSuccess(String path) {
                    FairLoader.stopLoading();
                    Notification notification = new Notification(title, content, new Date(), pusher, path);
                    Box<Notification> notificationBox = ObjectBox.get().boxFor(Notification.class);
                    notificationBox.put(notification);

                    Toasty.success(CreateActivity.this, "发布成功", Toasty.LENGTH_SHORT).show();

                    new Handler().postDelayed(CreateActivity.this::finish, 500);
                }

                @Override
                public void onError() {
                    FairLoader.stopLoading();
                    Toasty.error(CreateActivity.this, "发表失败", Toasty.LENGTH_SHORT).show();
                }
            });

        });

        mDelete.setOnClickListener(v -> {
            path = null;
            mAdd.setImageResource(R.drawable.ic_img_picker_add);
            mDelete.setVisibility(View.GONE);
        });

        mAdd.setOnClickListener(v -> openAlbum());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE_CHOOSE && resultCode == RESULT_OK && data != null) {
            path = Matisse.obtainPathResult(data).get(0);
            Glide.with(this).load(path).into(mAdd);
            mDelete.setVisibility(View.VISIBLE);
        }
    }

    private void openAlbum() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Matisse.from(this)
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
            EasyPermissions.requestPermissions(this, "打开图库需要存储读取权限", 1001,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
