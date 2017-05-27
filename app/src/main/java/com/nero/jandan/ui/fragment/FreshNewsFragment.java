package com.nero.jandan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.nero.jandan.R;
import com.nero.jandan.injector.component.ApplicationComponent;
import com.nero.jandan.injector.component.DaggerFreshNewsComponent;
import com.nero.jandan.injector.component.FreshNewsComponent;
import com.nero.jandan.injector.module.ActivityModule;
import com.nero.jandan.injector.module.FreshNewsModule;
import com.nero.jandan.mvp.contract.FreshNewsContract;
import com.nero.jandan.mvp.model.FreshNews;
import com.nero.jandan.ui.activity.FreshNewsActivity;
import com.nero.jandan.util.HtmlUtil;
import com.nero.jandan.util.IntentUtil;

import javax.inject.Inject;

import butterknife.BindView;


public class FreshNewsFragment extends Fragment implements FreshNewsContract.View{
	public static final String TAG = FreshNewsFragment.class.getSimpleName();

	@Inject
	FreshNewsContract.Presenter freshNewsPresenter;
	@BindView(R.id.wvFreshNews)
	WebView wvFreshNews;
//	@BindView(R.id.progressBar)
	//	ProgressBar progressBar;

	private String newsId;
	private String newsTitle;
	private String newsAuthor;

	private Toolbar toolbar;

	@Override
	public void onPause(){
		super.onPause();
	}

	@Override
	public void onResume(){
		super.onResume();
	}

	@Override
	public void onStart(){
		super.onStart();
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
	}

	@Override
	public void onCreate(@Nullable final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		injectDependences();
		freshNewsPresenter.attachView(this);
		if(getArguments() != null){
			newsId = getArguments().getString(IntentUtil.EXTRA_NEWS_ID);
			newsTitle = getArguments().getString(IntentUtil.EXTRA_NEWS_TITLE);
			newsAuthor = getArguments().getString(IntentUtil.EXTRA_NEWS_AUTHOR);
		}
	}

	private void injectDependences(){
		ApplicationComponent applicationComponent = ((FreshNewsActivity)getActivity()).getApplicationComponent();
		FreshNewsComponent freshNewsComponent = DaggerFreshNewsComponent.builder()
				                                        .applicationComponent(applicationComponent)
				                                        .activityModule(new ActivityModule(getActivity()))
				                                        .freshNewsModule(new FreshNewsModule())
				                                        .build();
		freshNewsComponent.inject(this);
	}

	public static FreshNewsFragment newInstance(String newsId,String title,String author){
		FreshNewsFragment fragment = new FreshNewsFragment();
		Bundle bundle = new Bundle();
		bundle.putString(IntentUtil.EXTRA_NEWS_ID,newsId);
		bundle.putString(IntentUtil.EXTRA_NEWS_TITLE,title);
		bundle.putString(IntentUtil.EXTRA_NEWS_AUTHOR,author);
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
	                         Bundle savedInstanceState){
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_news,container,false);
		//ButterKnife.bind(this,rootView);
		wvFreshNews = (WebView)rootView.findViewById(R.id.wvFreshNews);
		wvFreshNews.getSettings().setJavaScriptEnabled(true);
		//progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
		toolbar = (Toolbar)rootView.findViewById(R.id.actionbarToolbar);

//		wvFreshNews.setOnScrollChangeListener(scrollChangeListener);
		return rootView;
	}

	@Override
	public void onViewCreated(final View view,@Nullable final Bundle savedInstanceState){
		super.onViewCreated(view,savedInstanceState);
		freshNewsPresenter.refresh(newsId);
	}

	@Override
	public void hideProgressBar(){
		//progressBar.setVisibility(View.INVISIBLE);
	}

	@Override
	public void showProgressBar(){
		//progressBar.setVisibility(View.VISIBLE);
	}

	@Override
	public void showFreshNewsDetail(final FreshNews freshNews){
		String content = freshNews.getPost().getContent();
		String htmlStr = HtmlUtil.getHtml(content,newsTitle,newsAuthor);
		wvFreshNews.loadDataWithBaseURL("",htmlStr,"text/html","utf-8","");
	}
}
