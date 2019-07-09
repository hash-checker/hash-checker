-keep class com.google.android.gms.measurement.** { *; }
-dontwarn com.google.android.gms.measurement.**

-dontwarn javax.persistence.**,javax.annotation.**,javax.lang.model.**,javax.tools.Diagnostic,javax.tools.Diagnostic$Kind
-dontwarn org.slf4j.**,org.joda.**

-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}

-keep class com.j256.**
-keepclassmembers class com.j256.** { *; }
-keep enum com.j256.**
-keepclassmembers enum com.j256.** { *; }
-keep interface com.j256.**
-keepclassmembers interface com.j256.** { *; }

-keep class * extends com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
-keepclassmembers class * extends com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper {
  public <init>(android.content.Context);
}

-keepattributes *Annotation*
-keepattributes Signature

-keep @com.j256.ormlite.table.DatabaseTable class * {
    @com.j256.ormlite.field.DatabaseField <fields>;
    @com.j256.ormlite.field.ForeignCollectionField <fields>;
    <init>();
}