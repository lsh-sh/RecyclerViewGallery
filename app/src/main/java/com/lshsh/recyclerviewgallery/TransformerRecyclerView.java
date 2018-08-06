package com.lshsh.recyclerviewgallery;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

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
public class TransformerRecyclerView extends RecyclerView
{
	private static final float SCALE = 0.75f;
	private int itemWidth;
	private int lastPos;
	private int currentPos;
	private boolean canScroll;
	private int itemCount;

	public TransformerRecyclerView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		itemWidth = dp2px(context, 240.0f);
		this.addOnScrollListener(new OnScrollListener()
		{
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy)
			{
				if (dx != 0)
				{
					int scrollX = computeHorizontalScrollOffset();
					computerTransformer(scrollX);
				}
			}

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int newState)
			{
				if (newState == SCROLL_STATE_IDLE)
				{
					lastPos = currentPos;
				}
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		switch (e.getAction())
		{
			case MotionEvent.ACTION_UP:
				compuerCurrentPos(computeHorizontalScrollOffset());
				break;
		}
		return super.onTouchEvent(e);
	}

	@Override
	public void onChildAttachedToWindow(View child)
	{

		if (itemCount == 0)
		{
			itemCount = getLayoutManager().getItemCount();
		}

		int position = getChildLayoutPosition(child);
		if (position == currentPos)
		{
			child.setScaleY(1.0f);
		}
		else
		{
			child.setScaleY(SCALE);
		}
	}

	@Override
	public boolean fling(int velocityX, int velocityY)
	{

		if (!canScroll && Math.abs(velocityX) > getMinFlingVelocity())
		{
			if (velocityX > 0)
			{
				currentPos++;
			}
			else
			{
				currentPos--;
			}
		}
		if (currentPos < 0)
		{
			currentPos = 0;
		}
		if (currentPos >= itemCount)
		{
			currentPos = itemCount - 1;
		}
		smoothToPosition();
		return super.fling(velocityX, velocityY);
	}

	/**
	 * 计算出当前滑动到的位置
	 *
	 * @param scrollX
	 */
	private void compuerCurrentPos(int scrollX)
	{
		lastPos = currentPos;
		canScroll = false;
		if (currentPos * itemWidth - scrollX > itemWidth / 3)
		{
			currentPos--;
			canScroll = true;
		}
		else if (scrollX - currentPos * itemWidth > itemWidth / 3)
		{
			currentPos++;
			canScroll = true;
		}
	}

	private void smoothToPosition()
	{
		this.post(new Runnable()
		{
			@Override
			public void run()
			{
				smoothScrollToPosition(currentPos);
			}
		});
	}

	/**
	 * 变换计算
	 *
	 * @param scrollX
	 */
	private void computerTransformer(int scrollX)
	{
		View leftView = getLayoutManager().findViewByPosition(lastPos - 1);
		View currentView = getLayoutManager().findViewByPosition(lastPos);
		View rightView = getLayoutManager().findViewByPosition(lastPos + 1);

		int offset = Math.abs(scrollX - lastPos * itemWidth);

		if (offset > itemWidth)
		{
			return;
		}
		float percent = offset * 1.0f / itemWidth;

		if (leftView != null)
		{
			leftView.setScaleY(SCALE + (1 - SCALE) * percent);
		}


		if (currentView != null)
		{
			currentView.setScaleY(1.0f - (1.0f - SCALE) * percent);
		}


		if (rightView != null)
		{
			rightView.setScaleY(SCALE + (1 - SCALE) * percent);
		}

	}

	public static int dp2px(Context context, float dpVal)
	{
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
	}
}
