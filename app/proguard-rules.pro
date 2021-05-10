# --- OrmLite START ---
#noinspection ShrinkerUnresolvedReference
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
# --- OrmLite END ---

-keep class com.github.aelstad.**
-keepclassmembers class com.github.aelstad** { *; }
-keep enum com.github.aelstad.**
-keepclassmembers enum com.github.aelstad.** { *; }
-keep interface com.github.aelstad.**
-keepclassmembers interface com.github.aelstad.** { *; }
