package com.nero.jandan.injector.component;

import com.nero.jandan.injector.module.ActivityModule;
import com.nero.jandan.injector.module.FreshNewsPostsModule;
import com.nero.jandan.injector.scope.PerActivity;
import com.nero.jandan.ui.fragment.FreshNewsPostsFragment;

import dagger.Component;

/**
 * Created by lishiyan on 16/12/15.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FreshNewsPostsModule.class})
public interface FreshNewsPostsComponent{

	void inject(FreshNewsPostsFragment freshNewsPostsFragment);
}
