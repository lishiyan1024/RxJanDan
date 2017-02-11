package com.nero.jandan.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.nero.jandan.JanDanApplication;
import com.nero.jandan.R;
import com.nero.jandan.injector.component.ApplicationComponent;
import com.nero.jandan.ui.fragment.FreshNewsFragment;
import com.nero.jandan.util.IntentUtil;

public class FreshNewsActivity extends BaseAppcompatActivity{
	private static final String TAG = FreshNewsActivity.class.getSimpleName();

	@Override
	protected int getContentViewLayoutId(){
		return R.layout.activity_fresh_news;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		if(savedInstanceState == null){
			String newsId = getIntent().getStringExtra(IntentUtil.EXTRA_NEWS_ID);
			String title = getIntent().getStringExtra(IntentUtil.EXTRA_NEWS_TITLE);
			String author = getIntent().getStringExtra(IntentUtil.EXTRA_NEWS_AUTHOR);

			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			FreshNewsFragment freshNewsFragment = FreshNewsFragment.newInstance(newsId,title,author);
			transaction.add(R.id.webViewContainer,freshNewsFragment,FreshNewsFragment.TAG);
			transaction.commit();
		}
	}

	public ApplicationComponent getApplicationComponent(){
		ApplicationComponent applicationComponent = ((JanDanApplication)getApplication()).getApplicationComponent();
		return applicationComponent;
	}
}
