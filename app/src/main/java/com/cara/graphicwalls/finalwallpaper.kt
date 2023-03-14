package com.cara.graphicwalls

import android.app.AlertDialog
import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.cara.graphicwalls.databinding.ActivityFinalwallpaperBinding
import com.cara.graphicwalls.databinding.FragmentCatoBinding
import kotlinx.coroutines.*
import java.io.File
import java.io.OutputStream
import java.lang.Exception
import java.net.URL
import java.util.*

class finalwallpaper : AppCompatActivity() {
    lateinit var binding: ActivityFinalwallpaperBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFinalwallpaperBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        //status
        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.status)

        setContentView(binding.root)

        // get image URL from intent
        val link = intent.getStringExtra("url")
        // create valid URL object from link

       val urlImage = URL(link)

        // load image into ImageView using Glide
        Glide.with(this).load(link).into(binding.finalwall)

        binding.btnSetWallpaper.setOnClickListener {
            val options = arrayOf<CharSequence>("Home Screen", "Lock Screen", "Both")

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Set wallpaper for:")
            builder.setItems(options) { dialog, item ->
                val result: Deferred<Bitmap?> = GlobalScope.async {
                    urlImage.toBitmap()
                }
                GlobalScope.launch(Dispatchers.Main) {
                    val wallpaperManager = WallpaperManager.getInstance(applicationContext)
                    when (item) {
                        0 -> wallpaperManager.setBitmap(result.await(), null, true, WallpaperManager.FLAG_SYSTEM)
                        1 -> wallpaperManager.setBitmap(result.await(), null, true, WallpaperManager.FLAG_LOCK)
                        2 -> wallpaperManager.setBitmap(result.await())
                    }
                    Toast.makeText(applicationContext, "Applied", Toast.LENGTH_SHORT).show()
                }
            }
            builder.show()
        }

        binding.btnDownload.setOnClickListener {
            //download wallpaper

            val result: Deferred<Bitmap?> = GlobalScope.async {
                urlImage.toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {
                saveImage(result.await())
            }
        }
    }

    // extension function to convert URL to Bitmap
    fun URL.toBitmap(): Bitmap? {
        return try {
            BitmapFactory.decodeStream(openStream())
        } catch (e: WindowManager.InvalidDisplayException) {
            null
        }
    }

    // function to save image to storage
    private fun saveImage(image: Bitmap?) {
        val random1 = Random().nextInt(520985)
        val random2 = Random().nextInt(520985)

        val name = "AMOLED-${random1 + random2}"

        val data: OutputStream
        try {
            val resolver = contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$name.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + File.separator + "Amoled Wallpaper"
            )
            val imageUri =
                resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            data = resolver.openOutputStream(Objects.requireNonNull(imageUri)!!)!!
            image?.compress(Bitmap.CompressFormat.JPEG, 100, data)
            Objects.requireNonNull<OutputStream?>(data)
            Toast.makeText(this, "Image Save on Your Storage", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Image Not Save", Toast.LENGTH_SHORT).show()
        }
    }
}
