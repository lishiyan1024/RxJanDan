package com.nero.jandan.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.nero.jandan.R;

/**
 * Created by lishiyan on 16/12/6.
 */

public abstract class BaseActivity extends AppCompatActivity{

	protected Toolbar mToolbar;

	protected abstract int getContentViewLayoutId();

	private NetworkChangeReceiver receiver;

	@Override
	protected void onCreate(@Nullable final Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setContentView(getContentViewLayoutId());
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		mToolbar = (Toolbar) findViewById(R.id.actionbarToolbar);
		setupActionBar();
	}

	@Override
	protected void onResume(){
		super.onResume();

		IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
		receiver = new NetworkChangeReceiver();
		registerReceiver(receiver,intentFilter);
	}

	@Override
	protected void onPause(){
		super.onPause();
		unregisterReceiver(receiver);
	}

	private void setupActionBar() {
		setSupportActionBar(mToolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null && !(this instanceof NavigationDrawerActivity)) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	class NetworkChangeReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(final Context context,final Intent intent){

			ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			if(networkInfo != null && networkInfo.isAvailable()){
				Toast.makeText(context,"Network is available",Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(context,"Network is unavailable",Toast.LENGTH_SHORT).show();
			}
		}
	}
}
