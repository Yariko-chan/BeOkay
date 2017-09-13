package com.gmail.f.d.ganeeva.beokay.diary;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ToggleButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.gmail.f.d.ganeeva.beokay.R;

/**
 * Created by Diana on 12.09.2017 at 10:54.
 */

public class DiaryEntry extends FrameLayout {
    public DiaryEntry(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DiaryEntry(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DiaryEntry(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DiaryEntry(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(@NonNull Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_diary_entrie, this);
        ImageButton btn = (ImageButton) v.findViewById(R.id.showMore);
        final ExpandableRelativeLayout erl = (ExpandableRelativeLayout) v.findViewById(R.id.expandable);
        erl.collapse();
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                erl.toggle();
            }
        });
    }
}
