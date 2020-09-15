package com.neeru.anonymous;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class AttendanceRecyclerAdpater extends RecyclerView.Adapter<AttendanceRecyclerAdpater.ViewHolder> {
    private ArrayList<Attendance> attendances = new ArrayList<>();
    private Context context;

    public AttendanceRecyclerAdpater(Context context) {
        this.context = context;
    }

    public void setContents(ArrayList<Attendance> contents) {
        this.attendances = contents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.subject.setText(attendances.get(position).getSubject());
        int percentage = attendances.get(position).getPercent();
        holder.progressBar.setProgress(percentage);
        if(percentage>75){
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }else{
            holder.progressBar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }
        holder.attended.setText(String.format("%d Attended", attendances.get(position).getAttended()));
        holder.total.setText(String.format("Total %s", attendances.get(position).getTotal()));
        int days_bunk = attendances.get(position).getDays();
        if (days_bunk >= 0) {
            holder.days.setText(String.format("Bunk %s session(s)", String.valueOf(days_bunk)));
            holder.days.setTextColor(Color.GREEN);
        } else {
            holder.days.setText(String.format("Attend %s session(s)", String.valueOf(days_bunk)));
            holder.days.setTextColor(Color.RED);
        }
        holder.percent.setText(String.valueOf(attendances.get(position).getPercent()) + "%");
    }

    @Override
    public int getItemCount() {
        return attendances.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView subject, attended, total, days, percent;
        private ProgressBar progressBar;
        private RelativeLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subject = itemView.findViewById(R.id.item_subject);
            attended = itemView.findViewById(R.id.item_attended);
            total = itemView.findViewById(R.id.item_total);
            days = itemView.findViewById(R.id.item_days);
            progressBar = itemView.findViewById(R.id.progressBar);
            percent = itemView.findViewById(R.id.item_percent);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
