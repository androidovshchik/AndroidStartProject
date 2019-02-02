/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.project.inject.component

import androidovshchik.project.BaseApplication
import androidovshchik.project.inject.module.BuildersModule
import androidovshchik.project.inject.module.MainModule
import androidovshchik.project.inject.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    BuildersModule::class,
    MainModule::class,
    NetworkModule::class
])
interface AppComponent {

    fun inject(baseApplication: BaseApplication)
}
