package com.example.myapplication.dependencyinjection

import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
 abstract class BatteryModule {

    @Binds
     abstract fun BindsPower(nickolCadmiumBattery: NickolCadmiumBattery):Battery


}