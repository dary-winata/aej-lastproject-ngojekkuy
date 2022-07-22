package aej.finalproject.ngojekkuy

import aej.finalproject.ngojekkuy.di.MainModule
import aej.finalproject.ngojekkuy.event.StateEvent
import android.app.Application
import kotlinx.coroutines.flow.Flow
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(MainModule().module)
        }
    }
}