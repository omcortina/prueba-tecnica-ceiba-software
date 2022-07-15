package com.example.prueba_ceiba.ui.di.modules

import com.example.prueba_ceiba.data.EndPoints
import com.example.prueba_ceiba.data.dataSource.cloud.ApiUserImpl
import com.example.prueba_ceiba.data.mapper.UserEntityMapper
import com.example.prueba_ceiba.data.repository.UserRepositoryImpl
import com.example.prueba_ceiba.domain.interactor.UserInteractorImpl
import com.example.prueba_ceiba.ui.presenter.UserPresenterImpl
import com.example.prueba_ceiba.utils.Util
import com.loopj.android.http.AsyncHttpClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {
    @Provides
    @Singleton
    fun provideUtil() = Util.instance

    @Provides
    @Singleton
    fun provideEndPoints() = EndPoints()

    @Provides
    @Singleton
    fun provideClient() = AsyncHttpClient()

    @Provides
    @Singleton
    fun userEntityMapper() = UserEntityMapper()

    @Provides
    @Singleton
    fun provideApiUser(
        client: AsyncHttpClient,
        endPoints: EndPoints
    ) = ApiUserImpl(client, endPoints)

    @Provides
    @Singleton
    fun provideValidateRepository(
        apiUserImpl: ApiUserImpl,
        userEntityMapper: UserEntityMapper
    ) = UserRepositoryImpl(apiUserImpl, userEntityMapper)

    @Provides
    @Singleton
    fun provideValidateInteractor(
        userRepositoryImpl: UserRepositoryImpl
    ) = UserInteractorImpl(userRepositoryImpl)

    @Provides
    @Singleton
    fun provideValidatePresenter(
        userInteractorImpl: UserInteractorImpl,
        util: Util
    ) = UserPresenterImpl(userInteractorImpl, util)
}