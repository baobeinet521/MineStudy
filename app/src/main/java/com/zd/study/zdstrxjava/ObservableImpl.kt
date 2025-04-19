package com.zd.study.zdstrxjava

import com.zd.study.zdstrxjava.inter.Action
import com.zd.study.zdstrxjava.inter.Func1
import com.zd.study.zdstrxjava.inter.Observable
import io.reactivex.rxjava3.core.Observer


/**
 * 泛型声明在接口或类上,则类或接口中的方法均可使用 T 类型
 */
class ObservableImpl<T>(t: T) : Observable<T> {
    var t: T? = t
    override fun call(): T? {
        return null
    }

    /*override fun <R> map(fun1: Func1<T, R>?): Observer<R>? {
        TODO("Not yet implemented")
    }*/

    override fun doNext(action: Action<T>): Observable<T> {
        action.callAction(t)
        return this
    }

    override fun <R> map(fun1: Func1<T, R>?): Observable<R>? {
        val ob: Observable<R> = create(fun1!!.call(t))
        return ob
    }

    companion object {
        fun <T> create(t: T): Observable<T> {
            return ObservableImpl(t)
        }
    }

}