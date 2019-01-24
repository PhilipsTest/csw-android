# from dagger.android.DispatchingAndroidInjector
-dontwarn com.google.errorprone.annotations.*
-keep class com.philips.platform.catk.datamodel.CachedConsentStatus { *; }

# Dto (Data Transfer Object) files
-keep class com.philips.platform.catk.dto.GetConsentDto { *; }
-keep class com.philips.platform.catk.dto.CreateConsentDto { *; }
