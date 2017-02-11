package com.nero.jandan.api;

import com.nero.jandan.mvp.model.FreshNews;
import com.nero.jandan.mvp.model.FreshNewsPosts;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lishiyan on 16/12/14.
 */

public interface ApiService{

	@GET("/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&page=%s&custom_fields=thumb_c,views&dev=1&page=1")
	Observable<FreshNewsPosts> getLatestFreshNewsPosts();

	@GET("/")
	Observable<FreshNewsPosts> getBeforeFreshNewsPosts(@Query("oxwlxojflwblxbsapi") String api,
	                                                   @Query("include") String inclue,
	                                                   @Query("custom_fields") String custom_fields,
	                                                   @Query("dev") int dev,
	                                                   @Query("page") int page);

	@GET("/")
	Observable<FreshNews> getFreshNewsDetail(@Query("oxwlxojflwblxbsapi") String api,
	                                         @Query("include") String include,
	                                         @Query("id") String Id);
}
