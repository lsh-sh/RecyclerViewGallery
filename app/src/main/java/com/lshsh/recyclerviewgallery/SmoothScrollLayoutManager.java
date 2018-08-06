package com.lshsh.recyclerviewgallery;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

/**
 * ================================================
 * 作    者：lshsh
 * 版    本：5.0.2
 * 创建日期：2018/7/31
 * 描    述：
 * 修订历史：
 * <p>
 * <p>
 * ================================================
 */
public class SmoothScrollLayoutManager extends LinearLayoutManager
{
	public SmoothScrollLayoutManager(Context context, int orientation)
	{
		super(context, orientation, false);
	}

	@Override
	public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position)
	{

		LinearSmoothScroller smoothScroller =
				new LinearSmoothScroller(recyclerView.getContext())
				{
					// 返回：滑过1px时经历的时间(ms)。
					@Override
					protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics)
					{
						return 200f / displayMetrics.densityDpi;
					}
				};
		smoothScroller.setTargetPosition(position);
		startSmoothScroll(smoothScroller);
	}
}
