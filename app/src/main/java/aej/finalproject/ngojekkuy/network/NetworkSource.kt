package aej.finalproject.ngojekkuy.network

import aej.finalproject.ngojekkuy.data.LoginRequest
import aej.finalproject.ngojekkuy.data.Mapper
import aej.finalproject.ngojekkuy.data.User
import aej.finalproject.ngojekkuy.network.NetworkVariable.token
import aej.finalproject.ngojekkuy.utils.FlowState
import aej.finalproject.ngojekkuy.utils.asFlowStateEvent
import org.koin.core.annotation.Single

@Single
class NetworkSource(private val driverServiceProvider: DriverServiceProvider) {
    suspend fun login(loginRequest: LoginRequest): FlowState<String> {
        return driverServiceProvider.get().login(loginRequest).asFlowStateEvent {
            Mapper.mapLoginResponse(it)
        }
    }

    suspend fun getUser(): FlowState<List<User>> {
        return driverServiceProvider.get().getUser(token).asFlowStateEvent {
            Mapper.mapUserResponse(it)
        }
    }
}