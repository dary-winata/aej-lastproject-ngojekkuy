package aej.finalproject.ngojekkuy.utils

import aej.finalproject.ngojekkuy.event.MutableStateEventManager
import aej.finalproject.ngojekkuy.event.StateEvent
import aej.finalproject.ngojekkuy.event.StateEventManager
import aej.finalproject.ngojekkuy.event.StateEventSubscriber
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

typealias FlowState<T> = Flow<StateEvent<T>>

fun <T> default() = MutableStateEventManager<T>()

fun <T, U> Response<T>.asFlowStateEvent(mapper: (T) -> U): FlowState<U> {
    return flow {
        emit(StateEvent.Loading()) //menandakan interaksi API
        val emitData = try {
             val body = body()
             if (isSuccessful && body != null){
                 val dataMapper = mapper.invoke(body)
                 StateEvent.Success(dataMapper)
             } else {
                 val exception = Throwable(message())
                 StateEvent.Failure(exception)
             }

         } catch (e: Throwable) {
            StateEvent.Failure(e)
        }

        emit(emitData)
    }
}

fun <T> ViewModel.convertEventToSubscriber(
    eventManager: StateEventManager<T>,
    subscriber: StateEventSubscriber<T>
) {
    eventManager.subscribe(
        scope = viewModelScope,
        onIdle = subscriber::onIdle,
        onLoading = subscriber::onLoading,
        onFailure = subscriber::onFailure,
        onSuccess = subscriber::onSuccess
    )
}