package com.example.market.di

import com.example.market.domain.repository.MainRepository
import com.example.market.domain.usecase.*
import com.example.market.domain.usecase.impl.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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

    @Provides
    fun getProductByNameUseCase(mainRepository: MainRepository):GetProductByNameUseCase{
        return GetProductByNameUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllProductsUseCase(mainRepository: MainRepository):GetAllProductsUseCase{
        return GetAllProductsUseCaseImpl(mainRepository= mainRepository)
    }

    @Provides
    fun sellProductUseCase(mainRepository: MainRepository):SellProductUseCase{
        return SellProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllBuyUseCase(mainRepository: MainRepository):GetAllBuyUseCase{
        return GetAllBuyUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllSaleUseCase(mainRepository: MainRepository):GetAllSaleUseCase{
        return GetAllSaleUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getStatistics(mainRepository: MainRepository):GetStatisticsUseCase{
        return GetStatisticsUseCaseImpl(mainRepository = mainRepository)
    }

}