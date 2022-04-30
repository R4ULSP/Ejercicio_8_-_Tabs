package es.travelworld.ejercicio8_tabs.fragments;

import static es.travelworld.ejercicio8_tabs.domain.References.KEY_POSITION;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.travelworld.ejercicio8_tabs.databinding.FragmentPositionBinding;


public class PositionFragment extends Fragment {

    private int position;
    private FragmentPositionBinding binding;

    public static PositionFragment newInstance(int position) {
        PositionFragment positionFragment = new PositionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_POSITION, position);
        positionFragment.setArguments(bundle);
        return positionFragment;
    }

    public PositionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(KEY_POSITION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPositionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        position = position + 1;
        binding.position.setText("" + position);

        return view;
    }
}