package com.keziandre.dothedue;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskAdapter extends CursorRecyclerViewAdapter<TaskAdapter.TaskViewHolder>{
    public TaskAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_task, parent, false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder viewHolder, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(Task.COLUMN_TITLE));
        String date = cursor.getString(cursor.getColumnIndex(Task.COLUMN_DATE));
        String location = cursor.getString(cursor.getColumnIndex(Task.COLUMN_LOCATION));
        viewHolder.tvTitle.setText(title);
        viewHolder.tvDate.setText(date);
        viewHolder.tvLocation.setText(location);

        final long currentID = cursor.getLong(cursor.getColumnIndex(Task.COLUMN_ID));

        viewHolder.llTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get clicked item
                onItemClickListener.onItemClick(currentID);
            }
        });
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvDate;
        TextView tvLocation;
        LinearLayout llTask;

        public TaskViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvLocation = itemView.findViewById(R.id.tv_location);
            llTask = itemView.findViewById(R.id.task);
        }
    }

    public interface OnItemClickListener
    {
        public void onItemClick(long task);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
