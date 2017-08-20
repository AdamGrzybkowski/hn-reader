package com.adamg.hnreader

import android.app.Application
import com.adamg.hnreader.dagger.component.ApplicationComponent
import com.adamg.hnreader.dagger.component.DaggerApplicationComponent
import com.adamg.hnreader.dagger.module.ApplicationModule
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary

class HNApp : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        if(LeakCanary.isInAnalyzerProcess(this)) return
        LeakCanary.install(this)
        initializeApplicationComponent()
        initStetho()

    }

    private fun initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .build())
    }

    private fun initializeApplicationComponent(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule("http://node-hnapi.herokuapp.com/"))
                .build()
    }
}