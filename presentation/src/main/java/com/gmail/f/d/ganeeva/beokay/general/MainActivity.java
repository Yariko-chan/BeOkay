package com.gmail.f.d.ganeeva.beokay.general;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.gmail.f.d.ganeeva.beokay.diary.DiaryFragment;
import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.schedule.ScheduleFragment;
import com.gmail.f.d.ganeeva.beokay.settings.SettingsFragment;
import com.gmail.f.d.ganeeva.beokay.training.TrainingFragment;

public class MainActivity extends FragmentActivity {

    private BottomNavigationView bottomNavigationView;

    private boolean backToExitPressedOnce = false;

    public static void show (Context context, Bundle args) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        showFragmentFragmentManager (fragmentManager, new ScheduleFragment());

        bottomNavigationView = (BottomNavigationView)
            findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.action_schedule: {
                            backToExitPressedOnce = false;
                            showFragmentFragmentManager (fragmentManager, new ScheduleFragment());
                            return true;
                        }
                        case R.id.action_diary: {
                            backToExitPressedOnce = false;
                            showFragmentFragmentManager (fragmentManager, new DiaryFragment());
                            return true;
                        }
                        case R.id.action_training: {
                            backToExitPressedOnce = false;
                            showFragmentFragmentManager (fragmentManager, new TrainingFragment());
                            return true;
                        }
                        case R.id.action_settings: {
                            backToExitPressedOnce = false;
                            showFragmentFragmentManager (fragmentManager, new SettingsFragment());
                            return true;
                        }

                    }
                    return true;
                }
            });
    }

    @Override
    public void onBackPressed() {
        if (backToExitPressedOnce) {
            this.finish();
        } else {
            Toast.makeText(this, R.string.msg_exit_caution, Toast.LENGTH_SHORT).show();
            backToExitPressedOnce = true;
        }
    }

    private void showFragmentFragmentManager (FragmentManager fragmentManager, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // here pass animations
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.container, fragment, fragment.getClass().getName());
        fragmentTransaction.commit();
    }
}
