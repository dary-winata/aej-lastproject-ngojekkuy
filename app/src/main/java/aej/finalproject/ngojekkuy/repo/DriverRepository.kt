package aej.finalproject.ngojekkuy.repo

import aej.finalproject.ngojekkuy.data.LoginRequest
import aej.finalproject.ngojekkuy.data.User
import aej.finalproject.ngojekkuy.event.StateEventManager

interface DriverRepository {
    val userStateEventManager: StateEventManager<List<User>>
    val loginStateEventManager: StateEventManager<String>

    suspend fun login(loginRequest: LoginRequest)
    suspend fun getDriver()
}