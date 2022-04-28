package es.travelworld.ejercicio8_tabs;

import static es.travelworld.ejercicio8_tabs.domain.References.MATCH_FRAGMENT;
import static es.travelworld.ejercicio8_tabs.domain.References.NUM_PAGES_MAIN;
import static es.travelworld.ejercicio8_tabs.domain.References.ON_BOARDING_FRAGMENT;
import static es.travelworld.ejercicio8_tabs.domain.References.ROOMMATE_FRAGMENT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.os.Bundle;

import es.travelworld.ejercicio8_tabs.databinding.ActivityMainBinding;
import es.travelworld.ejercicio8_tabs.fragments.MatchFragment;
import es.travelworld.ejercicio8_tabs.fragments.OnBoardingFragment;
import es.travelworld.ejercicio8_tabs.fragments.RoommateFragment;

public class MainActivity extends AppCompatActivity implements OnBoardingFragment.OnClickItemOnBoardingFragment, MatchFragment.OnClickItemMatchFragment, RoommateFragment.OnClickItemRoommateFragment {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentStateAdapter fragmentStateAdapter = new MainActivityFragmentStateAdapter(this);
        binding.viewPager.setAdapter(fragmentStateAdapter);

        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if (binding.viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
            finish();
        } else {
            binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onBoardingNextButton() {
        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
    }

    @Override
    public void matchNextButton() {
        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
    }

    @Override
    public void matchSkipButton() {
        launchLoginActivity();
    }

    @Override
    public void roommateLoginButton() {
        launchLoginActivity();
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private class MainActivityFragmentStateAdapter extends FragmentStateAdapter {

        public MainActivityFragmentStateAdapter(MainActivity mainActivity) {
            super(mainActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = new Fragment();

            switch (position){
            case 0:
                fragment = startOnBoardingFragment();
                break;
            case 1:
                fragment = startMatchFragment();
                break;
            case 2:
                fragment = startRoommateFragment();
                break;
            }

            return fragment;
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES_MAIN;
        }

        private Fragment startOnBoardingFragment() {
            OnBoardingFragment fragment = (OnBoardingFragment) getSupportFragmentManager().findFragmentByTag(ON_BOARDING_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new OnBoardingFragment();
            }
        }

        private Fragment startMatchFragment() {
            MatchFragment fragment = (MatchFragment) getSupportFragmentManager().findFragmentByTag(MATCH_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new MatchFragment();
            }
        }

        private Fragment startRoommateFragment() {
            RoommateFragment fragment = (RoommateFragment) getSupportFragmentManager().findFragmentByTag(ROOMMATE_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new RoommateFragment();
            }
        }
    }
}