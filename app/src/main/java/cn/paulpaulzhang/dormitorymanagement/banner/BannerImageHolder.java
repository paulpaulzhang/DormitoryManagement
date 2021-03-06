package cn.paulpaulzhang.dormitorymanagement.banner;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import cn.paulpaulzhang.dormitorymanagement.R;

/**
 * 包名: cn.paulpaulzhang.fair.sc.main.banner
 * 创建时间: 7/13/2019
 * 创建人: zlm31
 * 描述:
 */
public class BannerImageHolder extends Holder<Integer> {
    private AppCompatImageView mImageView;

    public BannerImageHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.scroll_iv);
    }

    @Override
    public void updateUI(Integer data) {
        Glide.with(itemView).load(data).centerCrop().into(mImageView);
    }
}
