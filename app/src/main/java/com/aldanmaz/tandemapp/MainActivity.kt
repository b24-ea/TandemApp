package com.aldanmaz.tandemapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.aldanmaz.tandemapp.model.Response
import com.aldanmaz.tandemapp.ui.theme.Purple500
import com.aldanmaz.tandemapp.ui.theme.TandemAppTheme
import com.aldanmaz.tandemapp.viewmodel.UserViewModel
import kotlinx.coroutines.flow.Flow


const val USER_LIST_TEST_TAG = "task_list_test_tag"

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TandemAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    UserList(
                        userViewModel
                    )
                }
            }
        }
    }
}
@Composable
fun UserList(userViewModel: UserViewModel) {
    UserListDetail(userViewModel.user )
}
@Composable
fun UserListDetail(user: Flow<PagingData<Response>>) {
    val userListItem: LazyPagingItems<Response> = user.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(5f)
                .background(Purple500)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Tandem App",
                fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
            )
        }
        Spacer(modifier =Modifier.height(10.dp))

        LazyColumn(modifier = Modifier.testTag(USER_LIST_TEST_TAG)) {
            items(userListItem) { item ->
                item?.let {
                    UserLists(it)
                }
            }
        }
    }
}


@Composable
fun UserLists(userData: Response) {
    Card(
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = 12.dp
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White)
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                val image = rememberImagePainter(
                    data = userData.pictureUrl,
                    imageLoader = LocalImageLoader.current,
                    builder = {
                    }
                )
                Image(
                    painter = image,
                    contentDescription = "User Image",
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .align(CenterVertically)
            ) {

                Row {
                    Text(
                        text = userData.firstName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(text = userData.referenceCnt,
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(5.dp))
                }
                Text(
                    text = userData.topic,
                    fontWeight = FontWeight.Black,
                    fontSize = 12.sp,
                    color = Color.LightGray,
                    modifier = Modifier.padding(10.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.padding(7.dp)
                ) {
                    Text(
                        text = "NATIVE : ${userData.natives}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Color.Black,

                    )
                    Spacer(modifier = Modifier.width(20.dp))


                    Text(
                        text = "LEARNS : ${userData.learns}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    LikeButton()

                }

            }

        }
    }
}
@Composable
fun LikeButton(
) {
    var isLike by remember { mutableStateOf(false) }

    IconToggleButton(
        checked = isLike,
        onCheckedChange = { isLike = it },
        modifier = Modifier.padding(3.dp)

    ) {
        val tint by animateColorAsState(if (isLike) Color(0xFF1565C0) else Color(0xFFB0BEC5))
        Icon(
            Icons.Filled.ThumbUp,
            contentDescription = null,tint = tint ,
            modifier = Modifier.size(30.dp))
    }

}





