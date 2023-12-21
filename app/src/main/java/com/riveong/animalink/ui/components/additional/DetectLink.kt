package com.riveong.animalink.ui.components.additional


import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.riveong.animalink.R
import com.riveong.animalink.data.api.uploadImage
import com.riveong.animalink.data.model.DetectResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DetectLink() {
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val detectResponse = remember { mutableStateOf<DetectResponse?>(null) }
    val imageBitmap = remember { mutableStateOf<Bitmap?>(null) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            // Handle the returned bitmap
            bitmap?.let {
                imageBitmap.value = it
                GlobalScope.launch(Dispatchers.IO) {
                    val response = uploadImage(it, context)
                    withContext(Dispatchers.Main) {
                        detectResponse.value = response
                    }
                }
            }
        }
    Column(modifier = Modifier.padding(27.dp)) {


        Text(
            text = "DetectLink",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Welcome to DetectLink, Here you can detect animal and our AI technology will determine the extinction type! But first we need ur perms for using your camera!",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(500),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Image(
            painter = painterResource(id = R.drawable.instruction),
            contentDescription = null,
            Modifier.width(500.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        PermissionRequired(
            permissionState = cameraPermissionState,
            permissionNotGrantedContent = {
                Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                    Text("Request Camera Permission")
                }
            },
            permissionNotAvailableContent = {
                Text("Camera permission not available.")
            }
        ) {
            Button(onClick = { launcher.launch(null) }) {
                Text("Open Camera")
            }
        }
    }
    // Display the response
    detectResponse.value?.let { response ->
        imageBitmap.value?.let { bitmap ->
            Column(
                Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    contentDescription = "item picture",
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .heightIn(min = 700.dp)
                        .offset(y = -24.dp)

                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(
                                topStart = 27.dp,
                                topEnd = 27.dp
                            )
                        )
                ) {
                    Column(modifier = Modifier.padding(top = 33.dp, start = 33.dp, end = 33.dp)) {
                        Text(
                            text = response.predictedAnimal!!,
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                            )
                        )
                        Text(
                            text = "confidence: ${response.modelConfidence!!}",
                            style = TextStyle(
                                fontSize = 22.sp,
                                color = Color(0xFF000000),
                            )
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = response.message!!)
                        Spacer(modifier = Modifier.height(20.dp))


                    }


                }
            }


        }
    }

}