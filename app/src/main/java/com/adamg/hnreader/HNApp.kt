package com.adamg.hnreader

import android.app.Application
import com.adamg.hnreader.dagger.component.ApplicationComponent
import com.adamg.hnreader.dagger.component.DaggerApplicationComponent
import com.adamg.hnreader.dagger.module.ApplicationModule
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration

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
        initRealm()
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }


    private fun initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build())
    }

    private fun initializeApplicationComponent(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule("http://node-hnapi.herokuapp.com/"))
                .build()
    }
}