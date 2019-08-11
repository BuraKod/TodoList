package com.burakocak.todolist.view.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.burakocak.todolist.R;

import com.burakocak.todolist.model.EventbusObject;
import com.burakocak.todolist.model.Users;
import com.burakocak.todolist.databinding.ActivityLoginBinding;
import com.burakocak.todolist.utils.Constants;
import com.burakocak.todolist.view.base.BaseActivity;
import com.burakocak.todolist.viewmodel.LoginViewModel;




public class LoginActivity extends BaseActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCustomEvent(EventbusObject eventbusObject) {
        if(eventbusObject.getKey() == Constants.RESULT_OK) {
            SignIn(eventbusObject.getObject());
            //startActivity(new Intent(LoginActivity.this,MainActivity.class));

        } else if (eventbusObject.getKey() == Constants.RESULT_NO) {
            runOnUiThread(() -> showErrorSneaker("Login Error!","don't match username and password"));
        }
    }

    private void SignIn(Object object) {
        if (object instanceof String) {
            String username = object.toString();
            Intent returnIntent = new Intent(LoginActivity.this,MainActivity.class);
            returnIntent.putExtra("username",username);
            startActivity(returnIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this,R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);

        init();

    }

    private void init() {

        binding.btnLogin.setOnClickListener(clickListener);
        binding.btnRegister.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String strUserName = binding.etUserName.getText().toString().trim();
            String strPassword = binding.etPassword.getText().toString().trim();
            if (!loginControl(strUserName,strPassword)) return;

            Users user = new Users();


            binding.etPassword.getText().clear();
            binding.etUserName.getText().clear();

            switch (view.getId()) {
                case R.id.btn_login:
                    Log.d("USER","login clicked");
                    loginViewModel.getLogin(strUserName,strPassword);
                    break;
                case R.id.btn_register:
                    Log.d("USER","register clicked");
                    user.setUsername(strUserName);
                    user.setPassword(strPassword);
                    loginViewModel.registerUser(user);
                    showSuccessSneaker("Register", "Register successful");
                    break;

            }

        }
    };

    @Override
    public void onBackPressed() {
        showExitApplicationDialog("Closing Application","Are you sure you want to close this application?");
    }

    private boolean loginControl(String userName, String password) {


        if (TextUtils.isEmpty(userName)) {
            binding.etUserName.setError("Please Enter Your E-mail Address");
            return false;
        }
        else if (TextUtils.isEmpty(password)) {
            binding.etPassword.setError("Please Enter Your Password");
            return false;
        }
        return true;
    }
}
