package com.nero.jandan.mvp.presenter;

import com.nero.jandan.domain.FetchBeforeFreshNewsPostsUsecase;
import com.nero.jandan.domain.FetchLatestFreshNewsPostsUsecase;
import com.nero.jandan.mvp.contract.FreshNewsPostsContract;
import com.nero.jandan.mvp.model.FreshNewsPosts;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lishiyan on 16/12/14.
 */

public class FreshNewsPostsPresenter implements FreshNewsPostsContract.Presenter{

	private FetchLatestFreshNewsPostsUsecase mLatestFreshNewsPostsUsecase;
	private FetchBeforeFreshNewsPostsUsecase mBeforeFreshNewsPostsUsecase;
	private FreshNewsPostsContract.View mView;
	private CompositeSubscription mCompositeSubscription;

	public FreshNewsPostsPresenter(final FetchLatestFreshNewsPostsUsecase latestFreshNewsPostsUsecase,final FetchBeforeFreshNewsPostsUsecase beforeFreshNewsPostsUsecase){
		this.mLatestFreshNewsPostsUsecase = latestFreshNewsPostsUsecase;
		this.mBeforeFreshNewsPostsUsecase = beforeFreshNewsPostsUsecase;
	}

	@Override
	public void attachView(final FreshNewsPostsContract.View view){
		mView = view;
		mCompositeSubscription = new CompositeSubscription();
	}

	@Override
	public void onCreate(){

	}

	@Override
	public void refresh(){
		mView.showRefreshing();
		Subscription subscription = mLatestFreshNewsPostsUsecase.execute()
				.filter(new Func1<FreshNewsPosts,Boolean>(){
					@Override
					public Boolean call(final FreshNewsPosts freshNewsPosts){
						return freshNewsPosts != null && freshNewsPosts.getPosts().size() != 0;
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<FreshNewsPosts>(){
					@Override
					public void onCompleted(){

					}

					@Override
					public void onError(final Throwable e){
						mView.hideRefreshing();
						mView.showError(e.getLocalizedMessage());
					}

					@Override
					public void onNext(final FreshNewsPosts freshNewsPosts){
						mView.hideRefreshing();
						mView.showError("refresh success!!!");
						mView.showFreshNewsPosts(freshNewsPosts);
					}
				});
		mCompositeSubscription.add(subscription);
	}

	@Override
	public void loadMore(final int oldPage){
		//mView.showRefreshing();

		mBeforeFreshNewsPostsUsecase.setPage(oldPage);
		Subscription subscription = mBeforeFreshNewsPostsUsecase.execute()
				.filter(new Func1<FreshNewsPosts,Boolean>(){
					@Override
					public Boolean call(final FreshNewsPosts freshNewsPosts){
						return freshNewsPosts != null && freshNewsPosts.getPosts().size() != 0;
					}
				})
	            .subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
	            .subscribe(new Observer<FreshNewsPosts>(){
		            @Override
		            public void onCompleted(){

		            }

		            @Override
		            public void onError(final Throwable e){
			            mView.showError(e.getLocalizedMessage());
			            mView.hideRefreshing();
		            }

		            @Override
		            public void onNext(final FreshNewsPosts freshNewsPosts){
			            mView.appendFreshNewsPosts(freshNewsPosts);
		            }
	            });
		mCompositeSubscription.add(subscription);
	}
}
