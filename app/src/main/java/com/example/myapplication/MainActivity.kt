package com.example.myapplication

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdvanceMsg(msg = Message("jjjj","aljkkhjhjhjh"))
        }
    }
}

data class Message(
    var author: String,
    var text: String
)

@Composable
fun SimpleMsg(msg: Message)
{
    Image(painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "avatar")
    Text(msg.author)
    Text(text = msg.text)
}

@Composable
fun AdvanceMsg(msg: Message)
{
    Row(Modifier.padding(all = 8.dp)){
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "avatar",
            Modifier
                .clip(CircleShape)
                .size(80.dp))
        var isPressed: Boolean by remember { mutableStateOf(false) }
        Column(Modifier.clickable { isPressed = !isPressed }) {
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                Text(msg.author,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.primary)
            }
            Text(text = msg.text,
                style = MaterialTheme.typography.displaySmall,
                maxLines = if (isPressed) 1 else Int.MAX_VALUE)
        }
    }
}

@Preview
@Composable
fun Preview()
{
    MyApplicationTheme {
        Surface {
            AdvanceMsg(msg = Message("tft","f sorc"))
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun LazyView()
{
    MyApplicationTheme {
        Surface(modifier = Modifier.padding(5.dp)) {
            LazyColumn {
                item { AdvanceMsg(msg = Message("Lex","abcdefghijklmnopium")) }
                for (i in 1..100)
                {
                    item { AdvanceMsg(msg = Message("lex","msg $i")) }
                }
            }
        }
    }
}