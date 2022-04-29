package es.travelworld.ejercicio8_tabs;

import static es.travelworld.ejercicio8_tabs.domain.References.HOME_FRAGMENT;
import static es.travelworld.ejercicio8_tabs.domain.References.KEY_USER;
import static es.travelworld.ejercicio8_tabs.domain.References.NUM_PAGES_HOME;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

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

        //TODO: rehabilitar la recepcion del usuario
        //user = getIntent().getParcelableExtra(KEY_USER);
        user = new User();
        user.setName("a");
        user.setLastname("b");
        user.setAgeGroup("c");
        user.setPassword("d");


        setSupportActionBar(binding.toolbar);

        setUpTabs();
    }

    private void setUpTabs() {
        FragmentStateAdapter fragmentStateAdapter = new HomeActivityFragmentStateAdapter(this);
        binding.homeViewPager.setAdapter(fragmentStateAdapter);

        setCustomTabs();

        setListeners();

        initializeIconColors();

        binding.tabLayout.getTabAt(0).select();
    }

    private void initializeIconColors() {
        setIconColorBlack(binding.tabLayout.getTabAt(0).getCustomView().findViewById(R.id.iconCamera));
        setIconColorBlack(binding.tabLayout.getTabAt(1).getCustomView().findViewById(R.id.iconCar));
        setIconColorBlack(binding.tabLayout.getTabAt(2).getCustomView().findViewById(R.id.iconTerrain));
        setIconColorBlack(binding.tabLayout.getTabAt(3).getCustomView().findViewById(R.id.iconFace));
    }

    private void setListeners() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                AppCompatImageView imageView;
                switch (position) {
                    case 0:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconCamera); //Localizacion del imageview
                        setIconColorWhite(imageView);
                        break;
                    case 1:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconCar); //Localizacion del imageview
                        setIconColorWhite(imageView);
                        break;
                    case 2:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconTerrain); //Localizacion del imageview
                        setIconColorWhite(imageView);
                        break;
                    case 3:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconFace); //Localizacion del imageview
                        setIconColorWhite(imageView);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                AppCompatImageView imageView;
                switch (position) {
                    case 0:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconCamera); //Localizacion del imageview
                        setIconColorBlack(imageView);
                        break;
                    case 1:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconCar); //Localizacion del imageview
                        setIconColorBlack(imageView);
                        break;
                    case 2:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconTerrain); //Localizacion del imageview
                        setIconColorBlack(imageView);
                        break;
                    case 3:
                        imageView = Objects.requireNonNull(tab.getCustomView()).findViewById(R.id.iconFace); //Localizacion del imageview
                        setIconColorBlack(imageView);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabSelected(tab);
            }
        });
    }

    private void setCustomTabs() {
        new TabLayoutMediator(binding.tabLayout, binding.homeViewPager, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setCustomView(R.layout.tab_camera);
                    break;
                case 1:
                    tab.setCustomView(R.layout.tab_car);
                    break;
                case 2:
                    tab.setCustomView(R.layout.tab_terrain);
                    break;
                case 3:
                    tab.setCustomView(R.layout.tab_face);
                    break;
            }
        })).attach();
    }

    private void setIconColorWhite(AppCompatImageView imageView) {
        imageView.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.white));
    }

    private void setIconColorBlack(AppCompatImageView imageView) {
        imageView.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.black));
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

    private class HomeActivityFragmentStateAdapter extends FragmentStateAdapter {
        public HomeActivityFragmentStateAdapter(HomeActivity homeActivity) {
            super(homeActivity);

        }


        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = new Fragment();

            //TODO: crear contenido de las otras pestañas
            switch (position) {
                case 0:
                    fragment = startHomeFragment();
                    break;
                case 1:
                    //fragment = startHomeFragment1();
                    break;
                case 2:
                    //fragment = startHomeFragment2();
                    break;
                case 3:
                    //fragment = startHomeFragment3();
                    break;
            }

            return fragment;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES_HOME;
        }

        private Fragment startHomeFragment() {
            HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HOME_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new HomeFragment();
            }
        }
    }
}