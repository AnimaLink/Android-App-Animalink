package com.riveong.animalink.data.api

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import com.riveong.animalink.data.model.DetectResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.UUID

suspend fun uploadImage(bitmap: Bitmap, context: Context): DetectResponse? {
    val client = OkHttpClient()

    // Convert the Bitmap to a File
    val file = bitmapToFile(bitmap, context)

    // Create a MultipartBody.Part
    val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
    val body = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("file", file.name, requestBody)
        .build()

    // Create a Request
    val request = Request.Builder()
        .url("https://ml-deploy-khmozn4qbq-et.a.run.app/predict")
        .post(body)
        .build()

    // Send the request
    return client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        // Handle the response
        parseDetectResponse(response.body?.string())
    }
}



fun bitmapToFile(bitmap: Bitmap, context: Context): File {
    // Create a file
    val file = File(context.externalCacheDir, UUID.randomUUID().toString() + ".png")

    // Convert the bitmap to a file
    val bos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
    val bitmapData = bos.toByteArray()

    // Write the bytes in file
    val fos = FileOutputStream(file)
    fos.write(bitmapData)
    fos.flush()
    fos.close()

    return file
}

fun parseDetectResponse(json: String?): DetectResponse {
    // Implement this function to parse the JSON response to a DetectResponse object
    // ...
    return Gson().fromJson(json, DetectResponse::class.java)
}