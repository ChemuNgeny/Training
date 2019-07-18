package com.example.notekeeper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder>{

    private final Context context;
    private final LayoutInflater layoutInflater;
    List<NoteInfo> notes;

    public NoteRecyclerAdapter(Context context, List<NoteInfo> notes) {
        this.context = context;
        this.notes = notes;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = layoutInflater.inflate(R.layout.item_note_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NoteInfo note = notes.get(i);
        viewHolder.txtCourses.setText(note.getmCourses().getmTitle());
        viewHolder.txtTitle.setText(note.getmTitle());
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
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtCourses;
        public TextView txtTitle;
        public int currentPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            txtCourses = itemView.findViewById(R.id.txtCourse);
            txtTitle = itemView.findViewById(R.id.txtTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra(MainActivity.NOTE_POSITION,MainActivity.NOTE_POSITION);
                    context.startActivity(intent);
                }
            });
        }

        public void currentPosition(int i) {
        }
    }

}
