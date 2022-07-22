package aej.finalproject.ngojekkuy.network

import org.koin.core.annotation.Single

@Single
class DriverServiceProvider {
    fun get(): DriverService {
        return DriverService.build()
    }
}