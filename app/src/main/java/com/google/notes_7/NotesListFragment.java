package com.google.notes_7;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class NotesListFragment extends Fragment {


    private boolean isLandscape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_list, container, false);
    }

    // вызывается после создания макета фрагмента, здесь мы проинициализируем список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    // создаем список городов на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] notes = getResources().getStringArray(R.array.note_name);

        // При помощи этого объекта будем доставать элементы, спрятанные в item.xml
        LayoutInflater ltInflater = getLayoutInflater();

        // В этом цикле создаем элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаем обработку касания на элемент
        for (int i = 0; i < notes.length; i++) {
            String note = notes[i];
//            TextView tv = new TextView(getContext());
//            tv.setText(note);
//            tv.setTextSize(30);
//            layoutView.addView(tv);

            // Достаём элемент из item.xml
            View item = ltInflater.inflate(R.layout.item, layoutView, false);
            // Находим в этом элементе TextView
            TextView tv = item.findViewById(R.id.textView);
            tv.setText(note);
            layoutView.addView(item);

            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getResources().getStringArray(R.array.note_name);
                    ((MainActivity)getActivity()).currentNote = new Note(fi);
                    showNoteDescription(((MainActivity)getActivity()).currentNote);
                }
            });
        }
    }



    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Определение, можно ли будет расположить рядом заметку в другом фрагменте
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
        // Если можно нарисовать рядом заметку, то сделаем это
        if (isLandscape) {
            showLandNoteDescription(((MainActivity)getActivity()).currentNote);
        }
    }

    private void showNoteDescription(Note currentNote) {
        if (isLandscape) {
            showLandNoteDescription(currentNote);
        } else {
            showPortNoteDescription(currentNote);
        }
    }

    // Показать заметку в ландшафтной ориентации
    private void showLandNoteDescription(Note currentNote) {
        // Создаем новый фрагмент с текущей позицией для вывода заметки
        NoteDescriptionFragment detail = NoteDescriptionFragment.newInstance(currentNote);

        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    // Показать заметку в портретной ориентации. (устарело)
    private void showPortNoteDescription(Note currentNote) {
        // Мы полностью перешли на single-activity архитектуру, и теперь все проиходит в MainActivity
        Toast.makeText(getActivity()," Выбрана заметка "+currentNote.getNoteName(getActivity())+" \nМы полностью перешли на single-activity архитектуру, и теперь все проиходит в MainActivity",Toast.LENGTH_SHORT).show();
    }
}