package com.example.ciclevidaeac1ferrater

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ciclevidaeac1ferrater.ui.AppUiState
import com.example.ciclevidaeac1ferrater.ui.AppViewModel
import com.example.ciclevidaeac1ferrater.ui.theme.CicleVidaEAC1FerraterTheme

class MainActivity : ComponentActivity() {

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen(appViewModel)
        }
        appViewModel.gestionaLog("onCreate()")
    }

    override fun onStart() {
        super.onStart()
        appViewModel.gestionaLog("onStart()")
    }

    override fun onResume() {
        super.onResume()
        appViewModel.gestionaLog("onResume()")
    }

    override fun onPause() {
        super.onPause()
        appViewModel.gestionaLog("onPause()")
    }

    override fun onDestroy() {
        super.onDestroy()
        appViewModel.gestionaLog("onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        appViewModel.gestionaLog("onRestart()")
    }
}

@Composable
fun AppScreen(appViewModel: AppViewModel) {

    val appUiState by appViewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp)
    ) {
        LogoWithAuthorName()
        Spacer(modifier = Modifier.height(23.dp))
        AppTitle()
        Spacer(modifier = Modifier.height(23.dp))
        ListOfCiclesWithResetButton(
            appUiState = appUiState,
            appViewModel = appViewModel
        )
    }
}

@Composable
fun ListOfCiclesWithResetButton(
    appViewModel: AppViewModel,
    appUiState: AppUiState
) {
    Button(onClick = { appViewModel.clearList() }) {
        Text(stringResource(id = R.string.btn_reset))
    }
    LazyColumn {
        itemsIndexed(appUiState.listOfLiveCicles) { index, item ->
            Text(
                fontSize = 25.sp,
                text = "${index + 1}: $item"
            )
        }
    }

}

@Composable
fun LogoWithAuthorName() {
    Image(
        painter = painterResource(id = R.drawable.avatar),
        contentDescription = stringResource(id = R.string.autor),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
    )
    Text(text = stringResource(id = R.string.autor))

}

@Composable
fun AppTitle() {
    Text(
        fontSize = 25.sp,
        text = stringResource(id = R.string.titul),
    )
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview(appViewModel: AppViewModel = AppViewModel()) {
    AppScreen(appViewModel)

}