package com.nero.jandan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nero.jandan.R;
import com.nero.jandan.injector.component.ApplicationComponent;
import com.nero.jandan.injector.component.DaggerFreshNewsPostsComponent;
import com.nero.jandan.injector.component.FreshNewsPostsComponent;
import com.nero.jandan.injector.module.ActivityModule;
import com.nero.jandan.injector.module.FreshNewsPostsModule;
import com.nero.jandan.mvp.contract.FreshNewsPostsContract;
import com.nero.jandan.mvp.model.FreshNewsPosts;
import com.nero.jandan.ui.activity.NavigationDrawerActivity;
import com.nero.jandan.ui.adapter.FreshNewsPostsAdapter;
import com.nero.jandan.widget.LoadMoreRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreshNewsPostsFragment extends Fragment implements FreshNewsPostsContract.View{

	public static final String TAG = FreshNewsPostsFragment.class.getSimpleName();

	@Inject
	FreshNewsPostsContract.Presenter freshNewsPostsPresenter;
	@BindView(R.id.recyclerView)
	LoadMoreRecyclerView recyclerView;
	@BindView(R.id.swipeRefreshLayout)
	SwipeRefreshLayout swipeRefreshLayout;

	private LinearLayoutManager mLinearLayoutManager;
	private FreshNewsPostsAdapter adapter;
	private boolean isDataLoaded;

	private int currentPage = 0;

	@Override
	public void onCreate(@Nullable final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		injectDependences();
		freshNewsPostsPresenter.attachView(this);
		adapter = new FreshNewsPostsAdapter();
	}

	private void injectDependences(){
		ApplicationComponent applicationComponent = ((NavigationDrawerActivity)getActivity()).getApplicationComponent();
		FreshNewsPostsComponent freshNewsPostsComponent = DaggerFreshNewsPostsComponent.builder()
	            .applicationComponent(applicationComponent)
				.activityModule(new ActivityModule(getActivity()))
				.freshNewsPostsModule(new FreshNewsPostsModule())
				.build();
		freshNewsPostsComponent.inject(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
	                         Bundle savedInstanceState){
		//Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.fragment_fresh_news_posts,container,false);
//		ButterKnife.bind(this,rootView);

		recyclerView = (LoadMoreRecyclerView)rootView.findViewById(R.id.recyclerView);
		swipeRefreshLayout = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeRefreshLayout);

		return rootView;
	}

	@Override
	public void onViewCreated(final View view,@Nullable final Bundle savedInstanceState){
		super.onViewCreated(view,savedInstanceState);
		mLinearLayoutManager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(mLinearLayoutManager);

		swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
			@Override
			public void onRefresh(){
				freshNewsPostsPresenter.refresh();
			}
		});

		recyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.onLoadMoreListener(){
			@Override
			public void onLoadMore(){
				freshNewsPostsPresenter.loadMore(currentPage+1);
			}

			@Override
			public void onScrolled(final RecyclerView recyclerView,final int dx,final int dy){

			}
		});
	}

	@Override
	public void onActivityCreated(@Nullable final Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		recyclerView.setAdapter(adapter);
		swipeRefreshLayout.post(new Runnable(){
			@Override
			public void run(){
				if(!isDataLoaded){
					freshNewsPostsPresenter.refresh();
				}
			}
		});
	}

	@Override
	public void showError(final String errorString){
		Toast.makeText(getContext(), errorString, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showRefreshing(){
		swipeRefreshLayout.setRefreshing(true);
	}

	@Override
	public void hideRefreshing(){
		swipeRefreshLayout.setRefreshing(false);
	}

	@Override
	public void showFreshNewsPosts(final FreshNewsPosts news){
		if(news == null){
			isDataLoaded = false;
		}else{
			isDataLoaded = true;
			currentPage = 1;
			adapter.setList(news);
		}
	}

	@Override
	public void appendFreshNewsPosts(final FreshNewsPosts news){
		currentPage++;
		adapter.appendList(news);
	}
}
