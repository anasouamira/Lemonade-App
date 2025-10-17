package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
                LemonadeApp() // Call the main composable function
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    // 'step' determines which screen is currently shown (1 to 4)
    var step by remember { mutableIntStateOf(1) }

    // Random number of taps required to squeeze the lemon
    val squeezeCount = remember { (4..6).random() }

    // Counter for how many times the user has tapped to squeeze
    var squeezeStep by remember { mutableIntStateOf(0) }

    // Scaffold provides a layout structure with a fixed TopAppBar
    Scaffold(
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Yellow)
                    .padding(vertical = 8.dp),
                text = stringResource(R.string.Lemon),
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
        },
        containerColor = Color.White
    ) { innerPadding ->

        // Main content area below the TopAppBar
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .wrapContentSize(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Use 'when' to display the correct screen based on the current step
            when (step) {

                // STEP 1: Lemon tree screen
                1 -> {
                    Button(
                        onClick = { step = 2 }, // Move to step 2 when clicked
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC2EBD1)),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.lemon_tree),
                            contentDescription = stringResource(R.string.Lemon_tree)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.Tap_to_select_a_lemon),
                        fontSize = 18.sp
                    )
                }

                // STEP 2: Squeeze the lemon screen
                2 -> {
                    Button(
                        onClick = {
                            squeezeStep++ // Increment each tap
                            if (squeezeStep >= squeezeCount) {
                                // Move to the next step when enough taps are done
                                step = 3
                                squeezeStep = 0
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC2EBD1)),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.lemon_squeeze),
                            contentDescription = stringResource(R.string.Lemon)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.Keep_to_squeeze_it),
                        fontSize = 18.sp
                    )
                }

                // STEP 3: Drink the lemonade screen
                3 -> {
                    Button(
                        onClick = { step = 4 }, // Move to the final step
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC2EBD1)),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.lemon_drink),
                            contentDescription = stringResource(R.string.Glass_of_lemonade)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.Tap_to_drink_it),
                        fontSize = 18.sp
                    )
                }

                // STEP 4: Empty glass screen — restart the process
                4 -> {
                    Button(
                        onClick = { step = 1 }, // Go back to step 1
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC2EBD1)),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.lemon_restart),
                            contentDescription = stringResource(R.string.Empty_glass)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(R.string.Tap_to_start_again),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeTheme {
        LemonadeApp() // Preview of the main UI
    }
}
