package com.nero.jandan.domain;

import com.nero.jandan.mvp.model.FreshNews;
import com.nero.jandan.repository.interfaces.Repository;

import rx.Observable;

/**
 * Created by lishiyan on 17/1/10.
 */

public class FetchFreshNewsDetailUsecase implements Usecase{

	private Repository mRepository;

	private String Id;

	public FetchFreshNewsDetailUsecase(final Repository repository){
		this.mRepository = repository;
	}

	public String getId(){
		return Id;
	}

	public void setId(final String id){
		Id = id;
	}

	@Override
	public Observable<FreshNews> execute(){
		return mRepository.getFreshNewsDetail(Id);
	}
}
