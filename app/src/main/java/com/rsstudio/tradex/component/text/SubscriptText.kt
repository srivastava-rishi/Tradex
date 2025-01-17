import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import com.rsstudio.tradex.presentation.theme.captionDefault
import com.rsstudio.tradex.presentation.theme.charcoalGray


@Composable
fun SuperscriptText(
    baseText: String,
    subscriptText: String,
    spanStyle: SpanStyle = SpanStyle(),
    textStyle: TextStyle = MaterialTheme.typography.captionDefault.copy(
        color = charcoalGray,
        fontWeight = FontWeight.W500
    )
) {
    Text(
        text = buildAnnotatedString {
            append(baseText)
            withStyle(
                style = spanStyle.copy(
                    baselineShift = BaselineShift.Superscript
                )
            ) {
                append(subscriptText)
            }
        },
        style = textStyle
    )
}
