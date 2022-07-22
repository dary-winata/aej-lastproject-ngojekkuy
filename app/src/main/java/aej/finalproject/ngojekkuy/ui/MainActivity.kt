package aej.finalproject.ngojekkuy.ui

import aej.finalproject.ngojekkuy.data.LoginRequest
import aej.finalproject.ngojekkuy.databinding.ActivityMainBinding
import aej.finalproject.ngojekkuy.event.StateEventSubscriber
import aej.finalproject.ngojekkuy.network.NetworkVariable
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ScopeActivity() {
//    private val scope: Scope by activityScope()
    private val viewModel: MainViewModel by viewModel()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            btnTest.setOnClickListener {
                val loginRequest = LoginRequest(
                    username = binding.ptUsername.text.toString(),
                    password = binding.ptPassword.text.toString()
                )
                viewModel.login(loginRequest)
            }
        }

        viewModel.subscribeDriverLogin(subscriberDriver())
    }

//    private fun testLogin() {
//        val loginRequest = LoginRequest(
//            username = "dary",
//            password = "test"
//        )
//        viewModel.login(loginRequest)
//        viewModel.subscribeDriver(subscriberDriver())
//    }

    private fun subscriberDriver() = object : StateEventSubscriber<String> {
        override fun onIdle() {
            binding.textView.append("on Idle...\n")
        }

        override fun onLoading() {
            binding.textView.append("Loading...\n")
        }

        override fun onFailure(throwable: Throwable) {
            binding.textView.append("${throwable.message?:"username atau password salah"} \n")
        }

        override fun onSuccess(data: String) {
            binding.textView.append(data)
            NetworkVariable.token = data
            val intent = Intent(this@MainActivity, UserActivity::class.java)
            startActivity(intent)
        }
    }
}