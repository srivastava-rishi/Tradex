package com.rsstudio.tradex.component.bottomnav

import SuperscriptText
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rsstudio.tradex.alias.AppDrawable
import com.rsstudio.tradex.alias.AppString
import com.rsstudio.tradex.presentation.theme.captionBold
import com.rsstudio.tradex.presentation.theme.captionDefault
import com.rsstudio.tradex.presentation.theme.charcoalGray
import com.rsstudio.tradex.presentation.theme.crimsonRed
import com.rsstudio.tradex.presentation.theme.offWhite
import com.rsstudio.tradex.presentation.theme.tealGreen
import com.rsstudio.tradex.util.isPositiveAmount

@Composable
fun ExpandableHeader(
    modifier: Modifier = Modifier,
    rows: List<ExpandableRowConfig> = emptyList(),
    totalProfitAndLoss: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = offWhite,
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
            )
            .padding(12.dp)
            .animateContentSize()
    ) {
        if (isExpanded) {
            rows.forEach { row ->
                ExpandableRow(
                    title = row.title,
                    value = row.value,
                    valueTextStyle = row.valueTextStyle
                )
                Spacer(modifier = Modifier.size(24.dp))
            }
        }
        ExpandableRow(
            title = AppString.profit_loss,
            value = totalProfitAndLoss,
            isExpanded = isExpanded,
            onToggleExpand = { isExpanded = !isExpanded },
            valueTextStyle = MaterialTheme.typography.captionBold.copy(color = if (totalProfitAndLoss.isPositiveAmount()) tealGreen else crimsonRed)
        )
    }
}

@Composable
fun ExpandableRow(
    modifier: Modifier = Modifier,
    title: Int,
    value: String,
    valueTextStyle: TextStyle = MaterialTheme.typography.captionBold,
    isExpanded: Boolean = false,
    onToggleExpand: (() -> Unit)? = null,
    showToggleIcon: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            SuperscriptText(
                baseText = stringResource(title),
                subscriptText = stringResource(id = AppString.symbol_star),
                spanStyle = MaterialTheme.typography.captionDefault.copy(
                    color = charcoalGray,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp
                ).toSpanStyle(),
                textStyle = MaterialTheme.typography.captionDefault.copy(
                    color = charcoalGray,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.size(4.dp))
            if (showToggleIcon && onToggleExpand != null) {
                Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .clickable { onToggleExpand() }
                        .rotate(if (isExpanded) 0f else 180f),
                    tint = charcoalGray,
                    painter = painterResource(id = AppDrawable.ic_chevron_down),
                    contentDescription = "chevronDown"
                )
            }
        }
        Text(
            text = value,
            style = valueTextStyle
        )
    }
}


data class ExpandableRowConfig(
    val title: Int,
    val value: String,
    val valueTextStyle: TextStyle
)

@Preview
@Composable
fun ExpandableHeaderPreview() {
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