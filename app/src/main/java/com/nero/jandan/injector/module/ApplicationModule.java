package com.nero.jandan.injector.module;

import android.app.Application;

import com.nero.jandan.JanDanApplication;
import com.nero.jandan.injector.scope.PerApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lishiyan on 16/12/15.
 */
@Module
public class ApplicationModule{

	private final JanDanApplication mApplication;

	public ApplicationModule( JanDanApplication mApplication){
		this.mApplication = mApplication;
	}

	@Provides
	@PerApplication
	public Application provideApplication(){
		return mApplication;
	}

	@Provides
	@PerApplication
	public JanDanApplication provideJanDanApplication(){
		return mApplication;
	}
}
