package com.example.galgelegreallyfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private ArrayList<String> muligeOrd;

    public MainAdapter(ArrayList<String> muligeOrd) {
        this.muligeOrd = muligeOrd;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_rows, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        holder.words.setText(muligeOrd.get(position));
    }

    @Override
    public int getItemCount() {
        return muligeOrd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView words;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            words = itemView.findViewById(R.id.textView);
        }
    }
}
