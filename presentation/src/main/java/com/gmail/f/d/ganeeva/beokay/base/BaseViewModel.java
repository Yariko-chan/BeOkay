package com.gmail.f.d.ganeeva.beokay.base;

/**
 * Base methods for any ViewModels
 */

public interface BaseViewModel {
    // activity lifecycle
    // authorization check

    public void init();
    public void release(); // destroy

    // for managing activity lifecycle
    public void resume();
    public void pause();

}
