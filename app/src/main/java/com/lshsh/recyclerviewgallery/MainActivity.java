package com.lshsh.recyclerviewgallery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.WindowManager;

public class MainActivity extends Activity
{

	private int[] datas = {R.mipmap.pic1,R.mipmap.pic2,R.mipmap.pic3,R.mipmap.pic4,};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TransformerRecyclerView trv_view = findViewById(R.id.trv_view);
		TransformerAdapter transformerAdapter = new TransformerAdapter(datas);
		SmoothScrollLayoutManager smoothScrollLayoutManager = new SmoothScrollLayoutManager(this, LinearLayoutManager.HORIZONTAL);
		trv_view.setAdapter(transformerAdapter);
		trv_view.setLayoutManager(smoothScrollLayoutManager);
	}
}
