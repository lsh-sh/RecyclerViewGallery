package com.lshsh.recyclerviewgallery;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
public class TransformerAdapter extends RecyclerView.Adapter<TransformerAdapter.BaseViewHolder>
{
	private int[] datas;

	public TransformerAdapter(int[] datas)
	{
		this.datas = datas;
	}

	@Override
	public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, null);
		return new BaseViewHolder(view);
	}

	@Override
	public void onBindViewHolder(BaseViewHolder holder, int position)
	{
		holder.iv_pic.setImageResource(datas[position]);
	}

	@Override
	public int getItemCount()
	{
		if (datas == null)
		{
			return 0;
		}
		return datas.length;
	}

	public class BaseViewHolder extends RecyclerView.ViewHolder
	{
		ImageView iv_pic;

		public BaseViewHolder(View itemView)
		{
			super(itemView);
			iv_pic = itemView.findViewById(R.id.iv_pic);
		}
	}
}
