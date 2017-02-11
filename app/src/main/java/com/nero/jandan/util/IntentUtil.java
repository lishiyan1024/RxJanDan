package com.nero.jandan.util;

import android.app.Activity;
import android.content.Intent;

import com.nero.jandan.mvp.model.FreshNewsPost;
import com.nero.jandan.ui.activity.FreshNewsActivity;

/**
 * Created by lishiyan on 17/1/9.
 */

public class IntentUtil{
	public static final String EXTRA_NEWS_TITLE = "extra_news_title";
	public static final String EXTRA_NEWS_AUTHOR = "extra_news_author";
	public static final String EXTRA_NEWS_ID = "extra_news_id";

	public static final void intentToFreshNewsPostActivity(Activity activity,FreshNewsPost freshNewsPost){
		Intent intent = new Intent(activity,FreshNewsActivity.class);
		intent.putExtra(EXTRA_NEWS_ID,String.valueOf(freshNewsPost.getId()));
		intent.putExtra(EXTRA_NEWS_TITLE,freshNewsPost.getTitle());

		String author = freshNewsPost.getAuthor().getName()+" @ "+freshNewsPost.getTags().get(0).getTitle();
		intent.putExtra(EXTRA_NEWS_AUTHOR,author);
		activity.startActivity(intent);
	}
}
