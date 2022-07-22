package aej.finalproject.ngojekkuy.repo

import aej.finalproject.ngojekkuy.data.LoginRequest
import aej.finalproject.ngojekkuy.data.User
import aej.finalproject.ngojekkuy.event.StateEventManager
import aej.finalproject.ngojekkuy.network.NetworkSource
import aej.finalproject.ngojekkuy.utils.default
import org.koin.core.annotation.Single

@Single
class DriverRepositoryImpl(
    private val networkSources: NetworkSource
): DriverRepository {
    private val _loginStateEventManager = default<String>()
    private val _getUserStateEventManager = default<List<User>>()

    override val userStateEventManager: StateEventManager<List<User>>
        get() = _getUserStateEventManager
    override val loginStateEventManager: StateEventManager<String>
        get() = _loginStateEventManager

    override suspend fun login(loginRequest: LoginRequest) {
        networkSources.login(loginRequest).collect(_loginStateEventManager)
    }

    override suspend fun getDriver() {
        networkSources.getUser().collect(_getUserStateEventManager)
    }
}