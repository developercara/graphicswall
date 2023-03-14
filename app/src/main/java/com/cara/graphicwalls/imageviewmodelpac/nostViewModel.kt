package com.cara.graphicwalls.imageviewmodelpac

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class nostViewModel : ViewModel() {
    private val imageRef = Firebase.storage.reference

    private var currentPage = 0
    private val pageSize = 3
    private var isLoading = false
    private var isLastPage = false

    private val _imageUrls = MutableLiveData<List<String>>()
    val imageUrls: LiveData<List<String>> = _imageUrls

    init {
        loadMoreItems()
    }

    fun loadMoreItems() {
        if (!isLoading && !isLastPage) {
            isLoading = true
            val startIndex = currentPage * pageSize
            val endIndex = startIndex + pageSize

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val images = imageRef.child("nostalgia/").listAll().await()
                    val newUrls = mutableListOf<String>()
                    for (image in images.items.subList(startIndex, endIndex)) {
                        val url = image.downloadUrl.await()
                        newUrls.add(url.toString())
                    }

                    withContext(Dispatchers.Main) {
                        currentPage++
                        isLastPage = newUrls.size < pageSize
                        val currentUrls = _imageUrls.value ?: emptyList()
                        _imageUrls.value = currentUrls + newUrls
                    }
                } catch (e: Exception) {
                    // Handle error
                }

                isLoading = false
            }
        }
    }
}