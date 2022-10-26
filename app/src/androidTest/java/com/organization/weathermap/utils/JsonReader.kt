package com.organization.weathermap.data.utils

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.organization.weathermap.utils.Logger
import java.io.IOException
import java.io.InputStream

object JsonReader {
  fun getJson(path: String): String {
    return try {
      val context = ApplicationProvider.getApplicationContext<Context>()
      val jsonStream: InputStream = context.assets.open("networkresponses/$path")
      String(jsonStream.readBytes())
    } catch (exception: IOException) {
      Logger.e(exception, "Error reading network response json asset")
      throw exception
    }
  }
}