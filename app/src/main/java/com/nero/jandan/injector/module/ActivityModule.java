package com.nero.jandan.injector.module;

import android.app.Activity;
import android.content.Context;

import com.nero.jandan.injector.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lishiyan on 16/12/14.
 */
@Module
public class ActivityModule{

	private final Activity mActivity;

	public ActivityModule(Activity mActivity){
		this.mActivity = mActivity;
	}

	@Provides
	@PerActivity
	public Context context(){
		return mActivity;
	}
}
