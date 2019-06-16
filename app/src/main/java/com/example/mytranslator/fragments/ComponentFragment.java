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
import com.example.mytranslator.network.NetworkManager;
import com.example.mytranslator.resource.Constants;
import com.example.mytranslator.ui.Component;
import com.example.mytranslator.ui.ComponentPresenter;
import com.example.mytranslator.ui.ViewComponentInterface;

public class ComponentFragment extends Fragment implements ViewComponentInterface {

    private Component presenter;
    private View view;
    private Button originalButton;
    private Button translationButton;
    private Button translator;
    private EditText originalText;
    private TextView translationText;

    public ComponentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.component_fragment, container, false);
        presenter = new ComponentPresenter(new NetworkManager(Constants.BASE_URL));
        presenter.addComponentInterface(this);
        initializeComponent();
        createListeners();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStartView();
    }

    private void createListeners() {
        originalButton.setOnClickListener(view -> replaceFragment(LanguagesFragment.ORIGINAL_VALUE));
        translationButton.setOnClickListener(view -> replaceFragment(LanguagesFragment.TRANSLATION_VALUE));

        translator.setOnClickListener(view -> {
            presenter.translateText(originalText.getText().toString());
        });
    }

    private void initializeComponent() {
        originalButton = view.findViewById(R.id.original);
        translationButton = view.findViewById(R.id.translation);
        translator = view.findViewById(R.id.translator);
        originalText = view.findViewById(R.id.original_text);
        translationText = view.findViewById(R.id.translation_text);
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

    @Override
    public void showTranslation(String text) {
        translationText.setText(text);
    }
}