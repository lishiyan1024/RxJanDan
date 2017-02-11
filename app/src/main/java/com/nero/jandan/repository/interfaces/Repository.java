package com.nero.jandan.repository.interfaces;

import rx.Observable;

/**
 * Created by lishiyan on 16/12/14.
 */

public interface Repository{

	/**
	 * 获取最新发布的新鲜事
	 * @return
	 */
	Observable getLatestFreshNewsPosts();

	/**
	 * 获取旧的新鲜事
	 * @param oldPage //新鲜事列表的页码
	 * @return
	 */
	Observable getBeforeFreshNewsPosts(int oldPage);

	/**
	 * 获取某个新鲜事的详情
	 * @param Id 该新鲜事的ID
	 * @return
	 */
	Observable getFreshNewsDetail(String Id);
}
