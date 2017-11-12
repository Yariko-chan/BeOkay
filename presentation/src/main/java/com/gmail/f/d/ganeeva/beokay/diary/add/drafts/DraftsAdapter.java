package com.gmail.f.d.ganeeva.beokay.diary.add.drafts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.gmail.f.d.ganeeva.beokay.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diana on 30.10.2017 at 22:03.
 */

public class DraftsAdapter extends RecyclerView.Adapter<DraftsAdapter.DraftHolder> {

    private List<String> drafts = new ArrayList<>();
    private boolean[] selected;

    public void setDrafts(List<String> drafts) {
        this.drafts.addAll(drafts);
        selected = new boolean[drafts.size()];
    }

    @Override
    public DraftHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_draft, parent, false);
        DraftHolder vh = new DraftHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DraftHolder holder, int position) {
        holder.setPosition(position);
        holder.draftTV.setText(drafts.get(position));
    }

    @Override
    public int getItemCount() {
        return drafts.size();
    }

    public ArrayList<String> getSelectedStrings() {
        ArrayList<String> selectedStrings = new ArrayList<>(drafts.size());
        for (int i = 0; i < drafts.size(); i++) {
            if (selected[i])
                selectedStrings.add(drafts.get(i));
        }
        selectedStrings.trimToSize();
        return selectedStrings;
    }

    public class DraftHolder extends RecyclerView.ViewHolder {
        public TextView draftTV;
        public CheckBox selectedChB;
        private int position = -1;

        public void setPosition(int position) {
            this.position = position;
        }

        public DraftHolder(View itemView) {
            super(itemView);
            draftTV = (TextView) itemView.findViewById(R.id.draftTV);
            selectedChB = (CheckBox) itemView.findViewById(R.id.selectedChB);
            selectedChB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    selected[position] = isChecked;
                }
            });
        }
    }
}
