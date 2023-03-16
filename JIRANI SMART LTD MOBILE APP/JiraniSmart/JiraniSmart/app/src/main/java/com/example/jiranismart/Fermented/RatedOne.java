package com.example.jiranismart.Fermented;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jiranismart.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class RatedOne extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RateMode> list;
    private Context ctx;

    public RatedOne(Context context, List<RateMode> list) {
        this.list = list;
        ctx = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.emerge, viewGroup, false);
        viewHolder = new OriginalViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder hotel, int i) {
        if (hotel instanceof OriginalViewHolder) {
            final OriginalViewHolder viewHolder = (OriginalViewHolder) hotel;
            final RateMode get = list.get(i);
            if (get.getReply().equals("Pending")) {
                viewHolder.textInputLayout.setVisibility(View.GONE);
            }else {
                viewHolder.textInputLayout.setVisibility(View.VISIBLE);
            }
            viewHolder.Message.setText(get.getMessage());
            viewHolder.DateMes.setText(get.getReg_date());
            viewHolder.Reply.setText(get.getReply());
            viewHolder.DateRep.setText(get.getReply_date());

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView Message, DateMes, Reply, DateRep;
        public TextInputLayout textInputLayout;

        public OriginalViewHolder(@NonNull View view) {
            super(view);
            Message = view.findViewById(R.id.tetMessage);
            DateMes = view.findViewById(R.id.textDate);
            Reply = view.findViewById(R.id.textRepliedMess);
            DateRep = view.findViewById(R.id.textRplyDate);
            textInputLayout = view.findViewById(R.id.layout_pass);

        }
    }


}
