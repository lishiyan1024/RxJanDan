package com.nero.jandan.injector.component;

import com.nero.jandan.injector.module.ActivityModule;
import com.nero.jandan.injector.module.FreshNewsModule;
import com.nero.jandan.injector.scope.PerActivity;
import com.nero.jandan.ui.fragment.FreshNewsFragment;

import dagger.Component;

/**
 * Created by lishiyan on 17/1/10.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class,FreshNewsModule.class})
public interface FreshNewsComponent{

	void inject(FreshNewsFragment fragment);
}
