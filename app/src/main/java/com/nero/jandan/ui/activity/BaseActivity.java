package com.nero.jandan.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nero.jandan.R;

/**
 * Created by lishiyan on 16/12/6.
 */

public abstract class BaseActivity extends AppCompatActivity{

	protected Toolbar mToolbar;

	protected abstract int getContentViewLayoutId();

	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(getContentViewLayoutId());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mToolbar = (Toolbar) findViewById(R.id.actionbarToolbar);

		setupActionBar();
	}

	private void setupActionBar() {
		setSupportActionBar(mToolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null && !(this instanceof NavigationDrawerActivity)) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}
}
