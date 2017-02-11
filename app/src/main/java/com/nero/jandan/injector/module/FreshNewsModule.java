package com.nero.jandan.injector.module;

import com.nero.jandan.domain.FetchFreshNewsDetailUsecase;
import com.nero.jandan.mvp.contract.FreshNewsContract;
import com.nero.jandan.mvp.presenter.FreshNewsPresenter;
import com.nero.jandan.repository.interfaces.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lishiyan on 17/1/10.
 */
@Module
public class FreshNewsModule{

	@Provides
	public FetchFreshNewsDetailUsecase getFreshNewsDetailUsecase(Repository repository){
		return new FetchFreshNewsDetailUsecase(repository);
	}

	@Provides
	public FreshNewsContract.Presenter getFreshNewsPresenter(FetchFreshNewsDetailUsecase freshNewsDetailUsecase){
		return new FreshNewsPresenter(freshNewsDetailUsecase);
	}
}
