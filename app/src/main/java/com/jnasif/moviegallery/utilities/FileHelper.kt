package com.jnasif.moviegallery.utilities

import android.app.Application
import android.content.Context
import java.io.File

class FileHelper {
    companion object{
        fun getTextFromResources(context : Context, resourceId : Int) : String{
            return context.resources.openRawResource(resourceId).use { it.bufferedReader().use { it.readText() } }
        }

        fun getTextFromAssets(context : Context, fileName : String) : String{
            return context.assets.open(fileName).use { it.bufferedReader().use { it.readText() } }
        }

        fun saveTextToFile(app: Application, json: String?) {
            val file = File(app.filesDir, "movieDetails.json")
            file.writeText(json ?: "", Charsets.UTF_8)
        }

        fun saveTextToCache(app: Application, json: String?) {
            val file = File(app.cacheDir, "movieDetails.json")
            file.writeText(json ?: "", Charsets.UTF_8)
        }

    }
}