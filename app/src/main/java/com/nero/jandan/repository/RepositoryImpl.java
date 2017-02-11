package com.nero.jandan.repository;

import com.nero.jandan.api.ApiService;
import com.nero.jandan.repository.interfaces.Repository;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by lishiyan on 16/12/14.
 */

public class RepositoryImpl implements Repository{

	private final ApiService mApiService;

	public RepositoryImpl(Retrofit retrofit){
		mApiService = retrofit.create(ApiService.class);
	}

	@Override
	public Observable getLatestFreshNewsPosts(){
		return mApiService.getLatestFreshNewsPosts();
	}

	@Override
	public Observable getBeforeFreshNewsPosts(int oldPage){
		return mApiService.getBeforeFreshNewsPosts("get_recent_posts",
				"url,date,tags,author,title,comment_count,custom_fields",
				"thumb_c,views",
				1,
				oldPage);
	}

	@Override
	public Observable getFreshNewsDetail(final String Id){
		return mApiService.getFreshNewsDetail("get_post",
				"content",
				Id);
	}
}
