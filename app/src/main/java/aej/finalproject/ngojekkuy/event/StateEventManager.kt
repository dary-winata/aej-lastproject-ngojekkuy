package aej.finalproject.ngojekkuy.event

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope

abstract class StateEventManager<T> {
    var value: StateEvent<T> = StateEvent.Idle()
        protected set   //hanya bisa diedit oleh class yang mengimplementasikan, diluar itu val

    abstract var errorDistpatcher: CoroutineExceptionHandler //handler error -> membutuhkan dispatcher yang dapat depend lifecycle
    abstract var listener: StateEvent<T>.(StateEventManager<T>) -> Unit //optional digunakan selain menggunakan subscribe, untuk mapper contoh hanya string

    abstract fun subscribe(
        scope: CoroutineScope, //scope bawaan
        onIdle: () -> Unit = {},
        onLoading: () -> Unit = {},
        onFailure: (throwable: Throwable) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    )

    abstract fun <U> map(mapper: (T) -> U): StateEventManager<U>

    abstract fun invoke(): T? //mengirimkan data yang sedang berjalan/statusnya selain success dikasih null
    abstract fun createScope(another: CoroutineScope): CoroutineScope //scope yang aware lifecycle //scope implementasi untuk dijadikan scope lagi


}