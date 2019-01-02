package io.github.zwliew.zwliew.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun launchUrl(view: View, context: Context, url: String) {
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Snackbar.make(
            view,
            "Unable to launch URL", Snackbar.LENGTH_SHORT
        ).show()
    }
}