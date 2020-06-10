package com.example.qiming.mvp.model.mvp.ui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.example.qiming.R;
import com.example.qiming.mvp.model.entity.OptionEntity;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

public class HomeItemOptionAdapter extends DefaultAdapter<OptionEntity> {
    public HomeItemOptionAdapter(List<OptionEntity> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<OptionEntity> getHolder(@NonNull View v, int viewType) {
        return new OptionItemBaseHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.option_item;
    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        GridLayoutManager gll = new GridLayoutManager(context, 5);
        return gll;
    }


    public class OptionItemBaseHolder extends BaseHolder<OptionEntity> {
        @BindView(R.id.option_icon_image)
        ImageView mOptionIconImage;
        @BindView(R.id.option_name_txt)
        TextView mOptionNameTxt;

        public OptionItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull OptionEntity data, int position) {
            Glide.with(mOptionIconImage.getContext()).load(data.getImageId()).into(mOptionIconImage);
            if (data.getName() == null)
                mOptionNameTxt.setText(data.getNameId());
            else
                mOptionNameTxt.setText(data.getName());
        }
    }
}
