package com.nero.jandan.mvp.presenter;

import com.nero.jandan.mvp.view.BaseView;

/**
 * Created by lishiyan on 16/12/14.
 */

public interface BasePresenter<T extends BaseView>{

	void attachView(T view);

	void onCreate();

}
