package com.example.hogwartsdata.ui.layouts.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hogwartsdata.core.ScreenState
import com.example.hogwartsdata.data.local.SharedPreferencesManager
import com.example.hogwartsdata.databinding.ActivityLoginBinding
import com.example.hogwartsdata.domain.models.user.UserEntity
import com.example.hogwartsdata.ui.layouts.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    @Inject
    lateinit var sharedPrefs: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.user1 = UserEntity.preloadUsers[0]
        binding.user2 = UserEntity.preloadUsers[1]
        val view = binding.root
        setContentView(view)
        initListeners()
        initStates()
    }

    private fun initListeners(){
        binding.btnLogin.setOnClickListener {
            viewModel.doLogin(binding.etName.editText?.text.toString(), binding.etPassword.editText?.text.toString())
        }

        /*binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val check: Boolean = Validator().password(binding.etPassword.text.toString())
                if(binding.etPassword.hasFocus()) {
                    binding.etPassword.isActivated = !check
                }
                toggleLoginButton()
            }
        })

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val check: Boolean = Validator().email(binding.etEmail.text.toString())
                if(binding.etEmail.hasFocus()) {
                    binding.etEmail.isActivated = !check
                }
                toggleLoginButton()
            }
        })*/

    }


    private fun initStates(){
        viewModel.mState.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(state: ScreenState<LoginActivityState>?){
        when(state) {
            is ScreenState.Loading -> {
                binding.pbLogin.visibility = View.VISIBLE
                toggleControls(false)
            }
            is ScreenState.Render -> {
                toggleControls(true)
                binding.pbLogin.visibility = View.GONE
                renderScreenState(state.renderState)
            }

            else -> {}
        }
    }

    private fun renderScreenState(screenState: LoginActivityState) {
        when(screenState) {
            is LoginActivityState.Init -> {
                Toast.makeText(this, "welcome!", Toast.LENGTH_SHORT).show()
            }
            is LoginActivityState.SuccessLogin ->{
                //TODO: navigate to main
                handleSuccessLogin(screenState.loginEntity)
            }
            is LoginActivityState.ErrorLogin ->{
                //TODO: navigate to main
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
            }


            else -> {}
        }
    }

    /*private fun toggleLoginButton() {
        binding.btnLogin.isEnabled = (Validator().password(binding.etPassword.text.toString()) && Validator().email(binding.etEmail.text.toString()))
    }*/

    private fun toggleControls(enabled:Boolean) {
        binding.etPassword.isEnabled = enabled
        binding.etName.isEnabled = enabled
        if(enabled) {
            //toggleLoginButton()
        } else {
            binding.btnLogin.isEnabled = enabled
        }
    }

    private fun handleSuccessLogin(userEntity: UserEntity){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
