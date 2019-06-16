package com.example.mytranslator.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.mytranslator.R;
import com.example.mytranslator.ui.Component;
import com.example.mytranslator.ui.ComponentPresenter;
import com.example.mytranslator.ui.ViewComponentInterface;

public class ComponentFragment extends Fragment implements ViewComponentInterface {

    private Component presenter = new ComponentPresenter();
    private Button originalButton;
    private Button translationButton;
    private EditText originalText;
    private TextView translationText;

    public ComponentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.component_fragment, container, false);
        presenter.addComponentInterface(this);
        createButton(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStartView();
    }

    private void createButton(View view) {
        originalButton = view.findViewById(R.id.original);
        originalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(LanguagesFragment.ORIGINAL_VALUE);

            }
        });

        translationButton = view.findViewById(R.id.translation);
        translationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(LanguagesFragment.TRANSLATION_VALUE);
            }
        });
    }

    private void replaceFragment(String targetIntention) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putString(LanguagesFragment.BUNDLE_KEY, targetIntention);
        navController.navigate(R.id.languagesFragment, bundle);
    }

    @Override
    public void addTextOnButton(String originalValue, String translationValue) {
        originalButton.setText(originalValue);
        translationButton.setText(translationValue);
    }
}