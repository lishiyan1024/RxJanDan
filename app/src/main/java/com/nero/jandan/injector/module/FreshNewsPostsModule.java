package com.nero.jandan.injector.module;

import com.nero.jandan.domain.FetchBeforeFreshNewsPostsUsecase;
import com.nero.jandan.domain.FetchLatestFreshNewsPostsUsecase;
import com.nero.jandan.mvp.contract.FreshNewsPostsContract;
import com.nero.jandan.mvp.presenter.FreshNewsPostsPresenter;
import com.nero.jandan.repository.interfaces.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lishiyan on 16/12/15.
 */
@Module
public class FreshNewsPostsModule{

	@Provides
	public FetchLatestFreshNewsPostsUsecase getLatestFreshNewsPostsUsecase(Repository repository){
		return new FetchLatestFreshNewsPostsUsecase(repository);
	}

	@Provides
	public FetchBeforeFreshNewsPostsUsecase getBeforeFreshNewsPostsUsecase(Repository repository){
		return new FetchBeforeFreshNewsPostsUsecase(repository);
	}

	@Provides
	public FreshNewsPostsContract.Presenter getFreshNewsPostsPresenter(FetchLatestFreshNewsPostsUsecase latestFreshNewsUsecase,FetchBeforeFreshNewsPostsUsecase beforeFreshNewsUsecase){
		return new FreshNewsPostsPresenter(latestFreshNewsUsecase,beforeFreshNewsUsecase);
	}
}
