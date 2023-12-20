package com.riveong.animalink.ui.components.forum

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import coil.compose.rememberImagePainter
import com.riveong.animalink.R
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewForum(
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val animals = arrayOf("Burung", "Ular")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(animals[0]) }
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf("") }

    val captureLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) { isSuccess ->
        if (isSuccess) {
            val filename = "${System.currentTimeMillis()}.jpg"
            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val imageFile = File.createTempFile(filename, ".jpg", storageDir)

            imageUri.value = imageFile.absolutePath
        } else {
            // The image capture failed. Handle the failure.
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            // Permission is granted. You can now launch the camera.
            val filename = "${System.currentTimeMillis()}.jpg"
            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val imageFile = File.createTempFile(filename, ".jpg", storageDir)

            imageUri.value = imageFile.absolutePath
            captureLauncher.launch(imageUri.value.toUri())
        } else {
            // Permission is denied. Handle the denial.
        }
    }


    Column(Modifier.padding(27.dp)) {
        Text(
            text = "Animal Listing",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(700),
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = rememberImagePainter(data = imageUri.value),
                contentDescription = null,
                modifier = Modifier
                    .height(169.dp)
                    .width(169.dp)
                    .clickable {
                        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) != PERMISSION_GRANTED)
                            permissionLauncher.launch(android.Manifest.permission.CAMERA)
                        else captureLauncher.launch(imageUri.value.toUri())
                    }
            )
        }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                singleLine = true,
                shape = RoundedCornerShape(size = 13.dp),
                modifier = modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                singleLine = true,
                shape = RoundedCornerShape(size = 13.dp),
                modifier = modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                singleLine = false,
                shape = RoundedCornerShape(size = 13.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(150.dp, 200.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        animals.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectedText = item
                                    expanded = false
                                }
                            )
                        }
                    }

                }

            }

            Button(onClick = {
                SubmitData(name, price, description, selectedText)
            }) {
                Text(text = "Submit")
            }
        }


    }



fun SubmitData(name:String,price:String,description:String,selectedText:String) {
    TODO("Not yet implemented")
}

@Composable
@Preview
fun testNew() {
    NewForum()
}