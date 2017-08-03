package com.example.library.utils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhouyiran on 2017/7/18.
 */

public class RxSchedulerUtils {

    public static <T> Observable.Transformer<T, T> normalSchedulersTransformer() {
        return observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
