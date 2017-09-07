package com.gmail.f.d.ganeeva.beokay.authorization;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.general.HomeActivity;

public class AuthorizationActivity extends FragmentActivity {


    public static void show(Context context) {
        Intent intent = new Intent(context, AuthorizationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new AuthPagerAdapter(getSupportFragmentManager(),
            AuthorizationActivity.this));

        // Give the ViewPager to TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.slidingTabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
