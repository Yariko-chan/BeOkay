package com.gmail.f.d.ganeeva.beokay.diary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.diary.add.AddDiaryEntryFragment;

public class DiaryFragment extends Fragment {

    public DiaryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showDialog() {
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        AddDiaryEntryFragment newFragment = new AddDiaryEntryFragment();
//
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        // For a little polish, specify a transition animation
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        // To make it fullscreen, use the 'content' root view as the container
//        // for the fragment, which is always the root view for the activity
//        transaction.add(android.R.id.content, newFragment)
//            .addToBackStack(null).commit();
        AddDiaryEntryFragment fragment = new AddDiaryEntryFragment();
        fragment.show(getFragmentManager(), "");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_diary, container, false);

        FloatingActionButton button = (FloatingActionButton) v.findViewById(R.id.addNoteButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.diary_list);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(
            new RecyclerView.Adapter<Holder>() {

                @Override
                public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_diary_entry, parent, false);
                    Holder vh = new Holder(v);
                    return vh;
                }

                @Override
                public void onBindViewHolder(Holder holder, int position) {

                }

                @Override
                public int getItemCount() {
                    return 13;
                }
            });
    }

    private static class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView) {
            super(itemView);
        }
    }
}
