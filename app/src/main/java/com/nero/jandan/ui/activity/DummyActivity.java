package com.nero.jandan.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class DummyActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//临时启动一个不可见的Activity，为了解决InputMetheodManager持有Activity导致的内存泄露
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				finish();
			}
		},500);
	}
}
