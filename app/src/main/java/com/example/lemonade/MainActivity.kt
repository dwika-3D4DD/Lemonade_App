package com.example.lemonade

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    when(currentStep){
        1 -> {
            ImageAndText(
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random() },
                contentDescription = R.string.lemon_tree_content_description,
                image = R.drawable.lemon_tree,
                text = R.string.lemon_tree)
        }
        2 -> {
            ImageAndText(
                onImageClick = {
                    squeezeCount--
                    if(squeezeCount == 0){
                        currentStep = 3
                    } },
                contentDescription = R.string.lemon_content_description,
                image = R.drawable.lemon_squeeze,
                text = R.string.lemon)
        }
        3 -> {
            ImageAndText(
                onImageClick = { currentStep = 4 },
                contentDescription = R.string.glass_of_lemonade_content_description,
                image = R.drawable.lemon_drink,
                text = R.string.glass_of_lemonade)
        }
        4 -> {
            ImageAndText(
                onImageClick = { currentStep = 1 },
                contentDescription = R.string.empty_glass_content_description,
                image = R.drawable.lemon_restart,
                text = R.string.empty_glass)
        }
    }
}

@Composable
fun ImageAndText(
    onImageClick: () -> Unit,
    @StringRes contentDescription : Int,
    @DrawableRes image: Int,
    @StringRes text: Int
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0XFFC3ECD2))
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = contentDescription))
        }
        Text(
            text = stringResource(id = text),
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}