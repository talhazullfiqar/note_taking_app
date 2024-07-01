package com.example.note_taking_app;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private ArrayList<Note> notes;
    private OnNoteListener onNoteListener;

    public NoteAdapter(ArrayList<Note> notes, OnNoteListener onNoteListener) {
        this.notes = notes;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view, onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.subjectTextView.setText(note.getSubject());
        holder.contentTextView.setText(note.getContent());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView subjectTextView;
        TextView contentTextView;
        ImageButton deleteButton;
        OnNoteListener onNoteListener;

        public NoteViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            subjectTextView = itemView.findViewById(R.id.subjectTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            this.onNoteListener = onNoteListener;

            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteDelete(getAdapterPosition());
        }
    }

    public interface OnNoteListener {
        void onNoteDelete(int position);
    }
}
