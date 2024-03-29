package rs.rocketbyte.gym.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import rs.rocketbyte.core.audio.BeepPlayer
import rs.rocketbyte.core.audio.MediaBeepPlayer
import rs.rocketbyte.core.share.ShareUseCase
import rs.rocketbyte.core.util.DomainUseCases
import rs.rocketbyte.core.util.UseCaseInjector
import rs.rocketbyte.core.workout.WorkoutUseCase
import rs.rocketbyte.gym.R
import rs.rocketbyte.gym.commons.text.DefaultTextProvider
import rs.rocketbyte.gym.commons.text.TextProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideDomainUseCases(
        @ApplicationContext context: Context
    ): DomainUseCases =
        UseCaseInjector.getDomainUseCases(context)

    @Singleton
    @Provides
    fun provideWorkoutUseCase(
        domainUseCases: DomainUseCases
    ): WorkoutUseCase = domainUseCases.workoutUseCase

    @Singleton
    @Provides
    fun provideShareUseCase(
        domainUseCases: DomainUseCases
    ): ShareUseCase = domainUseCases.shareUseCase

    @Singleton
    @Provides
    fun provideTextProvider(
        @ApplicationContext context: Context
    ): TextProvider = DefaultTextProvider(context)


    @Singleton
    @Provides
    fun provideBeepPlayer(
        @ApplicationContext context: Context
    ): BeepPlayer = MediaBeepPlayer(context, R.raw.digital_alarm_107256)

}