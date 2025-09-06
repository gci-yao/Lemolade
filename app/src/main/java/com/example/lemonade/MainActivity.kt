package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
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
fun Lemonade(modifier: Modifier = Modifier) {
    var result by remember { mutableIntStateOf(1) }
    var countSqueeze by remember { mutableIntStateOf(0) }
    var squeezeRequires by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFF99))
                .padding(vertical = 16.dp)
                .wrapContentSize(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Lemonade",
                fontSize = 24.sp,
                color = Color.Black
            )
        }
        val imageResource = when (result) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            4 -> R.drawable.lemon_restart
            else -> R.drawable.lemon_tree
        }
        val textResource = when (result) {
            1 -> R.string.lemon_tree
            2 -> R.string.lemon
            3 -> R.string.glass_of_lemonade
            4 -> R.string.empty_glass
            else -> R.string.lemon_tree
        }

        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(textResource),
            modifier = Modifier
                .clickable {
                    when(result){
                        1 ->{
                            result = 2
                            squeezeRequires  = (2..4).random()
                            countSqueeze = 0
                        }
                        2->{
                            countSqueeze++
                            if(countSqueeze >= squeezeRequires){
                                result = 3
                            }
                        }
                        3->{
                            result = 4
                        }
                        4->{
                            result = 1
                        }
                    }
                }
                .padding(16.dp)
                .background(Color(0xFF81C784).copy(alpha = 0.5f), shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
        )
        Text(
            text = stringResource(textResource),
            modifier = Modifier.padding(16.dp),
            fontSize = 21.sp,
            color = Color.Black,
        )
    }
}



@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    Lemonade(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}
