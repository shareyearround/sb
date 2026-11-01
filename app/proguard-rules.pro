# -------------------------------------------------------------------------
# Pengaturan Dasar Kotlin & Android
# -------------------------------------------------------------------------
-keepattributes *Annotation*,Signature,InnerClasses,EnclosingMethod
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Jangan hentikan build jika ada missing reference dari library pihak ketiga
-dontwarn **
-ignorewarnings

# Pertahankan metadata Kotlin (penting untuk Coroutines, Reflection, dll)
-keep class kotlin.Metadata { *; }

# Pertahankan class View kustom dan atributnya
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# Pertahankan Parcelable dan Serializable
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# -------------------------------------------------------------------------
# Data Model / Entity
# (PENTING: Ganti nama package di bawah sesuai letak data class/model Anda)
# -------------------------------------------------------------------------
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
# Contoh jika Anda punya package model:
# -keep class com.package.anda.models.** { *; }
