package com.bizlergroup.stockcontrol.di

import com.bizlergroup.stockcontrol.domain.repository.login.LoginRepository
import com.bizlergroup.stockcontrol.domain.repository.main.MainRepository
import com.bizlergroup.stockcontrol.domain.usecase.category.addCategory.AddCategoryUseCase
import com.bizlergroup.stockcontrol.domain.usecase.category.addCategory.impl.AddCategoryUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.image.addImage.AddImageUseCase
import com.bizlergroup.stockcontrol.domain.usecase.image.addImage.impl.AddImageUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.product.addProduct.AddProductUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.addProduct.impl.AddProductUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.category.deleteCategory.DeleteCategoryUseCase
import com.bizlergroup.stockcontrol.domain.usecase.category.deleteCategory.impl.DeleteCategoryUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.product.deleteProduct.DeleteProductUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.deleteProduct.impl.DeleteProductUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.category.editCategory.EditCategoryUseCase
import com.bizlergroup.stockcontrol.domain.usecase.category.editCategory.impl.EditCategoryUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.editPassword.impl.EditPasswordUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.editPassword.EditPasswordUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.editProduct.EditProductUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.editProduct.impl.EditProductUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.editProfile.EditProfileUseCase
import com.bizlergroup.stockcontrol.domain.usecase.editProfile.impl.EditProfileUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.getAllBuy.GetAllBuyUseCase
import com.bizlergroup.stockcontrol.domain.usecase.getAllBuy.impl.GetAllBuyUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.category.getAllCategorires.GetAllCategoriesUseCase
import com.bizlergroup.stockcontrol.domain.usecase.category.getAllCategorires.impl.GetAllCategoriesUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.image.getAllImages.GetAllImagesUseCase
import com.bizlergroup.stockcontrol.domain.usecase.image.getAllImages.impl.GetAllImagesUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.product.getAllProducts.GetAllProductsUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.getAllProducts.impl.GetAllProductsUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.product.getAllProductsByCategory.GetAllProductsByCategoryUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.getAllProductsByCategory.impl.GetAllProductsByCategoryUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.getAllSale.GetAllSaleUseCase
import com.bizlergroup.stockcontrol.domain.usecase.getAllSale.impl.GetAllSaleUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.category.getCategoryById.GetCategoryByIdUseCase
import com.bizlergroup.stockcontrol.domain.usecase.category.getCategoryById.impl.GetCategoryByIdUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.product.getProductByName.GetProductByNameUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.getProductByName.impl.GetProductByNameUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.getStatistics.main.GetStatisticsMainUseCase
import com.bizlergroup.stockcontrol.domain.usecase.getStatistics.main.impl.GetStatisticsMainUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.getStatistics.statistics.GetStatisticsUseCase
import com.bizlergroup.stockcontrol.domain.usecase.getStatistics.statistics.impl.GetStatisticsUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.login.auth.AuthorizationUseCase
import com.bizlergroup.stockcontrol.domain.usecase.login.auth.impl.AuthorizationUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.login.registration.RegistrationUseCase
import com.bizlergroup.stockcontrol.domain.usecase.login.registration.impl.RegistrationUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.product.addAmount.AddAmountProductUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.addAmount.impl.AddAmountProductUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.product.sellProduct.SellProductUseCase
import com.bizlergroup.stockcontrol.domain.usecase.product.sellProduct.impl.SellProductUseCaseImpl
import com.bizlergroup.stockcontrol.domain.usecase.uploadStatistics.UploadStatisticsUseCase
import com.bizlergroup.stockcontrol.domain.usecase.uploadStatistics.impl.UploadStatisticsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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
    fun getStatisticsMain(mainRepository: MainRepository): GetStatisticsMainUseCase {
        return GetStatisticsMainUseCaseImpl(mainRepository = mainRepository)
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

    @Provides
    fun addAmountUseCase(mainRepository: MainRepository):AddAmountProductUseCase{
        return AddAmountProductUseCaseImpl(mainRepository = mainRepository)
    }

    @Provides
    fun uploadStatistics(mainRepository: MainRepository):UploadStatisticsUseCase{
        return UploadStatisticsUseCaseImpl(mainRepository = mainRepository)
    }
}