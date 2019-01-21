/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.project.inject.component

import androidovshchik.project.BaseApplication
import androidovshchik.project.inject.module.AppModule
import androidovshchik.project.inject.module.BuildersModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [(AndroidInjectionModule::class), (BuildersModule::class), (AppModule::class)]
)
interface AppComponent {

    fun inject(baseApplication: BaseApplication)
}
