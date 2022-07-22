package aej.finalproject.ngojekkuy.ui

import aej.finalproject.ngojekkuy.R
import aej.finalproject.ngojekkuy.data.User
import aej.finalproject.ngojekkuy.databinding.ActivityUserBinding
import aej.finalproject.ngojekkuy.event.StateEventSubscriber
import android.os.Bundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserActivity : ScopeActivity() {
    private val viewModel: UserViewModel by viewModel()
    private val binding: ActivityUserBinding by lazy {
        ActivityUserBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.subscribeGetUser(subscriberDriver())

        binding.run {
            viewModel.getUser()
        }
    }

    private fun subscriberDriver() = object : StateEventSubscriber<List<User>> {
        override fun onIdle() {
            binding.textView2.append("Idle...")
        }

        override fun onLoading() {
            binding.textView2.append("Loading...")
        }

        override fun onFailure(throwable: Throwable) {
            TODO("Not yet implemented")
        }

        override fun onSuccess(data: List<User>) {
            binding.textView2.append("$data  \n")
        }

    }
}