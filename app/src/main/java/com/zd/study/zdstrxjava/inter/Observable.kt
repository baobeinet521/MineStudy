package com.zd.study.zdstrxjava.inter

/**
 * 将泛型声明放在接口
 */
interface Observable<T> {
    fun call(): T?

    fun <R> map(fun1: Func1<T, R>?): Observable<R>?

    fun doNext(action: Action<T>): Observable<T>
}