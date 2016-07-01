package fr.inria.rsommerard.wifidirectsting;

import com.apisense.sdk.core.sting.StingModule;

import dagger.Module;

@Module(addsTo = StingModule.class, library = true, injects = { WiFiDirectSting.class })
public class CustomStingModule {}
