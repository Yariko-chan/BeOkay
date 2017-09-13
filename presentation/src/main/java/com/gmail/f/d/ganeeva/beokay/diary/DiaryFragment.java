package com.gmail.f.d.ganeeva.beokay.diary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.base.BaseAdapter;

public class DiaryFragment extends Fragment {

    public DiaryFragment() {
    }

    public static DiaryFragment newInstance() {
        DiaryFragment fragment = new DiaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diary, container, false);
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
//                ImageButton btn = (ImageButton) v.findViewById(R.id.showMore);
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        View view = v.getRootView();
//                        ExpandableRelativeLayout layout = (ExpandableRelativeLayout) view.findViewById(R.id.expandable);
//                        layout.toggle();
//                    }
//                });
//                // set the view's size, margins, paddings and layout parameters
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
