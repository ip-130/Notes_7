package com.google.notes_7.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.notes_7.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SocialNetworkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SocialNetworkFragment extends Fragment {

    public static SocialNetworkFragment newInstance() {
        return new SocialNetworkFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_network, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_lines);
        String[] data = getResources().getStringArray(R.array.titles);
        initRecyclerView(recyclerView, data);
        return view;
    }

    private void initRecyclerView(RecyclerView recyclerView, String[] data) {

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        SocialNetworkAdapter adapter = new SocialNetworkAdapter(data);
        recyclerView.setAdapter(adapter);

        // Установим слушателя
        adapter.SetOnItemClickListener(new SocialNetworkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(), String.format("%s - %d", ((TextView) view).getText(), position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}