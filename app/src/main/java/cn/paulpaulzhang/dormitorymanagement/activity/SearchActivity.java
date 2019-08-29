package cn.paulpaulzhang.dormitorymanagement.activity;

import android.os.Bundle;
import android.transition.Explode;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;

import com.gyf.immersionbar.ImmersionBar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import cn.paulpaulzhang.dormitorymanagement.R;
import cn.paulpaulzhang.dormitorymanagement.R2;
import cn.paulpaulzhang.dormitorymanagement.base.BaseActivity;
import cn.paulpaulzhang.dormitorymanagement.util.KeyBoardUtil;
import es.dmoral.toasty.Toasty;

/**
 * 包名：cn.paulpaulzhang.fair.sc.main.search
 * 创建时间：8/28/19
 * 创建人： paulpaulzhang
 * 描述：
 */
public class SearchActivity extends BaseActivity {

    @BindView(R2.id.et_content)
    AppCompatEditText mContent;

    @BindView(R2.id.iv_back)
    AppCompatImageView mBack;

    @BindView(R2.id.iv_delete)
    AppCompatImageView mDelete;

    @BindView(R2.id.iv_search)
    AppCompatImageView mSearch;

    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarDarkFont(true)
                .init();

        getWindow().setExitTransition(new Explode());
        getWindow().setEnterTransition(new Explode());

        mBack.setOnClickListener(v -> {
            KeyBoardUtil.hideKeyboard(mContent);
            finishAfterTransition();
        });
        mDelete.setOnClickListener(v -> mContent.setText(""));

        KeyBoardUtil.showKeyboard(mContent);

        mContent.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEND
                    || i == EditorInfo.IME_ACTION_DONE
                    || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                search();
            }
            return false;
        });
    }

    @OnClick(R2.id.iv_search)
    void search() {
        KeyBoardUtil.hideKeyboard(mContent);
        Toasty.info(this, Objects.requireNonNull(mContent.getText()).toString(), Toasty.LENGTH_SHORT).show();
        //TODO
    }
}
