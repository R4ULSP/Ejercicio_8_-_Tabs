package es.travelworld.ejercicio8_tabs.fragments;

import static es.travelworld.ejercicio8_tabs.domain.References.KEY_USER;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayoutMediator;

import es.travelworld.ejercicio8_tabs.databinding.FragmentHomeBinding;
import es.travelworld.ejercicio8_tabs.domain.User;


public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    private User user;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(User receivedUser) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_USER, receivedUser);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable(KEY_USER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        //TODO: Snackbar.make(binding.getRoot(), "Nombre: " + user.getName() + "  Apellidos: " + user.getLastname() + "  Edad:" + user.getAgeGroup(), BaseTransientBottomBar.LENGTH_LONG).show();
        return view;
    }
}