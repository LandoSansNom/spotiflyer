///*
// *  * Copyright (c)  2021  Shabinder Singh
// *  * This program is free software: you can redistribute it and/or modify
// *  * it under the terms of the GNU General Public License as published by
// *  * the Free Software Foundation, either version 3 of the License, or
// *  * (at your option) any later version.
// *  *
// *  * This program is distributed in the hope that it will be useful,
// *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
// *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// *  * GNU General Public License for more details.
// *  *
// *  *  You should have received a copy of the GNU General Public License
// *  *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
// */
//
//package com.shabinder.common.uikit.screens
//
//import androidx.compose.animation.Crossfade
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentWidth
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.BottomSheetScaffold
//import androidx.compose.material.BottomSheetScaffoldDefaults
//import androidx.compose.material.Card
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.Icon
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.OutlinedButton
//import androidx.compose.material.Slider
//import androidx.compose.material.Surface
//import androidx.compose.material.Switch
//import androidx.compose.material.SwitchDefaults
//import androidx.compose.material.Tab
//import androidx.compose.material.TabPosition
//import androidx.compose.material.TabRow
//import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
//import androidx.compose.material.Text
//import androidx.compose.material.TextField
//import androidx.compose.material.TextFieldDefaults.textFieldColors
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.MusicNote
//import androidx.compose.material.icons.filled.Pause
//import androidx.compose.material.icons.filled.PlayArrow
//import androidx.compose.material.icons.filled.SkipNext
//import androidx.compose.material.icons.outlined.Download
//import androidx.compose.material.icons.outlined.History
//import androidx.compose.material.icons.outlined.Info
//import androidx.compose.material.icons.rounded.CardGiftcard
//import androidx.compose.material.icons.rounded.Edit
//import androidx.compose.material.icons.rounded.Flag
//import androidx.compose.material.icons.rounded.Insights
//import androidx.compose.material.icons.rounded.Share
//import androidx.compose.material.rememberBottomSheetScaffoldState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
//import com.shabinder.common.core_components.picture.Picture
//import com.shabinder.common.main.SpotiFlyerMain
//import com.shabinder.common.main.SpotiFlyerMain.HomeCategory
//import com.shabinder.common.models.DownloadRecord
//import com.shabinder.common.models.Actions
//import com.shabinder.common.database.Audio
//import com.shabinder.common.translations.Strings
//import com.shabinder.common.uikit.GaanaLogo
//import com.shabinder.common.uikit.GithubLogo
//import com.shabinder.common.uikit.ImageLoad
//import com.shabinder.common.uikit.SaavnLogo
//import com.shabinder.common.uikit.ShareImage
//import com.shabinder.common.uikit.SoundCloudLogo
//import com.shabinder.common.uikit.SpotifyLogo
//import com.shabinder.common.uikit.VerticalScrollbar
//import com.shabinder.common.uikit.YoutubeLogo
//import com.shabinder.common.uikit.YoutubeMusicLogo
//import com.shabinder.common.uikit.configurations.SpotiFlyerShapes
//import com.shabinder.common.uikit.configurations.SpotiFlyerTypography
//import com.shabinder.common.uikit.configurations.colorAccent
//import com.shabinder.common.uikit.configurations.colorOffWhite
//import com.shabinder.common.uikit.configurations.colorPrimary
//import com.shabinder.common.uikit.configurations.transparent
//import com.shabinder.common.uikit.dialogs.DonationDialogComponent
//import com.shabinder.common.uikit.rememberScrollbarAdapter
//import java.lang.Math.floor
//import androidx.core.net.toUri
//import java.io.File
//import android.media.MediaPlayer;
//
//
//
//@Composable
//fun SpotiFlyerMainContent(component: SpotiFlyerMain) {
//    val model by component.model.subscribeAsState()
//
//    val (openDonationDialog, _, _) = DonationDialogComponent {
//        component.dismissDonationDialogOffset()
//    }
//
//    Column {
//        SearchPanel(
//            model.link,
//            component::onInputLinkChanged,
//            component::onLinkSearch
//        )
//
//        HomeTabBar(
//            model.selectedCategory,
//            HomeCategory.values(),
//            component::selectCategory,
//        )
//
//        when (model.selectedCategory) {
//            HomeCategory.About -> AboutColumn(
//                analyticsEnabled = model.isAnalyticsEnabled,
//                toggleAnalytics = component::toggleAnalytics,
//                openDonationDialog = {
//                    component.analytics.donationDialogVisit()
//                    openDonationDialog()
//                }
//            )
//            HomeCategory.History -> HistoryColumn(
//                model.records.sortedByDescending { it.id },
//                component::loadImage,
//                component::onLinkSearch
//            )
//
//            HomeCategory.Downloads -> DownloadsColumn(
//                progress = 50f,
//                onProgressChange = {},
//                isAudioPlaying = true,
//                audioList = dummyAudioList,
//                currentPlayingAudio = dummyAudioList[0],
//                onStart = {},
//                onItemClick = {},
//                onNext = {}
//            )
//        }
//    }
//}
//
//private val dummyAudioList = listOf(
//    Audio(
//        uri = "".toUri(),
//        displayName = "Kotlin Programming",
//        id = 0L,
//        artist = "Hood",
//        data = "",
//        duration = 12345,
//        title = "Android Programming"
//    ),
//    Audio(
//        uri = "".toUri(),
//        displayName = "Kotlin Programming",
//        id = 0L,
//        artist = "Lab",
//        data = "",
//        duration = 25678,
//        title = "Android Programming"
//    ),
//    Audio(
//        uri = "".toUri(),
//        displayName = "Kotlin Programming",
//        id = 0L,
//        artist = "Android Lab",
//        data = "",
//        duration = 8765454,
//        title = "Android Programming"
//    ),
//    Audio(
//        uri = "".toUri(),
//        displayName = "Kotlin Programming",
//        id = 0L,
//        artist = "Kotlin Lab",
//        data = "",
//        duration = 23456,
//        title = "Android Programming"
//    ),
//    Audio(
//        uri = "".toUri(),
//        displayName = "Kotlin Programming",
//        id = 0L,
//        artist = "Hood Lab",
//        data = "",
//        duration = 65788,
//        title = "Android Programming"
//    ),
//    Audio(
//        uri = "".toUri(),
//        displayName = "Kotlin Programming",
//        id = 0L,
//        artist = "Hood Lab",
//        data = "",
//        duration = 234567,
//        title = "Android Programming"
//    ),
//
//    )
//
//
//@Composable
//fun HomeTabBar(
//    selectedCategory: HomeCategory,
//    categories: Array<HomeCategory>,
//    selectCategory: (HomeCategory) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    val selectedIndex = categories.indexOfFirst { it == selectedCategory }
//    val indicator = @Composable { tabPositions: List<TabPosition> ->
//        HomeCategoryTabIndicator(
//            Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
//        )
//    }
//
//    TabRow(
//        backgroundColor = transparent,
//        selectedTabIndex = selectedIndex,
//        indicator = indicator,
//        modifier = modifier,
//    ) {
//        categories.forEachIndexed { index, category ->
//            Tab(
//                selected = index == selectedIndex,
//                onClick = { selectCategory(category) },
//                text = {
//                    Text(
//                        text = when (category) {
//                            HomeCategory.About -> Strings.about()
//                            HomeCategory.History -> Strings.history()
//                            HomeCategory.Downloads -> Strings.downloads()
//
//                        },
//                        style = MaterialTheme.typography.body2
//                    )
//                },
//                icon = {
//                    when (category) {
//                        HomeCategory.About -> Icon(Icons.Outlined.Info, Strings.infoTab())
//                        HomeCategory.History -> Icon(Icons.Outlined.History, Strings.historyTab())
//                        HomeCategory.Downloads -> Icon(Icons.Outlined.Download, Strings.downloadsTab())
//                    }
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun SearchPanel(
//    link: String,
//    updateLink: (String) -> Unit,
//    onSearch: (String) -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = modifier.padding(top = 16.dp)
//    ) {
//        TextField(
//            value = link,
//            onValueChange = updateLink,
//            leadingIcon = {
//                Icon(Icons.Rounded.Edit, Strings.linkTextBox(), tint = Color.LightGray)
//            },
//            label = { Text(text = Strings.pasteLinkHere(), color = Color.LightGray) },
//            singleLine = true,
//            textStyle = TextStyle.Default.merge(TextStyle(fontSize = 18.sp, color = Color.White)),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
//            modifier = modifier.padding(12.dp).fillMaxWidth()
//                .border(
//                    BorderStroke(
//                        2.dp,
//                        Brush.horizontalGradient(
//                            listOf(
//                                colorPrimary,
//                                colorAccent
//                            )
//                        )
//                    ),
//                    RoundedCornerShape(30.dp)
//                ),
//            shape = RoundedCornerShape(size = 30.dp),
//            colors = textFieldColors(
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent,
//                backgroundColor = Color.Black
//            )
//        )
//        OutlinedButton(
//            modifier = Modifier.padding(12.dp).wrapContentWidth(),
//            onClick = {
//                if (link.isBlank()) Actions.instance.showPopUpMessage(Strings.enterALink())
//                else {
//                    // TODO if(!isOnline(ctx)) showPopUpMessage("Check Your Internet Connection") else
//                    onSearch(link)
//                }
//            },
//            border = BorderStroke(
//                1.dp,
//                Brush.horizontalGradient(
//                    listOf(
//                        colorPrimary,
//                        colorAccent
//                    )
//                )
//            )
//        ) {
//            Text(text = Strings.search(), style = SpotiFlyerTypography.h6, modifier = Modifier.padding(4.dp))
//        }
//    }
//}
//
//@Composable
//fun AboutColumn(
//    modifier: Modifier = Modifier,
//    analyticsEnabled: Boolean,
//    openDonationDialog: () -> Unit,
//    toggleAnalytics: (enabled: Boolean) -> Unit
//) {
//
//    Box {
//        val stateVertical = rememberScrollState(0)
//
//        Column(modifier.fillMaxSize().padding(8.dp).verticalScroll(stateVertical)) {
//            Card(
//                modifier = modifier.fillMaxWidth(),
//                border = BorderStroke(1.dp, Color.Gray)
//            ) {
//                Column(modifier.padding(12.dp)) {
//                    Text(
//                        text = Strings.supportedPlatforms(),
//                        style = SpotiFlyerTypography.body1,
//                        color = colorAccent
//                    )
//                    Spacer(modifier = Modifier.padding(top = 12.dp))
//                    Row(horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()) {
//                        Icon(
//                            SpotifyLogo(),
//                            "${Strings.open()} Spotify",
//                            tint = Color.Unspecified,
//                            modifier = Modifier.clip(SpotiFlyerShapes.small).clickable(
//                                onClick = { Actions.instance.openPlatform("com.spotify.music", "https://open.spotify.com") }
//                            )
//                        )
//                        Spacer(modifier = modifier.padding(start = 16.dp))
//                        Icon(
//                            GaanaLogo(),
//                            "${Strings.open()} Gaana",
//                            tint = Color.Unspecified,
//                            modifier = Modifier.clip(SpotiFlyerShapes.small).clickable(
//                                onClick = { Actions.instance.openPlatform("com.gaana", "https://www.gaana.com") }
//                            )
//                        )
//                        Spacer(modifier = modifier.padding(start = 16.dp))
//                        Icon(
//                            SaavnLogo(),
//                            "${Strings.open()} Jio Saavn",
//                            tint = Color.Unspecified,
//                            modifier = Modifier.clickable(
//                                onClick = { Actions.instance.openPlatform("com.jio.media.jiobeats", "https://www.jiosaavn.com/") }
//                            )
//                        )
//                        Spacer(modifier = modifier.padding(start = 16.dp))
//                        Icon(
//                            YoutubeLogo(),
//                            "${Strings.open()} Youtube",
//                            tint = Color.Unspecified,
//                            modifier = Modifier.clip(SpotiFlyerShapes.small).clickable(
//                                onClick = { Actions.instance.openPlatform("com.google.android.youtube", "https://m.youtube.com") }
//                            )
//                        )
//                        Spacer(modifier = modifier.padding(start = 12.dp))
//                        Icon(
//                            YoutubeMusicLogo(),
//                            "${Strings.open()} Youtube Music",
//                            tint = Color.Unspecified,
//                            modifier = Modifier.clip(SpotiFlyerShapes.small).clickable(
//                                onClick = { Actions.instance.openPlatform("com.google.android.apps.youtube.music", "https://music.youtube.com/") }
//                            )
//                        )
//                    }
//                    Spacer(modifier = Modifier.padding(top = 8.dp))
//                    Row(horizontalArrangement = Arrangement.Center, modifier = modifier.fillMaxWidth()) {
//                        Icon(
//                            SoundCloudLogo(),
//                            "${Strings.open()} Sound Cloud",
//                            tint = Color.Unspecified,
//                            modifier = Modifier.clip(SpotiFlyerShapes.medium).clickable(
//                                onClick = { Actions.instance.openPlatform("com.soundcloud.android", "https://soundcloud.com/") }
//                            )
//                        )
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.padding(top = 8.dp))
//            Card(
//                modifier = modifier.fillMaxWidth(),
//                border = BorderStroke(1.dp, Color.Gray) // Gray
//            ) {
//                Column(modifier.padding(12.dp)) {
//                    Text(
//                        text = Strings.supportDevelopment(),
//                        style = SpotiFlyerTypography.body1,
//                        color = colorAccent
//                    )
//                    Spacer(modifier = Modifier.padding(top = 6.dp))
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically,
//                        modifier = Modifier.fillMaxWidth().clickable(
//                            onClick = { Actions.instance.openPlatform("", "https://github.com/Shabinder/SpotiFlyer") }
//                        )
//                            .padding(vertical = 6.dp)
//                    ) {
//                        Icon(GithubLogo(), Strings.openProjectRepo(), Modifier.size(32.dp), tint = Color(0xFFCCCCCC))
//                        Spacer(modifier = Modifier.padding(start = 16.dp))
//                        Column {
//                            Text(
//                                text = "GitHub",
//                                style = SpotiFlyerTypography.h6
//                            )
//                            Text(
//                                text = Strings.starOrForkProject(),
//                                style = SpotiFlyerTypography.subtitle2
//                            )
//                        }
//                    }
//                    Row(
//                        modifier = modifier.fillMaxWidth().padding(vertical = 6.dp)
//                            .clickable(onClick = { Actions.instance.openPlatform("", "https://github.com/Shabinder/SpotiFlyer/blob/main/CONTRIBUTING.md") }),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(Icons.Rounded.Flag, Strings.help() + Strings.translate(), Modifier.size(32.dp))
//                        Spacer(modifier = Modifier.padding(start = 16.dp))
//                        Column {
//                            Text(
//                                text = Strings.translate(),
//                                style = SpotiFlyerTypography.h6
//                            )
//                            Text(
//                                text = Strings.helpTranslateDescription(),
//                                style = SpotiFlyerTypography.subtitle2
//                            )
//                        }
//                    }
//
//                    Row(
//                        modifier = modifier.fillMaxWidth().padding(vertical = 6.dp)
//                            .clickable(onClick = openDonationDialog),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(Icons.Rounded.CardGiftcard, Strings.supportDeveloper(), Modifier.size(32.dp))
//                        Spacer(modifier = Modifier.padding(start = 16.dp))
//                        Column {
//                            Text(
//                                text = Strings.donate(),
//                                style = SpotiFlyerTypography.h6
//                            )
//                            Text(
//                                text = Strings.donateDescription(),
//                                // text = "SpotiFlyer will always be, Free and Open-Source. You can however show us that you care by sending a small donation.",
//                                style = SpotiFlyerTypography.subtitle2
//                            )
//                        }
//                    }
//                    Row(
//                        modifier = modifier.fillMaxWidth().padding(vertical = 6.dp)
//                            .clickable(
//                                onClick = {
//                                    Actions.instance.shareApp()
//                                }
//                            ),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(Icons.Rounded.Share, Strings.share() + Strings.title() + "App", Modifier.size(32.dp))
//                        Spacer(modifier = Modifier.padding(start = 16.dp))
//                        Column {
//                            Text(
//                                text = Strings.share(),
//                                style = SpotiFlyerTypography.h6
//                            )
//                            Text(
//                                text = Strings.shareDescription(),
//                                style = SpotiFlyerTypography.subtitle2
//                            )
//                        }
//                    }
//                    Row(
//                        modifier = modifier.fillMaxWidth().padding(vertical = 6.dp)
//                            .clickable(
//                                onClick = {
//                                    toggleAnalytics(!analyticsEnabled)
//                                }
//                            ),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(Icons.Rounded.Insights, Strings.analytics() + Strings.status(), Modifier.size(32.dp))
//                        Spacer(modifier = Modifier.padding(start = 16.dp))
//                        Column(
//                            Modifier.weight(1f)
//                        ) {
//                            Text(
//                                text = Strings.analytics(),
//                                style = SpotiFlyerTypography.h6
//                            )
//                            Text(
//                                text = Strings.analyticsDescription(),
//                                style = SpotiFlyerTypography.subtitle2
//                            )
//                        }
//                        Switch(
//                            checked = analyticsEnabled,
//                            onCheckedChange = null,
//                            colors = SwitchDefaults.colors(uncheckedThumbColor = colorOffWhite)
//                        )
//                    }
//                }
//            }
//        }
//
//        VerticalScrollbar(
//            modifier = Modifier.padding(end = 2.dp).align(Alignment.CenterEnd).fillMaxHeight(),
//            adapter = rememberScrollbarAdapter(stateVertical)
//        )
//    }
//}
//
//@Composable
//fun HistoryColumn(
//    list: List<DownloadRecord>,
//    loadImage: suspend (String) -> Picture,
//    onItemClicked: (String) -> Unit
//) {
//    Crossfade(list) {
//        if (it.isEmpty()) {
//            Column(Modifier.padding(8.dp).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
//                Icon(
//                    Icons.Outlined.Info, Strings.noHistoryAvailable(), modifier = Modifier.size(80.dp),
//                    colorOffWhite
//                )
//                Text(Strings.noHistoryAvailable(), style = SpotiFlyerTypography.h4.copy(fontWeight = FontWeight.Light), textAlign = TextAlign.Center)
//            }
//        } else {
//            Box {
//
//                val listState = rememberLazyListState()
//                val itemList = it.distinctBy { record -> record.coverUrl }
//
//                LazyColumn(
//                    verticalArrangement = Arrangement.spacedBy(12.dp),
//                    content = {
//                        items(itemList) { record ->
//                            DownloadRecordItem(
//                                item = record,
//                                loadImage,
//                                onItemClicked
//                            )
//                        }
//                    },
//                    state = listState,
//                    modifier = Modifier.padding(top = 8.dp).fillMaxSize()
//                )
//            }
//        }
//    }
//}
//
//
//
//@Composable
//fun DownloadRecordItem(
//    item: DownloadRecord,
//    loadImage: suspend (String) -> Picture,
//    onItemClicked: (String) -> Unit
//) {
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth().padding(end = 8.dp)
//        ) {
//            ImageLoad(
//                item.coverUrl,
//                { loadImage(item.coverUrl) },
//                Strings.albumArt(),
//                modifier = Modifier.height(70.dp).width(70.dp).clip(SpotiFlyerShapes.medium)
//            )
//            Column(
//                modifier = Modifier.padding(horizontal = 8.dp).height(60.dp).weight(1f),
//                verticalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Text(
//                    item.name,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                    style = SpotiFlyerTypography.h6,
//                    color = colorAccent
//                )
//                Row(
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.Bottom,
//                    modifier = Modifier.padding(horizontal = 8.dp).fillMaxSize()
//                ) {
//                    Text(item.type, fontSize = 13.sp, color = colorOffWhite)
//                    Text(
//                        "${Strings.tracks()}: ${item.totalFiles}",
//                        fontSize = 13.sp,
//                        color = colorOffWhite
//                    )
//                }
//            }
//            Image(
//                ShareImage(),
//                Strings.reSearch(),
//                modifier = Modifier.clickable(
//                    onClick = {
//                        // if(!isOnline(ctx)) showDialog("Check Your Internet Connection") else
//                        onItemClicked(item.link)
//                    }
//                )
//            )
//        }
//}
//
//
//
//@Composable
//fun AudioItem(
//    item: DownloadRecord,
//    onItemClick: (id: Long) -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable {
//                onItemClick.invoke(audio.id)
//            },
//        backgroundColor = MaterialTheme.colors.surface.copy(alpha = .5f)
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Column(
//                modifier = Modifier
//                    .weight(1f)
//                    .padding(8.dp)
//            ) {
//                Spacer(modifier = Modifier.size(4.dp))
//                Text(
//                    text = audio.displayName,
//                    style = MaterialTheme.typography.h6,
//                    overflow = TextOverflow.Clip,
//                    maxLines = 1
//                )
//                Spacer(modifier = Modifier.size(4.dp))
//                Text(
//                    text = audio.artist,
//                    style = MaterialTheme.typography.subtitle1,
//                    maxLines = 1,
//                    overflow = TextOverflow.Clip,
//                    color = MaterialTheme.colors
//                        .onSurface
//                        .copy(alpha = .5f)
//                )
//
//            }
//            Text(text = timeStampToDuration(audio.duration.toLong()))
//            Spacer(modifier = Modifier.size(8.dp))
//        }
//
//    }
//
//
//}
//
//private fun timeStampToDuration(position:Long):String{
//    val totalSeconds = floor(position / 1E3).toInt()
//    val minutes = totalSeconds / 60
//    val remainingSeconds = totalSeconds - (minutes * 60)
//
//    return if (position < 0) "--:--"
//    else "%d:%02d".format(minutes,remainingSeconds)
//
//
//
//}
//
//@Composable
//fun HomeCategoryTabIndicator(
//    modifier: Modifier = Modifier,
//    color: Color = MaterialTheme.colors.onSurface
//) {
//    Spacer(
//        modifier.padding(horizontal = 24.dp)
//            .height(3.dp)
//            .background(color, RoundedCornerShape(topStartPercent = 100, topEndPercent = 100))
//    )
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun DownloadsColumn(
//    progress: Float,
//    onProgressChange: (Float) -> Unit,
//    isAudioPlaying: Boolean,
//    audioList: List<Audio>,
//    currentPlayingAudio: Audio?,
//    onStart: (Audio) -> Unit,
//    onItemClick: (Audio) -> Unit,
//    onNext: () -> Unit
//){
//
//    val directoryPath = "/storage/emulated/0/Music/SpotiFlyer/"
//    val directory = File(directoryPath)
//    val mediaPlayer = remember { MediaPlayer() }
//
////    if (directory.exists() && directory.isDirectory) {
//        val musicFiles = directory.listFiles { file ->
//            file.isFile && file.extension.equals("mp3", ignoreCase = true)
//        }
////    }
//    val scaffoldState = rememberBottomSheetScaffoldState()
//
//    val animatedHeight by animateDpAsState(
//        targetValue = if (currentPlayingAudio == null) 0.dp
//        else BottomSheetScaffoldDefaults.SheetPeekHeight
//    )
//
//    val listState = rememberLazyListState()
//    var recordIdCounter : Long = 1 // Initialize the counter with 1
//
//    BottomSheetScaffold(
//        sheetContent = {
//            currentPlayingAudio?.let { currentPlayingAudio ->
//                BottomBarPlayer(
//                    progress = progress,
//                    onProgressChange = onProgressChange,
//                    audio = currentPlayingAudio,
//                    isAudioPlaying = isAudioPlaying,
//                    onStart = { onStart.invoke(currentPlayingAudio) },
//                    onNext = { onNext.invoke() }
//                )
//
//            }
//        },
//        scaffoldState = scaffoldState,
//        sheetPeekHeight = animatedHeight
//    ) {
//        LazyColumn(
//            contentPadding = PaddingValues(bottom = 56.dp)
//        ) {
//            items(musicFiles) { file ->
//                val title = extractTitleFromFileName(file.name) // Extract title from the file name
//
//                // Create a DownloadRecord for each music file.
//                val record = DownloadRecord(
//                    id = recordIdCounter, // Replace with an appropriate ID
//                    type = "Audio", // Replace with the appropriate type
//                    name = title, // Use the extracted title
//                    link = file.absolutePath, // Use the file path as the link
//                    coverUrl =  "", // Provide the cover image URL if available
//                    totalFiles = recordIdCounter
//                )
//                recordIdCounter++
//
//                // Create a DownloadItem for each music file.
//                AudioItem(
//                    item = record
//                )
//            },
//        state = listState,
//        modifier = Modifier.padding(top = 8.dp).fillMaxSize()
//                )
//            }
//        }
//
//    }
//}
//
//@Composable
//fun BottomBarPlayer(
//    progress: Float,
//    onProgressChange: (Float) -> Unit,
//    audio: Audio,
//    isAudioPlaying: Boolean,
//    onStart: () -> Unit,
//    onNext: () -> Unit
//) {
//    Column {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(56.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            ArtistInfo(
//                audio = audio,
//                modifier = Modifier.weight(1f),
//            )
//            MediaPlayerController(
//                isAudioPlaying = isAudioPlaying,
//                onStart = { onStart.invoke() },
//                onNext = { onNext.invoke() }
//            )
//        }
//        Slider(
//            value = progress,
//            onValueChange = { onProgressChange.invoke(it) },
//            valueRange = 0f..100f
//        )
//
//
//    }
//
//
//}
//
//@Composable
//fun MediaPlayerController(
//    isAudioPlaying: Boolean,
//    onStart: () -> Unit,
//    onNext: () -> Unit
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .height(56.dp)
//            .padding(4.dp)
//    ) {
//        PlayerIconItem(
//            icon = if (isAudioPlaying) Icons.Default.Pause
//            else Icons.Default.PlayArrow,
//            backgroundColor = MaterialTheme.colors.primary
//        ) {
//            onStart.invoke()
//        }
//        Spacer(modifier = Modifier.size(8.dp))
//        Icon(
//            imageVector = Icons.Default.SkipNext,
//            contentDescription = null,
//            modifier = Modifier.clickable {
//                onNext.invoke()
//            }
//        )
//    }
//
//
//}
//
//
//@Composable
//fun ArtistInfo(
//    modifier: Modifier = Modifier,
//    audio: Audio
//) {
//    Row(
//        modifier = modifier.padding(4.dp),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//
//        PlayerIconItem(
//            icon = Icons.Default.MusicNote,
//            border = BorderStroke(
//                width = 1.dp,
//                color = MaterialTheme.colors.onSurface
//            ),
//        ) {}
//        Spacer(modifier = Modifier.size(4.dp))
//
//        Column {
//            Text(
//                text = audio.title,
//                fontWeight = FontWeight.Bold,
//                style = MaterialTheme.typography.h6,
//                overflow = TextOverflow.Clip,
//                modifier = Modifier.weight(1f),
//                maxLines = 1
//            )
//            Spacer(modifier = Modifier.size(4.dp))
//            Text(
//                text = audio.artist,
//                fontWeight = FontWeight.Normal,
//                style = MaterialTheme.typography.subtitle1,
//                overflow = TextOverflow.Clip,
//                maxLines = 1
//            )
//        }
//
//
//    }
//
//
//}
//
//@Composable
//fun PlayerIconItem(
//    modifier: Modifier = Modifier,
//    icon: ImageVector,
//    border: BorderStroke? = null,
//    backgroundColor: Color = MaterialTheme.colors.surface,
//    color: Color = MaterialTheme.colors.onSurface,
//    onClick: () -> Unit
//) {
//
//    Surface(
//        shape = CircleShape,
//        border = border,
//        modifier = Modifier
//            .clip(CircleShape)
//            .clickable {
//                onClick.invoke()
//            },
//        contentColor = color,
//        color = backgroundColor
//
//    ) {
//        Box(
//            modifier = Modifier.padding(4.dp),
//            contentAlignment = Alignment.Center,
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = null,
//            )
//
//        }
//
//
//    }
//}
//
//fun extractTitleFromFileName(fileName: String): String {
//    // Remove the file extension (e.g., ".mp3")
//    val nameWithoutExtension = fileName.substringBeforeLast(".")
//
//    // If your file names have underscores or other separators, you can replace them with spaces
//    val title = nameWithoutExtension.replace("_", " ")
//
//    // Capitalize the first letter of each word (optional)
//    val capitalizedTitle = title.split(" ").joinToString(" ") { it.capitalize() }
//
//    return capitalizedTitle
//}
