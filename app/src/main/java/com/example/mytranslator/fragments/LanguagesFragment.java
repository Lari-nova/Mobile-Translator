package com.example.mytranslator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mytranslator.R;
import com.example.mytranslator.recyclerview.LangsAdapter;
import com.example.mytranslator.ui.LanguagesPresenter;
import com.example.mytranslator.ui.Languages;
import com.example.mytranslator.ui.ViewLanguageInterface;
import java.util.ArrayList;

public class LanguagesFragment extends Fragment implements ViewLanguageInterface {

    public static final String BUNDLE_KEY = "LanguagesFragment";
    public static final String ORIGINAL_VALUE = "LanguagesFragment.original_value";
    public static final String TRANSLATION_VALUE = "LanguagesFragment.translation_value";
    private RecyclerView recyclerView;
    private LangsAdapter adapter;
    private View view;
    private ProgressBar progressBar;
    private Languages presenter = new LanguagesPresenter();
    private static final int SELECT_KEY = 0;
    private static final int SELECT_VALUE = 1;
    private static final String ORIGINAL_LANGUAGE = "originalLanguage";

    public LanguagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.translation_fragment, container, false);
        progressBar = view.findViewById(R.id.progress_bar);
        createRecyclerView();
        presenter.addLanguageInterface(this);
        presenter.loadLang();
        return view;
    }

    private void createRecyclerView() {
        recyclerView = view.findViewById(R.id.languageRecyclerView);
        adapter = new LangsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.initializeListener(language -> this.presenter.addSelectLanguage(language));
    }

    @Override
    public Bundle getFragmentArguments() {
        return getArguments();
    }

    @Override
    public void replaceFragment() {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.popBackStack();

    }

    @Override
    public void showLangs(ArrayList<String> list) {
        adapter.addData(list);
        progressBar.setVisibility(View.VISIBLE);
    }
}
