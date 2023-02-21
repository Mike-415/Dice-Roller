package com.example.diceroller

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.diceroller.ui.theme.DiceRollerTheme


private val TAG = "TAG_MAIN_ACTIVITY"
private var diceImageMap : Map<Int, Int>? = null


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        diceImageMap = mapOf(
            1 to R.drawable.dice_1,
            2 to R.drawable.dice_2,
            3 to R.drawable.dice_3,
            4 to R.drawable.dice_4,
            5 to R.drawable.dice_5,
            6 to R.drawable.dice_6
        )

        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp(){
    DiceWithButtonAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(
            Alignment.Center
        )
    )

}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier){
    // ^ The default value of the modifier parameter lets the caller decided
    //   whether or not to pass a value for the parameter.   If so, they can
    //   customize the properties and behaviours of the UI.
    var result by remember { mutableStateOf(1) }
    val imageResource = when(result){
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = imageResource), contentDescription = result.toString())
        Button(onClick = {
            result = (1..6).random()
            Log.d(TAG, "Dice roll button pressed.  Number is $result")
        }) {
            Text(text = stringResource(id = R.string.roll_dice))
        }

    }

}



