package com.nero.jandan.mvp.presenter;

import com.nero.jandan.domain.FetchFreshNewsDetailUsecase;
import com.nero.jandan.mvp.contract.FreshNewsContract;
import com.nero.jandan.mvp.model.FreshNews;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lishiyan on 17/1/10.
 */

public class FreshNewsPresenter implements FreshNewsContract.Presenter{

	private FetchFreshNewsDetailUsecase mFreshNewsDetailUsecase;
	private FreshNewsContract.View mView;
	private CompositeSubscription mCompositeSubscription;

	public FreshNewsPresenter(final FetchFreshNewsDetailUsecase mFreshNewsDetailUsecase){
		this.mFreshNewsDetailUsecase = mFreshNewsDetailUsecase;
	}

	@Override
	public void attachView(final FreshNewsContract.View view){
		mView = view;
		mCompositeSubscription = new CompositeSubscription();
	}

	@Override
	public void onCreate(){

	}

	@Override
	public void refresh(final String Id){
		mView.showProgressBar();
		mFreshNewsDetailUsecase.setId(Id);
		Subscription subscription = mFreshNewsDetailUsecase.execute()
				.filter(new Func1<FreshNews,Boolean>(){
					@Override
					public Boolean call(final FreshNews freshNews){
						return freshNews != null;
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<FreshNews>(){
					@Override
					public void onCompleted(){

					}

					@Override
					public void onError(final Throwable e){
						mView.hideProgressBar();
					}

					@Override
					public void onNext(final FreshNews freshNews){
						mView.hideProgressBar();
						mView.showFreshNewsDetail(freshNews);
					}
				});
		mCompositeSubscription.add(subscription);
	}
}
