package com.nero.jandan;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.nero.jandan.injector.component.ApplicationComponent;
import com.nero.jandan.injector.component.DaggerApplicationComponent;
import com.nero.jandan.injector.module.ApplicationModule;
import com.nero.jandan.injector.module.NetworkModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by lishiyan on 16/12/14.
 */

public class JanDanApplication extends Application{

	private ApplicationComponent mApplicationComponent;
	private static Context sContext;
	private static RefWatcher sRefWatcher;

	@Override
	public void onCreate(){
		super.onCreate();
		sContext = getApplicationContext();
		setStrictMode();
		initLeakCanary();
		initStetho();
		setupInjector();
	}

	private void setupInjector(){
		mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
				.networkModule(new NetworkModule(this))
				.build();
	}

	private void initLeakCanary() {
		sRefWatcher = LeakCanary.install(this);
	}

	private void initStetho() {
		Stetho.initializeWithDefaults(this);
	}

	private void setStrictMode() {
		if (BuildConfig.DEBUG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
		}
	}

	public static Context getContext(){
		return sContext;
	}

	public ApplicationComponent getApplicationComponent(){
		return mApplicationComponent;
	}
}
