package com.example.notekeeper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder>{

    private final Context context;
    private final LayoutInflater layoutInflater;
    List<CourseInfo> mCourses;

    public CourseRecyclerAdapter(Context context, List<CourseInfo> mCourses) {
        this.context = context;
        this.mCourses = mCourses;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.item_course_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CourseInfo course = mCourses.get(i);
        viewHolder.txtCourses.setText(course.getmCourseId());
        viewHolder.currentPosition(i);
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        NoteInfo note = mCourses.get(i);
//        viewHolder.txtCourse.setText(note.getmCourses().getmTitle());
//        viewHolder.txtTitle.setText(note.getmTitle());
//        viewHolder.currentPosition(i);
//    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtCourses;
        public int currentPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCourses = itemView.findViewById(R.id.txtCourse);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, mCourses.get(currentPosition).getmTitle(),Snackbar.LENGTH_LONG).show();
                }
            });
        }

        public void currentPosition(int i) {
        }
    }

}
