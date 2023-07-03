package com.example.market.di

import com.example.market.data.network.ApiService
import com.example.market.data.repository.MainRepositoryImpl
import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.*
import com.example.market.domain.usecase.impl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun getAllCategoriesUseCase(mainRepository: MainRepository):GetAllCategoriesUseCase{
        return GetAllCategoriesUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun addCategoryUseCase(mainRepository: MainRepository):AddCategoryUseCase{
        return AddCategoryUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun addProductUseCase(mainRepository: MainRepository):AddProductUseCase{
        return AddProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun deleteCategoryUseCase(mainRepository: MainRepository):DeleteCategoryUseCase{
        return DeleteCategoryUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun deleteProductUseCase(mainRepository: MainRepository):DeleteProductUseCase{
        return DeleteProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun editCategoryUseCase(mainRepository: MainRepository):EditCategoryUseCase{
        return EditCategoryUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun editProductUseCase(mainRepository: MainRepository):EditProductUseCase{
        return EditProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllImagesUseCase(mainRepository: MainRepository):GetAllImagesUseCase{
        return GetAllImagesUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllProductByCategoryUseCase(mainRepository: MainRepository):GetAllProductsByCategoryUseCase{
        return GetAllProductsByCategoryUseCaseImpl(mainRepository = mainRepository)
    }

}