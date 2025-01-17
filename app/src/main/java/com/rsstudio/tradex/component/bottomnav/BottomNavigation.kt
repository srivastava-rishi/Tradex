package com.rsstudio.tradex.component.bottomnav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rsstudio.tradex.alias.AppDrawable
import com.rsstudio.tradex.alias.AppString
import com.rsstudio.tradex.presentation.theme.ashGray
import com.rsstudio.tradex.presentation.theme.paleGray
import com.rsstudio.tradex.presentation.theme.royalBlue


@Composable
fun BottomNavigation(
    header: (@Composable () -> Unit)? = null,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.WatchList,
        BottomNavItem.Orders,
        BottomNavItem.PortFolio,
        BottomNavItem.Funds,
        BottomNavItem.Invest
    )
) {
    Column {
        header?.let {
            header()
        }
        Row(
            modifier = Modifier
                .background(paleGray)
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items.forEach { item ->
                Box(
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .height(IntrinsicSize.Min)
                ) {
                    BottomNavigationItem(
                        item = item,
                        isSelected = item.active,
                    )
                    if (item.active) {
                        Divider(
                            color = royalBlue,
                            thickness = 2.dp,
                            modifier = Modifier.wrapContentWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    modifier: Modifier = Modifier,
    item: BottomNavItem,
    isSelected: Boolean,
) {
    Column(
        modifier = modifier.height(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = stringResource(id = item.title),
            tint = if (isSelected) royalBlue else ashGray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = stringResource(id = item.title),
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (isSelected) royalBlue else ashGray
            )
        )
    }
}

sealed class BottomNavItem(var title: Int, val icon: Int, val active: Boolean) {
    data object WatchList : BottomNavItem(AppString.watchlist, AppDrawable.ic_format_list_bulleted, false)
    data object Orders : BottomNavItem(AppString.orders, AppDrawable.ic_history, false)
    data object PortFolio : BottomNavItem(AppString.portfolio, AppDrawable.ic_work, true)
    data object Funds : BottomNavItem(AppString.funds, AppDrawable.ic_currency_rupee, false)
    data object Invest : BottomNavItem(AppString.invest, AppDrawable.ic_money_bag, false)
}


@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview() {
    BottomNavigation(
        items = listOf(
            BottomNavItem.WatchList,
            BottomNavItem.Orders,
            BottomNavItem.PortFolio,
            BottomNavItem.Funds,
            BottomNavItem.Invest
        )
    )
}

