package es.travelworld.ejercicio8_tabs;

import static es.travelworld.ejercicio8_tabs.domain.References.HOME_FRAGMENT;
import static es.travelworld.ejercicio8_tabs.domain.References.KEY_USER;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import es.travelworld.ejercicio8_tabs.databinding.ActivityHomeBinding;
import es.travelworld.ejercicio8_tabs.domain.User;
import es.travelworld.ejercicio8_tabs.fragments.HomeFragment;
import es.travelworld.ejercicio8_tabs.fragments.WipFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = getIntent().getParcelableExtra(KEY_USER);

        setSupportActionBar(binding.toolbar);

        startHomeFragment();
    }

    private void startHomeFragment() {
        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(binding.homeFragmentFrame.getId(),
                        fragment != null ? fragment : HomeFragment.newInstance(user),
                        HOME_FRAGMENT)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.eurodisney) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.disneylandparis.com/es-es/"));
            startActivity(intent);
        }

        if (item.getItemId() == R.id.rentacar) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            WipFragment wipFragment = WipFragment.newInstance();
            wipFragment.show(fragmentManager, null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(KEY_USER, user);
        startActivity(intent);
        finish();
    }
}