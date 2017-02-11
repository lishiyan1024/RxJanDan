package com.nero.jandan.injector.component;

import android.app.Application;

import com.nero.jandan.JanDanApplication;
import com.nero.jandan.injector.module.ApplicationModule;
import com.nero.jandan.injector.module.NetworkModule;
import com.nero.jandan.injector.scope.PerApplication;
import com.nero.jandan.repository.interfaces.Repository;

import dagger.Component;

/**
 * Created by lishiyan on 16/12/15.
 */
@PerApplication
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent{

	Application application();

	JanDanApplication janDanApplication();

	Repository repository();
}
