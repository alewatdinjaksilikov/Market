package com.example.market.di

import com.example.market.domain.repository.login.LoginRepository
import com.example.market.domain.repository.main.MainRepository
import com.example.market.domain.usecase.addCategory.AddCategoryUseCase
import com.example.market.domain.usecase.addCategory.impl.AddCategoryUseCaseImpl
import com.example.market.domain.usecase.addImage.AddImageUseCase
import com.example.market.domain.usecase.addImage.impl.AddImageUseCaseImpl
import com.example.market.domain.usecase.addProduct.AddProductUseCase
import com.example.market.domain.usecase.addProduct.impl.AddProductUseCaseImpl
import com.example.market.domain.usecase.deleteCategory.DeleteCategoryUseCase
import com.example.market.domain.usecase.deleteCategory.impl.DeleteCategoryUseCaseImpl
import com.example.market.domain.usecase.deleteProduct.DeleteProductUseCase
import com.example.market.domain.usecase.deleteProduct.impl.DeleteProductUseCaseImpl
import com.example.market.domain.usecase.editCategory.EditCategoryUseCase
import com.example.market.domain.usecase.editCategory.impl.EditCategoryUseCaseImpl
import com.example.market.domain.usecase.editPassword.EditPasswordUseCaseImpl
import com.example.market.domain.usecase.editPassword.impl.EditPasswordUseCase
import com.example.market.domain.usecase.editProduct.EditProductUseCase
import com.example.market.domain.usecase.editProduct.impl.EditProductUseCaseImpl
import com.example.market.domain.usecase.editProfile.EditProfileUseCase
import com.example.market.domain.usecase.editProfile.impl.EditProfileUseCaseImpl
import com.example.market.domain.usecase.getAllBuy.GetAllBuyUseCase
import com.example.market.domain.usecase.getAllBuy.impl.GetAllBuyUseCaseImpl
import com.example.market.domain.usecase.getAllCategorires.GetAllCategoriesUseCase
import com.example.market.domain.usecase.getAllCategorires.impl.GetAllCategoriesUseCaseImpl
import com.example.market.domain.usecase.getAllImages.GetAllImagesUseCase
import com.example.market.domain.usecase.getAllImages.impl.GetAllImagesUseCaseImpl
import com.example.market.domain.usecase.getAllProducts.GetAllProductsUseCase
import com.example.market.domain.usecase.getAllProducts.impl.GetAllProductsUseCaseImpl
import com.example.market.domain.usecase.getAllProductsByCategory.GetAllProductsByCategoryUseCase
import com.example.market.domain.usecase.getAllProductsByCategory.impl.GetAllProductsByCategoryUseCaseImpl
import com.example.market.domain.usecase.getAllSale.GetAllSaleUseCase
import com.example.market.domain.usecase.getAllSale.impl.GetAllSaleUseCaseImpl
import com.example.market.domain.usecase.getCategoryById.GetCategoryByIdUseCase
import com.example.market.domain.usecase.getCategoryById.impl.GetCategoryByIdUseCaseImpl
import com.example.market.domain.usecase.getProductByName.GetProductByNameUseCase
import com.example.market.domain.usecase.getProductByName.impl.GetProductByNameUseCaseImpl
import com.example.market.domain.usecase.getStatistics.GetStatisticsUseCase
import com.example.market.domain.usecase.getStatistics.impl.GetStatisticsUseCaseImpl
import com.example.market.domain.usecase.login.auth.AuthorizationUseCase
import com.example.market.domain.usecase.login.auth.impl.AuthorizationUseCaseImpl
import com.example.market.domain.usecase.login.registration.RegistrationUseCase
import com.example.market.domain.usecase.login.registration.impl.RegistrationUseCaseImpl
import com.example.market.domain.usecase.sellProduct.SellProductUseCase
import com.example.market.domain.usecase.sellProduct.impl.SellProductUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlin.math.log

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun getAllCategoriesUseCase(mainRepository: MainRepository): GetAllCategoriesUseCase {
        return GetAllCategoriesUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun addCategoryUseCase(mainRepository: MainRepository): AddCategoryUseCase {
        return AddCategoryUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun addProductUseCase(mainRepository: MainRepository): AddProductUseCase {
        return AddProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun deleteCategoryUseCase(mainRepository: MainRepository): DeleteCategoryUseCase {
        return DeleteCategoryUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun deleteProductUseCase(mainRepository: MainRepository): DeleteProductUseCase {
        return DeleteProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun editCategoryUseCase(mainRepository: MainRepository): EditCategoryUseCase {
        return EditCategoryUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getCategoryByIdUseCase(mainRepository: MainRepository): GetCategoryByIdUseCase {
        return GetCategoryByIdUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun editProductUseCase(mainRepository: MainRepository): EditProductUseCase {
        return EditProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllImagesUseCase(mainRepository: MainRepository): GetAllImagesUseCase {
        return GetAllImagesUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllProductByCategoryUseCase(mainRepository: MainRepository): GetAllProductsByCategoryUseCase {
        return GetAllProductsByCategoryUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getProductByNameUseCase(mainRepository: MainRepository): GetProductByNameUseCase {
        return GetProductByNameUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllProductsUseCase(mainRepository: MainRepository): GetAllProductsUseCase {
        return GetAllProductsUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun sellProductUseCase(mainRepository: MainRepository): SellProductUseCase {
        return SellProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllBuyUseCase(mainRepository: MainRepository): GetAllBuyUseCase {
        return GetAllBuyUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getAllSaleUseCase(mainRepository: MainRepository): GetAllSaleUseCase {
        return GetAllSaleUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun getStatistics(mainRepository: MainRepository): GetStatisticsUseCase {
        return GetStatisticsUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun authorizationUseCase(loginRepository: LoginRepository): AuthorizationUseCase {
        return AuthorizationUseCaseImpl(loginRepository = loginRepository)
    }

    @Provides
    fun registrationUseCase(loginRepository: LoginRepository): RegistrationUseCase {
        return RegistrationUseCaseImpl(loginRepository = loginRepository)
    }

    @Provides
    fun addImageUseCase(mainRepository: MainRepository): AddImageUseCase {
        return AddImageUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun editPassword(mainRepository: MainRepository): EditPasswordUseCase {
        return EditPasswordUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun editProfile(mainRepository: MainRepository): EditProfileUseCase {
        return EditProfileUseCaseImpl(mainRepository = mainRepository)
    }

}