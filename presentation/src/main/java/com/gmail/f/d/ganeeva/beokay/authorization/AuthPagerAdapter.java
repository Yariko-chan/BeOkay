package com.gmail.f.d.ganeeva.beokay.authorization;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gmail.f.d.ganeeva.beokay.R;
import com.gmail.f.d.ganeeva.beokay.authorization.login.LoginFragment;

class AuthPagerAdapter extends FragmentPagerAdapter {

    private final int PAGE_COUNT = 2;
    private String tabTitles[];
    private Context context;

    public AuthPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        tabTitles = new String[]{context.getString(R.string.login_tab), context.getString(R.string.register_tab)};
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (0 == position) {
            fragment = LoginFragment.newInstance();
        } else { // register by default
            fragment = RegisterFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) { // register by default
        if (position <0  || position > PAGE_COUNT) return tabTitles[1];
        return tabTitles[position];
    }
}
