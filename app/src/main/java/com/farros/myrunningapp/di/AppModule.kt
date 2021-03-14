package com.farros.myrunningapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.farros.myrunningapp.db.RunningDatabase
import com.farros.myrunningapp.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.farros.myrunningapp.other.Constants.KEY_NAME
import com.farros.myrunningapp.other.Constants.KEY_WEIGHT
import com.farros.myrunningapp.other.Constants.RUNNING_DATABASE_NAME
import com.farros.myrunningapp.other.Constants.SHARED_PREFERENCE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRunningDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun ProvideRunDao(db: RunningDatabase) = db.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext app: Context) =
            app.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun providesName(sharedPref: SharedPreferences) = sharedPref.getString(KEY_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) = sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToogle(sharedPref: SharedPreferences) =
            sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE, true)
}