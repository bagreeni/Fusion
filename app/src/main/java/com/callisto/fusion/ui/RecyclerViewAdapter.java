package com.callisto.fusion.ui;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.callisto.fusion.DataRepository;
import com.callisto.fusion.R;
import com.callisto.fusion.db.entities.FullTextTask;

import java.util.List;

/**
 * Created by brookgreening on 3/7/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<FullTextTask> data;
    private Button doneButton;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v;
        }
    }

    public RecyclerViewAdapter(List<FullTextTask> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//         create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ((TextView)holder.mTextView.findViewById(R.id.taskTitle)).setText(data.get(position).getData());
        ((TextView)holder.mTextView.findViewById(R.id.taskCategory)).setText(data.get(position).getCategoryList());
        ((TextView)holder.mTextView.findViewById(R.id.taskId)).setText(String.valueOf(data.get(position).getTaskID()));

        holder.itemView.setTag(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
