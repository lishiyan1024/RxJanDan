package com.nero.jandan.domain;

import rx.Observable;

/**
 * Created by lishiyan on 16/12/14.
 */

public interface Usecase<T>{
	Observable<T> execute();
}
