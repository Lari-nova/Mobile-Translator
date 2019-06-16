package com.example.mytranslator.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mytranslator.R;
import java.util.ArrayList;

public class LangsAdapter extends RecyclerView.Adapter<LangsViewHolder> {

    public interface SelectLaguageListener {
        void onClick(String language);
    }

    private SelectLaguageListener languageListener;

    private ArrayList<String> list = new ArrayList<>();

    public LangsAdapter() { }

    @Override
    public LangsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.langs_view_holder, parent, false);
        return new LangsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LangsViewHolder holder, int position) {
        holder.getTextView().setText(list.get(position));
        holder.getCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageListener.onClick(holder.getTextView().getText().toString());
            }
        });
    }

    public void initializeListener(SelectLaguageListener languageListener) {
        this.languageListener = languageListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}

