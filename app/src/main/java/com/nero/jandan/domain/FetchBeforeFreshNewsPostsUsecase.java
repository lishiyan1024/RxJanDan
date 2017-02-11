package com.nero.jandan.domain;

import com.nero.jandan.mvp.model.FreshNewsPosts;
import com.nero.jandan.repository.interfaces.Repository;

import rx.Observable;

/**
 * Created by lishiyan on 17/1/9.
 */

public class FetchBeforeFreshNewsPostsUsecase implements Usecase{

	private Repository mRepository;
	private int page;

	public FetchBeforeFreshNewsPostsUsecase(final Repository mRepository){
		this.mRepository = mRepository;
	}

	public int getPage(){
		return page;
	}

	public void setPage(final int page){
		this.page = page;
	}

	@Override
	public Observable<FreshNewsPosts> execute(){
		return mRepository.getBeforeFreshNewsPosts(page);
	}
}
