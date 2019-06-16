package com.example.mytranslator.recyclerview;


import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.example.mytranslator.R;

public class LangsViewHolder extends RecyclerView.ViewHolder {

    private TextView text;
    private CardView cardView;
    private View view;

    public LangsViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        text = view.findViewById(R.id.info_text);
        cardView = view.findViewById(R.id.card_view);
    }

    public TextView getTextView() {
        return text;
    }

    public CardView getCardView() {
        return cardView;
    }
}

