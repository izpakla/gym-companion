package rs.rocketbyte.gym.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideExampleUseCase(
        @ApplicationContext context: Context
    ): rs.rocketbyte.core.usecase.example.WorkoutUseCase =
        rs.rocketbyte.core.util.UseCaseInjector.getDefaultExampleUseCase(context)

}