package com.example.moneytracker.modules

import android.content.SharedPreferences
import com.example.moneytracker.view.ui.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FragmentModule {
    @Singleton
    @Provides
    fun provideWelcomeFragment() = WelcomeFragment()

    @Singleton
    @Provides
    fun provideLoginFragment(sharedPreferences: SharedPreferences) =
        LoginUserFragment(sharedPreferences)

    @Singleton
    @Provides
    fun provideRegisterUserFragment() = RegisterUserFragment()

    @Singleton
    @Provides
    fun provideHomeFragment(sharedPreferences: SharedPreferences) = HomeFragment(sharedPreferences)

    @Singleton
    @Provides
    fun provideYearlyOperationsSummaryFragment() = YearlyOperationsSummaryFragment()

    @Singleton
    @Provides
    fun provideAddOperationFragment() = AddOperationFragment()

    @Singleton
    @Provides
    fun provideAddCategoryFragment() = AddCategoryFragment()

    @Singleton
    @Provides
    fun provideOperationListFragment() = OperationListFragment()

    @Singleton
    @Provides
    fun provideOperationCategoryListFragment() = CategoryListFragment()

    @Singleton
    @Provides
    fun provideChartFragment() = ChartFragment()

    @Singleton
    @Provides
    fun provideListFragment() = ListFragment()

    @Singleton
    @Provides
    fun provideAddFragment() = AddFragment()

    @Singleton
    @Provides
    fun provideDatePickerFragment() = DatePickerFragment()
}