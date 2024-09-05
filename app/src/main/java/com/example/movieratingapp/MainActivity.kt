package com.example.movieratingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieratingapp.ui.theme.MovieRatingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieRatingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    myapp()
                }
            }
        }
    }
}

@Composable
fun myapp(){
    val nav_controller = rememberNavController()
    var theEmail by remember { mutableStateOf("") }
    NavHost(
        navController = nav_controller,
        startDestination = "login_page")
    {
        composable("login_page"){
            loginpage(
                goToHomeUi = {
                    email->
                    theEmail = email
                    nav_controller.navigate("home_ui/$theEmail")
                }
            )
        }
        composable("home_ui/{email}"){
            val x = (it.arguments?.getString("email"))?:"user"
            homePage(
                emailName = x,
                goToLoginPage = {nav_controller.navigate("login_page") },
                goToShowMovie = {
                    movieid->
                    nav_controller.navigate("movie_details/${movieid}")
                }
            )
        }
        composable("movie_details/{id}"){
            val id = (it.arguments?.getString("id")?.toInt())?:0
            showMovie(
                theId = id,
                goToHomeUi = {
                    nav_controller.navigate("home_ui/$theEmail")
                }
            )
        }
    }
}
