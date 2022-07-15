package com.example.prueba_ceiba.ui.di.modules

import com.example.prueba_ceiba.data.EndPoints
import com.example.prueba_ceiba.data.dataSource.cloud.ApiUserDetailImpl
import com.example.prueba_ceiba.data.dataSource.cloud.ApiUserImpl
import com.example.prueba_ceiba.data.mapper.PostEntityMapper
import com.example.prueba_ceiba.data.repository.UserDetailRepositoryImpl
import com.example.prueba_ceiba.domain.interactor.UserDetailInteractorImpl
import com.example.prueba_ceiba.ui.presenter.UserDetailPresenterImpl
import com.example.prueba_ceiba.utils.Util
import com.loopj.android.http.AsyncHttpClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDetailModule {

    @Provides
    @Singleton
    fun provideEndPoints() = EndPoints()

    @Provides
    @Singleton
    fun provideClient() = AsyncHttpClient()

    @Provides
    @Singleton
    fun postEntityMapper() = PostEntityMapper()

    @Provides
    @Singleton
    fun provideApiUserDetail(
        client: AsyncHttpClient,
        endPoints: EndPoints
    ) = ApiUserDetailImpl(client, endPoints)

    @Provides
    @Singleton
    fun provideValidateRepository(
        apiUserDetailImpl: ApiUserDetailImpl,
        postEntityMapper: PostEntityMapper
    ) = UserDetailRepositoryImpl(apiUserDetailImpl, postEntityMapper)

    @Provides
    @Singleton
    fun provideValidateInteractor(
        userDetailRepositoryImpl: UserDetailRepositoryImpl
    ) = UserDetailInteractorImpl(userDetailRepositoryImpl
    )

    @Provides
    @Singleton
    fun provideValidatePresenter(
        userDetailInteractorImpl: UserDetailInteractorImpl
    ) = UserDetailPresenterImpl(userDetailInteractorImpl)
}