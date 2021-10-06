package com.example.alarm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Alarm> alarms = new ArrayList<>();

    public RecyclerViewAdapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_recyclerview, parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.alarmMessage.setText(alarms.get(position).getMessage());
        String time = alarms.get(position).getHour() + " : " + alarms.get(position).getMin();
        holder.alarmTime.setText(time);

    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView alarmMessage , alarmTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            alarmMessage = (TextView) itemView.findViewById(R.id.messagetextview);
            alarmTime = (TextView) itemView.findViewById(R.id.timetextview);
        }
    }

    public void setAlarms(ArrayList<Alarm> alarms) {
        this.alarms = alarms;
        notifyDataSetChanged();
    }
}
