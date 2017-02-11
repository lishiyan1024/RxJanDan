package com.nero.jandan.domain;

import com.nero.jandan.mvp.model.FreshNewsPosts;
import com.nero.jandan.repository.interfaces.Repository;

import rx.Observable;

/**
 * Created by lishiyan on 16/12/14.
 */

public class FetchLatestFreshNewsPostsUsecase implements Usecase<FreshNewsPosts>{

	private Repository mRepository;

	public FetchLatestFreshNewsPostsUsecase(final Repository mRepository){
		this.mRepository = mRepository;
	}

	@Override
	public Observable<FreshNewsPosts> execute(){
		return mRepository.getLatestFreshNewsPosts();
	}
}
