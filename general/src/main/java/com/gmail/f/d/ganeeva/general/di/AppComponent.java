package com.gmail.f.d.ganeeva.general.di;

import com.gmail.f.d.ganeeva.data.net.RestService;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

//    public void inject(RestService restService);
}
