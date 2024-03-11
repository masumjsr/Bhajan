package com.vajan.vajan.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.pierfrancescosoffritti.androidyoutubeplayer.BuildConfig


internal fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Activity not found. Unknown error.")
}

fun Context.shareApk(){
    try {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
        var shareMessage = "\nLet me recommend you this application\n\n"
        shareMessage =
            (shareMessage + "https://play.google.com/store/apps/details?id=" + packageName).toString() + "\n\n"
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        startActivity(Intent.createChooser(shareIntent, "choose one"))
    } catch (e: Exception) {
        //e.toString();
    }


}

fun Context.openUrl(url: String?) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, url?.toUri())
        startActivity(intent)
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}
