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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.qiming.R;
import com.example.qiming.mvp.model.entity.CourseEntity;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

public class HomeItemContextAdapter extends DefaultAdapter<CourseEntity> {
    public HomeItemContextAdapter(List<CourseEntity> infos) {
        super(infos);
    }

    @NonNull
    @Override
    public BaseHolder<CourseEntity> getHolder(@NonNull View v, int viewType) {

        return new ContextItemBaseHolder(v);

    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.context_item_ontext;
    }
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount; //列数
        private int spacing; //间隔
        private boolean includeEdge; //是否包含边缘

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //这里是关键，需要根据你有几列来判断
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }

        }

    }

    public RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new GridLayoutManager(context, 2);
    }

    public class ContextItemBaseHolder extends BaseHolder<CourseEntity> {
        @BindView(R.id.img_context_item_background)
        ImageView ContextItemBackgroundImg;
        @BindView(R.id.txt_context_item_title)
        TextView ContextItemTitleTxt;
        public ContextItemBaseHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void setData(@NonNull CourseEntity data, int position) {
            ContextItemTitleTxt.setText(data.getCourseName());

            RequestOptions options = new RequestOptions().bitmapTransform(new RoundedCorners(10));//图片圆角为30
            Glide.with(ContextItemTitleTxt.getContext()).load(data.getBackgroundId()) //图片地址
                     .apply(options)
                     .into(ContextItemBackgroundImg);

        }
    }
}
