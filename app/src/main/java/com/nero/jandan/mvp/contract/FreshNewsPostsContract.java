package com.nero.jandan.mvp.contract;

import com.nero.jandan.mvp.model.FreshNewsPosts;
import com.nero.jandan.mvp.presenter.BasePresenter;
import com.nero.jandan.mvp.view.BaseView;

/**
 * Created by lishiyan on 16/12/14.
 */

public interface FreshNewsPostsContract{

	interface View extends BaseView{

		void showError(String errorString);

		void showRefreshing();

		void hideRefreshing();

		void showFreshNewsPosts(FreshNewsPosts news);

		void appendFreshNewsPosts(FreshNewsPosts news);
	}

	interface Presenter extends BasePresenter<View>{

		void refresh();

		void loadMore(int page);
	}
}
