package es.travelworld.ejercicio8_tabs;


import static es.travelworld.ejercicio8_tabs.domain.References.KEY_USER;
import static es.travelworld.ejercicio8_tabs.domain.References.LOGIN_FRAGMENT;
import static es.travelworld.ejercicio8_tabs.domain.References.LOGIN_SUCCESSFUL;
import static es.travelworld.ejercicio8_tabs.domain.References.REGISTER_FRAGMENT;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import es.travelworld.ejercicio8_tabs.databinding.ActivityLoginBinding;
import es.travelworld.ejercicio8_tabs.domain.User;
import es.travelworld.ejercicio8_tabs.fragments.LoginErrorFragment;
import es.travelworld.ejercicio8_tabs.fragments.LoginFragment;
import es.travelworld.ejercicio8_tabs.fragments.RegisterFragment;

public class LoginActivity extends AppCompatActivity implements LoginFragment.OnClickItemLoginFragment, RegisterFragment.OnClickItemRegisterFragment {

    private ActivityLoginBinding binding;
    private String currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startLoginFragment();
    }

    private void startLoginFragment() {
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentByTag(LOGIN_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        fragment != null ? fragment : LoginFragment.newInstance(),
                        LOGIN_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        currentFragment = LOGIN_FRAGMENT;
    }

    private void startRegisterFragment() {
        RegisterFragment fragment = (RegisterFragment) getSupportFragmentManager().findFragmentByTag(REGISTER_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.loginFragmentFrame.getId(),
                        fragment != null ? fragment : RegisterFragment.newInstance(),
                        REGISTER_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
        currentFragment = REGISTER_FRAGMENT;
    }

    @Override
    public void onBackPressed() {
        if (currentFragment.equals(LOGIN_FRAGMENT)) {
            super.onBackPressed();
            finish();
        } else {
            startLoginFragment();
        }
    }

    @Override
    public void loginButton(User user, String code) {
        if (code.equals(LOGIN_SUCCESSFUL)) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(KEY_USER, user);
            startActivity(intent);
            finish();
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            LoginErrorFragment loginErrorFragment = LoginErrorFragment.newInstance();
            loginErrorFragment.show(fragmentManager, null);
        }
    }

    @Override
    public void loginNewAccountButton() {
        startRegisterFragment();
    }

    @Override
    public void registerJoinButton() {
        startLoginFragment();
    }
}