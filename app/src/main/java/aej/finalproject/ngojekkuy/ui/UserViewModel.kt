package aej.finalproject.ngojekkuy.ui

import aej.finalproject.ngojekkuy.data.User
import aej.finalproject.ngojekkuy.event.StateEventManager
import aej.finalproject.ngojekkuy.event.StateEventSubscriber
import aej.finalproject.ngojekkuy.repo.DriverRepository
import aej.finalproject.ngojekkuy.utils.convertEventToSubscriber
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.annotation.Scope

@Scope(UserActivity::class)
class UserViewModel(
    private val driverRepository: DriverRepository
): ViewModel() {
    private val driverGetUser = driverRepository.userStateEventManager
    private val scope = driverGetUser.createScope(viewModelScope)

    fun subscribeGetUser(subscriber: StateEventSubscriber<List<User>>) {
        convertEventToSubscriber(driverGetUser, subscriber)
    }

    fun getUser() = scope.launch {
        driverRepository.getDriver()
    }
}