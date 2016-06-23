package fr.inria.rsommerard.gsmsting;

import com.apisense.sdk.core.sting.StingModule;

import dagger.Module;

@Module(addsTo = StingModule.class, library = true, injects = { GSMSting.class })
public class CustomStingModule {}
