package io.github.zwliew.zwliew.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import io.github.zwliew.zwliew.R

fun viewUri(view: View, context: Context, uriString: String) {
    val uri = Uri.parse(uriString)
    var success = true
    try {
        // The custom tabs library requires API 15+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            viewUriInCustomTabs(context, uri)
        } else {
            success = viewUriNormally(context, uri)
        }
    } catch (e: ActivityNotFoundException) {
        success = viewUriNormally(context, uri)
    } finally {
        if (!success) {
            Snackbar
                .make(view, "Unable to launch URL", Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry_title) {
                    viewUri(view, context, uriString)
                }.show()
        }
    }
}

fun viewUriNormally(context: Context, uri: Uri): Boolean {
    val intent = Intent(Intent.ACTION_VIEW, uri)
    if (intent.resolveActivity(context.packageManager) == null) return false
    context.startActivity(intent)
    return true
}

fun viewUriInCustomTabs(context: Context, uri: Uri) {
    val intent = CustomTabsIntent.Builder()
        .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        .build()
    intent.launchUrl(context, uri)
}