package com.example.movieratingapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter


fun toogleShowPassworld(CB_1:Boolean):Boolean{
    if (CB_1){

        return false
    }else{
        return true
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginpage(goToHomeUi:(String)->Unit){
    var email :String by remember { mutableStateOf("") }
    var password :String by remember { mutableStateOf("") }
    var CB_1:Boolean by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .border(width = 3.dp, color = Color(0xFF001021), shape = RoundedCornerShape(25.dp))
                .padding(10.dp)
                .width(320.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Greetings User!",
                modifier = Modifier
                    .padding(10.dp)
                    .graphicsLayer { scaleX = 1.08f },
                style = TextStyle(fontFamily = FontFamily.Default, fontSize = 17.sp), fontWeight = FontWeight.Bold)
            Text(text = "Continue to Movies List App",
                modifier = Modifier
                    .padding(10.dp)
                    .graphicsLayer { scaleX = 1.08f },
                style = TextStyle(fontSize = 11.sp), fontWeight = FontWeight.Bold, color = Color.DarkGray
            )
            Spacer(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth())
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                placeholder = { Text(text = "Enter your email address", color = Color.DarkGray)},
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(0.dp)
                    .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent),
                )
            Spacer(modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth())
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = { Text(text = "Password", color = Color.DarkGray)},
                visualTransformation = if (CB_1) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = if (CB_1) KeyboardOptions.Default else KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(0.dp)
                    .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment =Alignment.CenterVertically
            ){
                Row (
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Spacer(modifier = Modifier.width(12.dp))
                    Checkbox(
                        checked = CB_1,
                        onCheckedChange = {
                            CB_1 = toogleShowPassworld(CB_1)
                        }
                    )
                    Text(text = "Show",
                        fontSize = 12.sp)
                }

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.White),
                )  {
                    Text(text = "Forgot password?", fontSize = 12.sp)
                }
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .background(color = Color(0xFF1481BA), shape = RoundedCornerShape(50.dp))
            ) {
                Text(text = "Login")
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Divider(
                    color = Color.DarkGray,
                    modifier = Modifier
                        .width(100.dp)
                        .padding(20.dp)
                )
                Text(text = "Or continue with",
                    modifier = Modifier
                        .padding(10.dp)
                        .graphicsLayer { scaleX = 1.08f },
                    style = TextStyle(fontSize = 11.sp), fontWeight = FontWeight.Bold, color = Color.DarkGray
                )
                Divider(
                        color = Color.DarkGray,
                modifier = Modifier
                    .width(100.dp)
                    .padding(20.dp)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(color = Color.Transparent),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent)
                    ) {
                    Icon(
                        painter = rememberImagePainter(data = "https://imagepng.org/wp-content/uploads/2019/08/google-icon.png"),
                        contentDescription = "Google",
                        tint = Color.Unspecified)
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(color = Color.Transparent),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent)
                ) {
                    Icon(
                        painter = rememberImagePainter(data = "https://www.pngkey.com/png/full/283-2831746_insta-icon-instagram.png"),
                        contentDescription = "Instagram",
                        tint = Color.Unspecified)
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(color = Color.Transparent),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent)
                ) {
                    Icon(
                        painter = rememberImagePainter(data = "https://images.vexels.com/media/users/3/223136/isolated/preview/984f500cf9de4519b02b354346eb72e0-facebook-icon-social-media-by-vexels.png"),
                        contentDescription = "facebook",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(25.6.dp))
                    }

            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp))
            Text(text = "Don't have an account? Join today!",
                modifier =Modifier.padding(10.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 10.sp,
                    color = Color(0xFF11B5E4)))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.White),
                modifier = Modifier
                    .border(width = 3.dp, color = Color.DarkGray, shape = RoundedCornerShape(50.dp))
                    .fillMaxWidth(0.9f)
            )  {
                Text(text = "Create Account")
            }
            Divider(
                color = Color.DarkGray,
                modifier = Modifier
                    .width(100.dp)
                    .padding(20.dp)
            )
            Button(
                onClick = {
                          goToHomeUi("User")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            )  {
                Text(text = "Continue as guest", color = Color.LightGray)
            }
        }
    }
}
