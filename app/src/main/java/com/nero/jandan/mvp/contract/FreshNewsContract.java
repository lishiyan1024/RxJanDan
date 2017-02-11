package com.nero.jandan.mvp.contract;

import com.nero.jandan.mvp.model.FreshNews;
import com.nero.jandan.mvp.presenter.BasePresenter;
import com.nero.jandan.mvp.view.BaseView;

/**
 * Created by lishiyan on 17/1/10.
 */

public interface FreshNewsContract{

	interface View extends BaseView{

		void showFreshNewsDetail(FreshNews freshNews);

		void showProgressBar();

		void hideProgressBar();
	}

	interface Presenter extends BasePresenter<View>{

		void refresh(String Id);
	}
}
