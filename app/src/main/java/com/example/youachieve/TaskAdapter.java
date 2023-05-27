//package com.example.youachieve;
//
//import android.annotation.SuppressLint;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.youachieve.utils.MyDate;
//import com.example.youachieve.utils.Task;
//
//import java.util.ArrayList;
//
//class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
//    private final ArrayList<Task> taskList;
//
//    public TaskAdapter(ArrayList<Task> taskList) {
//        this.taskList = taskList;
//    }
//
//    @Override
//    public int getItemCount() {
//        return taskList.size();
//    }
//
//    @NonNull
//    @Override
//    public TaskAdapter.TaskViewHolder onCreateViewHolder(
//            @NonNull ViewGroup parent,
//            int viewType)
//    {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        return new TaskViewHolder(layoutInflater.inflate(R.layout.list_item_task, parent, false));
//    }
//
//    @SuppressLint("ClickableViewAccessibility")
//    @Override
//    public void onBindViewHolder(
//            @NonNull TaskAdapter.TaskViewHolder holder,
//            int position)
//    {
//        Task task = taskList.get(position);
//
//        holder.taskName.setText(task.getName());
//        holder.taskDeadline.setText(new MyDate(task.getDeadline()).toString());
//
//        holder.taskMark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (task.isComplete()) {
//                    task.setComplete(false);
//                    holder.taskMark.setBackgroundResource(R.drawable.round_button_frame);
//                }
//                else {
//                    task.setComplete(true);
//                    holder.taskMark.setBackgroundResource(R.drawable.round_button_fill);
//                }
//            }
//        });
//        holder.taskContent.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    holder.itemView.setBackgroundResource(R.drawable.selection);
//                }
//                else if (event.getAction() == MotionEvent.ACTION_UP ||
//                        event.getAction() == MotionEvent.ACTION_CANCEL) {
//                    holder.itemView.setBackgroundResource(R.drawable.background);
//                }
//                return true;
//            }
//        });
//    }
//
//    static class TaskViewHolder extends RecyclerView.ViewHolder {
//        TextView taskName;
//        TextView taskDeadline;
//        ImageView taskMark;
//        LinearLayout taskContent;
//        public TaskViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.taskName = itemView.findViewById(R.id.taskName);
//            this.taskDeadline = itemView.findViewById(R.id.taskDeadline);
//            this.taskMark = itemView.findViewById(R.id.taskMark);
//            this.taskContent = itemView.findViewById(R.id.taskContent);
//        }
//    }
//}
