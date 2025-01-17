package com.rsstudio.tradex.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rsstudio.tradex.alias.AppDrawable
import com.rsstudio.tradex.alias.AppString
import com.rsstudio.tradex.component.divider.VerticalDivider
import com.rsstudio.tradex.presentation.theme.captionDefault
import com.rsstudio.tradex.presentation.theme.corporateBlue
import com.rsstudio.tradex.presentation.theme.subTitle
import com.rsstudio.tradex.presentation.theme.white
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.rsstudio.tradex.component.bottomnav.BottomNavigation
import com.rsstudio.tradex.component.bottomnav.ExpandableHeader
import com.rsstudio.tradex.component.bottomnav.ExpandableRowConfig
import com.rsstudio.tradex.presentation.theme.ParagraphSmallRegular
import com.rsstudio.tradex.presentation.theme.captionBold
import com.rsstudio.tradex.presentation.theme.crimsonRed
import com.rsstudio.tradex.presentation.theme.gray
import com.rsstudio.tradex.presentation.theme.paleGray
import com.rsstudio.tradex.presentation.theme.tealGreen
import com.rsstudio.tradex.util.CoreUtil.ONE_UNIT_SPACE
import com.rsstudio.tradex.util.formatAsCurrency
import com.rsstudio.tradex.util.isPositiveAmount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    val uiState = viewModel.uiState
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = corporateBlue
        )
        systemUiController.setNavigationBarColor(
            color = paleGray,
            darkIcons = true
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.Blue),
                title = {
                    Row {
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            text = stringResource(AppString.portfolio),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 16.sp,
                                color = white
                            )
                        )
                    }
                },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.size(12.dp))
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = AppDrawable.ic_account_circle),
                            contentDescription = "",
                            tint = white
                        )
                    }

                },
                actions = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = AppDrawable.ic_swap),
                        contentDescription = "",
                        tint = white
                    )
                    VerticalDivider(modifier = Modifier.padding(8.dp))
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = AppDrawable.ic_search),
                        contentDescription = "",
                        tint = white
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = corporateBlue
                )
            )
        },
        bottomBar = {
            BottomNavigation(
                header = {
                    ExpandableHeader(
                        rows = listOf(
                            ExpandableRowConfig(
                                AppString.current_value,
                                uiState.sheetData.currentValue,
                                valueTextStyle = MaterialTheme.typography.captionBold
                            ),
                            ExpandableRowConfig(
                                AppString.total_investment,
                                uiState.sheetData.totalInvestment,
                                valueTextStyle = MaterialTheme.typography.captionBold
                            ),
                            ExpandableRowConfig(
                                AppString.today_profit_and_loss,
                                uiState.sheetData.todayProfitAndLoss,
                                valueTextStyle = MaterialTheme.typography.captionBold.copy(color = if (uiState.sheetData.todayProfitAndLoss.isPositiveAmount()) tealGreen else crimsonRed)
                            ),
                        ),
                        totalProfitAndLoss = uiState.sheetData.totalProfitAndLoss,
                    )
                }
            )
        }
    ) { innerPadding ->
        HomeScreenContent(
            uiState = uiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun HomeScreenContent(
    uiState: HomeScreenUiState,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(uiState.userHoldingData) { index, it ->
            if (index != 0) {
                Divider(
                    modifier = Modifier.fillMaxWidth(),
                    color = gray
                )
            }
            Item(
                symbol = it.symbol,
                quantity = it.quantity.toString(),
                ltp = it.ltp.formatAsCurrency(),
                pl = it.totalProfitAndLoss
            )
        }
    }
}

@Composable
fun Item(
    symbol: String,
    quantity: String,
    ltp: String,
    pl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(white)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = symbol,
                style = MaterialTheme.typography.subTitle
            )
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.captionDefault.toSpanStyle()
                    ) {
                        append(stringResource(id = AppString.net_qty))
                    }
                    append(ONE_UNIT_SPACE)
                    withStyle(
                        style = MaterialTheme.typography.ParagraphSmallRegular.toSpanStyle()
                    ) {
                        append(quantity)
                    }
                },
                style = MaterialTheme.typography.subTitle
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.captionDefault.toSpanStyle()
                    ) {
                        append(stringResource(id = AppString.ltp))
                    }
                    append(ONE_UNIT_SPACE)
                    withStyle(
                        style = MaterialTheme.typography.ParagraphSmallRegular.copy(fontSize = 14.sp)
                            .toSpanStyle()
                    ) {
                        append(ltp)
                    }
                },
                style = MaterialTheme.typography.subTitle,
            )
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = MaterialTheme.typography.captionDefault.toSpanStyle()
                    ) {
                        append(stringResource(id = AppString.pl))
                    }
                    append(ONE_UNIT_SPACE)
                    withStyle(
                        style = MaterialTheme.typography.ParagraphSmallRegular.copy(
                            color = if (pl.isPositiveAmount()) tealGreen else crimsonRed,
                            fontSize = 14.sp
                        )
                            .toSpanStyle()
                    ) {
                        append(pl)
                    }
                },
                style = MaterialTheme.typography.captionDefault
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.Blue),
                title = {
                    Row {
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            text = stringResource(AppString.portfolio),
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontSize = 16.sp,
                                color = white
                            )
                        )
                    }
                },
                navigationIcon = {
                    Row {
                        Spacer(modifier = Modifier.size(12.dp))
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = AppDrawable.ic_account_circle),
                            contentDescription = "",
                            tint = white
                        )
                    }

                },
                actions = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = AppDrawable.ic_swap),
                        contentDescription = "",
                        tint = white
                    )
                    VerticalDivider(modifier = Modifier.padding(8.dp))
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = AppDrawable.ic_search),
                        contentDescription = "",
                        tint = white
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = corporateBlue
                )
            )
        },
        bottomBar = {
            BottomNavigation(
                header = {
                    ExpandableHeader(
                        totalProfitAndLoss = "-₹697.06 (2.44%)",
                        rows = listOf(
                            ExpandableRowConfig(
                                AppString.current_value,
                                "₹27,893.65",
                                valueTextStyle = MaterialTheme.typography.captionBold
                            ),
                            ExpandableRowConfig(
                                AppString.total_investment,
                                "₹28,590.71",
                                valueTextStyle = MaterialTheme.typography.captionBold
                            ),
                            ExpandableRowConfig(
                                AppString.today_profit_and_loss,
                                "-₹235.65",
                                valueTextStyle = MaterialTheme.typography.captionBold
                            ),
                        )
                    )
                }
            )
        }
    ) { innerPadding ->
        HomeScreenContent(
            uiState = HomeScreenUiState(
                userHoldingData = listOf(
                    UserHoldingsData(
                        symbol = "MAHABANK",
                        quantity = 2,
                        ltp = 23.99,
                        avgPrice = 12.44,
                        close = 13.44,
                        totalProfitAndLoss = "78.99"
                    ),
                    UserHoldingsData(
                        symbol = "RELIANCE",
                        quantity = 2,
                        ltp = 23.99,
                        avgPrice = 12.44,
                        close = 13.44,
                        totalProfitAndLoss = "78.99"
                    ),
                    UserHoldingsData(
                        symbol = "RELIANCE",
                        quantity = 2,
                        ltp = 2222.99,
                        avgPrice = 12.44,
                        close = 13.44,
                        totalProfitAndLoss = "78098765434567890.994567890"
                    ),
                )
            ),
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    Item("ASHOKLEY", "3", "₹119.10", "₹12.90")
}