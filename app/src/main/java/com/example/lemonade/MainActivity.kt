package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageResource = when (result) {
            1 -> R.drawable.lemon_tree
            2 -> R.drawable.lemon_squeeze
            3 -> R.drawable.lemon_drink
            4 -> R.drawable.lemon_restart
            else -> R.drawable.lemon_tree
        }
        val textResource = when (result) {
            1 -> R.string.tap_lemon
            2 -> R.string.squeeze_lemon
            3 -> R.string.drink_lemonade
            4 -> R.string.restart
            else -> R.string.tap_lemon
        }

            Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString(),
            modifier = Modifier.clickable {
                result = if (result < 4) result + 1 else 1
            }
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
