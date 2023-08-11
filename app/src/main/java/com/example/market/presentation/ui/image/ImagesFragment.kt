package com.example.market.presentation.ui.image

import android.app.Activity
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract.EventDays.query
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.market.R
import com.example.market.databinding.FragmentImagesBinding
import com.example.market.presentation.ui.image.adapter.RvImagesAdapter
import com.example.market.presentation.ui.image.vm.ImagesFragmentViewModel
import com.example.market.presentation.ui.main.MainFragment
import com.example.market.utils.makeToast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.http.Url
import java.io.File
import java.net.URLConnection

@AndroidEntryPoint
class ImagesFragment:DialogFragment(R.layout.fragment_images) {
    private lateinit var binding : FragmentImagesBinding
    private var adapter = RvImagesAdapter()
    private val viewModel : ImagesFragmentViewModel by viewModels()

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data

                imgUri?.let {
                    uploadImageToServer(requireContext(),imgUri)
                }


//                selectedImage.setImageURI(imgUri)
            }
        }


    override fun getTheme(): Int = R.style.DialogTheme

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return object : Dialog(requireContext(),theme){
//            override fun onBackPressed() {
//                    Toast.makeText(requireContext(), "Выберите один из этих фотографий!!!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesBinding.bind(view)

        initVariables()

        initObservables()

        initListeners()
    }

    private fun initListeners() {

        binding.btnAddImage.setOnClickListener {
            makeToast("Находится в разработке!!!")
//            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//            changeImage.launch(pickImg)
        }

        adapter.onClickImage {
            findNavController().previousBackStackEntry?.savedStateHandle?.set(
                "imageUrl",
                it.imageUrl
            )
            findNavController().popBackStack()
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().onBackPressed()
                    }
                }
            }
            )
    }

    private fun initObservables() {
        viewModel.getAllImagesFlow.onEach {
            if (it.isEmpty()){
                binding.tvNoPhoto.visibility = View.VISIBLE
            }else{
                binding.tvNoPhoto.visibility = View.GONE
            }
            adapter.submitList(it)

        }.launchIn(lifecycleScope)

        viewModel.addImage.onEach {
            makeToast(it.message)
        }.launchIn(lifecycleScope)
    }

    private fun initVariables() {
        binding.rvImages.adapter = adapter

        lifecycleScope.launch {
            viewModel.getAllImages()
        }
    }


    private fun uploadImageToServer(context: Context, imageUrl: Uri) {
        val file = File(getRealPathFromURI(context, imageUrl))
//        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
//        val imageBody = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val contentType = URLConnection.guessContentTypeFromName(file.name)
        Log.d("CSS",contentType)
        val requestFile = RequestBody.create(contentType.toMediaTypeOrNull(), file)
        val imagePart = MultipartBody.Part.createFormData("file", file.name, requestFile)

        lifecycleScope.launch {
            viewModel.addImage(imagePart)
        }

    }

    private fun getRealPathFromURI(context: Context,imageUrl: Uri): String {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.contentResolver.query(imageUrl, projection, null, null, null)
        cursor?.let {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            val path = it.getString(columnIndex)
            it.close()
            return path
        }
        return ""
    }

}