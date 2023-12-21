package com.riveong.animalink.data.api

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.ForumAddResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.IOException


suspend fun uploadForum(bitmap: Bitmap, context: Context, name: String, price: String, description: String, forumTypeId: String, forumCategoryId: String, store: ProfileStore): ForumAddResponse? {
    var ttoken = ""
    val token: LiveData<String> = store.getTokenProfile.asLiveData()
    withContext(Dispatchers.Main) {
        token.observeForever { data: String ->
            ttoken = data
        }
    }

    val client = OkHttpClient()

    // Convert the Bitmap to a File
    val file = bitmapToFile(bitmap, context)

    // Create a MultipartBody.Part
    val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
    val body = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("attachment", file.name, requestBody)
        .addFormDataPart("name", name)
        .addFormDataPart("price", price)
        .addFormDataPart("description", description)
        .addFormDataPart("forum_type_id", forumTypeId)
        .addFormDataPart("forum_category_id", forumCategoryId)
        .build()

    // Create a Request
    val request = Request.Builder()
        .url("https://animalink-backend-api-khmozn4qbq-et.a.run.app/api/forums")
        .header("Authorization", "Bearer $ttoken")  // Add the JWT token here
        .post(body)
        .build()

    // Send the request
    return client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        // Handle the response
        forumResponse(response.body?.string())
    }
}



//TODO ADD RESPONSE
fun forumResponse(json: String?): ForumAddResponse {
    // Implement this function to parse the JSON response to a DetectResponse object
    // ...
    return Gson().fromJson(json, ForumAddResponse::class.java)
}





