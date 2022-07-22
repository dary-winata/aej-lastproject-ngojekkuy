package aej.finalproject.ngojekkuy.ui

import aej.finalproject.ngojekkuy.data.LoginRequest
import aej.finalproject.ngojekkuy.event.StateEventSubscriber
import aej.finalproject.ngojekkuy.repo.DriverRepository
import aej.finalproject.ngojekkuy.utils.convertEventToSubscriber
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.annotation.Scope

@Scope(MainActivity::class)
class MainViewModel(
    private val driverRepository: DriverRepository
): ViewModel() {
    private val driverLogin = driverRepository.loginStateEventManager
    private val userScope = driverLogin.createScope(viewModelScope)

    fun subscribeDriverLogin(subscriber: StateEventSubscriber<String>) {
        convertEventToSubscriber(driverLogin, subscriber)
    }

    fun login(loginRequest: LoginRequest) = userScope.launch {
        driverRepository.login(loginRequest)
    }
}