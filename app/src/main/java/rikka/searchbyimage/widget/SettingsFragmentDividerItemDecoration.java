package rikka.searchbyimage.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import rikka.searchbyimage.R;

/**
 * Created by Rikka on 2016/1/4.
 */
public class SettingsFragmentDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;


    public SettingsFragmentDividerItemDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildLayoutPosition(view) < 1) {
            return;
        }

        outRect.top = mDivider.getIntrinsicHeight();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();


        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            boolean canDraw = ((i < childCount - 1)
                    && parent.getChildAt(i + 1).findViewById(android.R.id.summary) != null
                    && child.findViewById(android.R.id.summary) != null);

            if (!canDraw) {
                continue;
            }

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setColorFilter(0, PorterDuff.Mode.DST);
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}