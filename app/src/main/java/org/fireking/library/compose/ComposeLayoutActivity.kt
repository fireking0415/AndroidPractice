package org.fireking.library.compose

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.fireking.ap.R
import org.fireking.library.kotlin.ext.intentFor

class ComposeLayoutActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<ComposeLayoutActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                NewsStory()
                UserBanner()
            }
        }
    }
}

@Composable
fun UserBanner() {
    Row(
        modifier = Modifier
            .shadow(1.dp, shape = RoundedCornerShape(1.dp))
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Avatar(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
        )
        Info(
            modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        )
        FollowBtn(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 12.dp)
        )
    }
}

@Composable
fun FollowBtn(modifier: Modifier) {
    Text(
        text = "Follow",
        modifier = modifier
            .width(80.dp)
            .clickable {

            }
            .shadow(3.dp, shape = RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Red, Color.Red
                    ),
                    startY = 0F,
                    endY = 80F
                )
            )
            .padding(8.dp),
        textAlign = TextAlign.Center,
        style = typography.body1.copy(color = Color.White)
    )
}

@Composable
fun Info(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Hatsune Miku",
            maxLines = 1,
            color = Color.Black,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                letterSpacing = 0.15.sp
            )
        )
        Text(
            text = "World is Mine、 Davenport, CaliforniaDavenport, California",
            maxLines = 1,
            color = Color.Black.copy(alpha = 0.75F),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                letterSpacing = 0.25.sp
            )
        )
    }
}

@Composable
fun Avatar(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.mipmap.ic_meizi),
        contentDescription = null,
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Blue, Color.Cyan, Color.Green, Color.Yellow),
                        start = Offset(0F, 0F),
                        end = Offset(100F, 100F)
                    )
                )
            )
            .border(
                shape = CircleShape,
                border = BorderStroke(4.dp, SolidColor(Color.White))
            )//串型调用，后面的先执行
    )
}

@Composable
fun NewsStory() {
    val image = painterResource(id = R.mipmap.header)
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = image, contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "A day in shark fin cove",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )
        Text(
            text = "Davenport, California",
            style = typography.h6
        )
        Text(
            text = "December 2018",
            style = typography.body2
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    UserBanner()
}