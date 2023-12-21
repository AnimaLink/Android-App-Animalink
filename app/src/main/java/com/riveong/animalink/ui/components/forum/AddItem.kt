package com.riveong.animalink.ui.components.forum

import android.Manifest
import android.graphics.Bitmap
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState
import com.riveong.animalink.data.api.uploadForum
import com.riveong.animalink.data.api.uploadImage
import com.riveong.animalink.data.datastore.ProfileStore
import com.riveong.animalink.data.model.Category
import com.riveong.animalink.data.model.DetectResponse
import com.riveong.animalink.data.model.ForumAddResponse
import com.riveong.animalink.data.model.ForumResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddItem(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val store = remember { ProfileStore(context) }
    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var forumTypeID by remember { mutableStateOf("forumTypeID") }
    var forumCategoryID by remember { mutableStateOf("forumCategoryID") }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val imageBitmap = remember { mutableStateOf<Bitmap?>(null) }

    val forumResponse = remember { mutableStateOf<ForumAddResponse?>(null) }


    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            // Handle the returned bitmap
            bitmap?.let {
                imageBitmap.value = it
                GlobalScope.launch(Dispatchers.IO) {

                    if (forumTypeID == "Product") {
                        forumTypeID = "1"
                    } else {
                        forumTypeID = "2"
                    }

                    if (forumCategoryID == "Bird") {
                        forumCategoryID = "1"
                    } else if (forumCategoryID == "Snake") {
                        forumCategoryID = "2"
                    } else if (forumCategoryID == "Dog") {
                        forumCategoryID = "3"
                    } else {
                        forumCategoryID = "1"
                    }

                    if (name != "" || price != "" || description != "") {
                        val response = uploadForum(
                            it,
                            description = description,
                            context = context,
                            name = name,
                            price = price,
                            forumTypeId = forumTypeID,
                            forumCategoryId = forumCategoryID,
                            store = store
                        )
                        withContext(Dispatchers.Main) {
                            forumResponse.value = response
                        }
                    } else {
                        Toast.makeText(context, "There are datas that need to be filled!", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }



    Column(Modifier.padding(27.dp)) {


        Text(
            text = "Add New Forum",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Item Name") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Item Price") },
            singleLine = true,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Item Description") },
            maxLines = 4,
            shape = RoundedCornerShape(size = 13.dp),
            modifier = modifier
                .fillMaxWidth()
                .height(140.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp, Color(0xFF25C5F5),
                    shape = RoundedCornerShape(20)

                )
                .padding(20.dp)
                .clickable { expanded = !expanded }
        ) {

            Text(text = forumTypeID)


            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Product") },
                    onClick = {
                        forumTypeID = "Product"

                    }
                )
                DropdownMenuItem(
                    text = { Text("Animal") },
                    onClick = {
                        forumTypeID = "Animal"

                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    2.dp, Color(0xFF25C5F5),
                    shape = RoundedCornerShape(20)

                )
                .padding(20.dp)
                .clickable { expanded2 = !expanded2 }

        ) {
            Text(text = forumCategoryID)

            DropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Bird") },
                    onClick = {
                        forumCategoryID = "Bird"

                    }
                )
                DropdownMenuItem(
                    text = { Text("Snake") },
                    onClick = {
                        forumCategoryID = "Snake"

                    }
                )
                DropdownMenuItem(
                    text = { Text("Dog") },
                    onClick = {
                        forumCategoryID = "Dog"

                    }
                )
            }
        }
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
}




