package com.google.notes_7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class NoteDescriptionFragment extends Fragment {

    public static final String ARG_NOTE = "note";
    private Note note;

    // Фабричный метод создания фрагмента
    // Фрагменты рекомендуется создавать через фабричные методы.
    public static NoteDescriptionFragment newInstance(Note note) {
        NoteDescriptionFragment f = new NoteDescriptionFragment();    // создание

        // Передача параметра
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTE, note);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Таким способом можно получить головной элемент из макета
        View view = inflater.inflate(R.layout.fragment_note_description, container, false);

        //теперь найдем и будем работать с нужними нам элементами
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewBody = view.findViewById(R.id.textViewBody);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        DatePicker datePicker = view.findViewById(R.id.datePicker);
        textViewName.setText(note.getNoteName(getContext()));
        textViewBody.setText(note.getNoteBody(getContext()));
        // по хорошему нужно вот так запивать textViewDate.setText(new StringBuilder().append(note.getNoteDateYear(getContext())).append(" ").append(note.getNoteDateMonth(getContext())).append(" ").append(note.getNoteDateDay(getContext())).toString());
        textViewDate.setText(note.getNoteDateYear(getContext())+" "+ note.getNoteDateMonth(getContext())+" "+ note.getNoteDateDay(getContext()));
         datePicker.init(note.getNoteDateYear(getContext()), note.getNoteDateMonth(getContext()), note.getNoteDateDay(getContext()), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //сохраним полученные данные
                note.setNoteDate(getContext(),year, monthOfYear,dayOfMonth);
                // обновим поле даты
                textViewDate.setText(new StringBuilder().append(note.getNoteDateYear(getContext())).append(" ").append(note.getNoteDateMonth(getContext())).append(" ").append(note.getNoteDateDay(getContext())).toString());
            }
        });
        return view;
    }
}