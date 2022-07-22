package uz.gita.testapp.data.source.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MySharedPreference @Inject constructor(@ApplicationContext context: Context) {
    private val preference = context.getSharedPreferences("TestApp", Context.MODE_PRIVATE)

    var theme: Boolean
        get() = preference.getBoolean("THEME", false)
        set(value) = preference.edit().putBoolean("THEME", value).apply()

    var language: Int
        get() = preference.getInt("LANGUAGE", 0)
        set(value) = preference.edit().putInt("LANGUAGE", value).apply()
}