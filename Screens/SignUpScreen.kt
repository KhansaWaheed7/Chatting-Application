package com.example.gochat.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gochat.CommonProgressBar
import com.example.gochat.DestinationScreen
import com.example.gochat.GCViewModel
import com.example.gochat.R
import com.example.gochat.navigateTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController, vm: GCViewModel) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .verticalScroll(
                rememberScrollState()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var nameState = remember {
                mutableStateOf(TextFieldValue())
            }
            var numberState = remember {
                mutableStateOf(TextFieldValue())
            }
            var emailState = remember {
                mutableStateOf(TextFieldValue())
            }
            var passwordState = remember {
                mutableStateOf(TextFieldValue())
            }
            var focus= LocalFocusManager.current
            Image(painter = painterResource(id =  R.drawable.chat)
            , contentDescription = null,
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp))
            Text(text = "Sign Up",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            OutlinedTextField(value =nameState.value , onValueChange = {
                nameState.value = it
            },
                label = {Text(text= "Name")},
                modifier = Modifier.padding(8.dp))

            OutlinedTextField(value =numberState.value , onValueChange = {
                numberState.value = it
            },
                label = {Text(text= "Number")},
                modifier = Modifier.padding(8.dp))

            OutlinedTextField(value =emailState.value , onValueChange = {
                emailState.value = it
            },
                label = {Text(text= "Email")},
                modifier = Modifier.padding(8.dp))

            OutlinedTextField(value =passwordState.value , onValueChange = {
                passwordState.value = it
            },
                label = {Text(text= "Password")},
                modifier = Modifier.padding(8.dp)
            )
            Button(onClick = {vm.signUp(
                nameState.value.text,
                numberState.value.text,
                emailState.value.text,
                passwordState.value.text
            )},
                modifier = Modifier.padding(8.dp)) {
                Text(text = "SIGN UP")
            }
            Text(
                text = "Already a User? Go to Login ->",
                color = Color.Blue,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        navigateTo(navController, DestinationScreen.Login.route)
                    }
            )

        }
    }
    if (vm.inProcess.value){
        CommonProgressBar()

    }
}
