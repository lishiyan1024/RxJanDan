package com.nero.jandan.injector.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nero.jandan.JanDanApplication;
import com.nero.jandan.injector.scope.PerApplication;
import com.nero.jandan.interceptor.CacheInterceptor;
import com.nero.jandan.repository.RepositoryImpl;
import com.nero.jandan.repository.interfaces.Repository;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;


/**
 * Created by lishiyan on 16/12/14.
 */
@Module
public class NetworkModule{

	private final JanDanApplication mJanDanApplication;

	public NetworkModule(final JanDanApplication mJanDanApplication){
		this.mJanDanApplication = mJanDanApplication;
	}

	@Provides
	@PerApplication
	Repository provideRepository(Retrofit retrofit){
		return new RepositoryImpl(retrofit);
	}

	@Provides
	@PerApplication
	Retrofit provideRetrofit(){
		String baseUrl = "http://i.jandan.net";
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		//setup cache
		File httpCacheDirectory = new File(JanDanApplication.getContext().getExternalCacheDir(), "responses");
		int cacheSize = 100 * 1024 * 1024; // 100 MiB
		Cache cache = new Cache(httpCacheDirectory, cacheSize);

		OkHttpClient client = new OkHttpClient.Builder()
				.cache(cache)
				.connectTimeout(3,TimeUnit.SECONDS)
				.readTimeout(5,TimeUnit.SECONDS)
				//.addInterceptor(httpLoggingInterceptor)
				.addInterceptor(new CacheInterceptor())
				.build();

		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(baseUrl)
				.client(client)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();

		return retrofit;
	}
}
