package com.sr.techhelper.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.InputStream

object ImageUtils {

    fun decodeBase64ToImage(base64String: String?): Bitmap? {
        if (base64String.isNullOrEmpty()) {
            Log.w("ImageUtils", "Base64 string is null or empty")
            return null
        }
        try {
            val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
            if (decodedBytes.isEmpty()) {
                Log.e("ImageUtils", "Decoded bytes array is empty")
                return null
            }
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        } catch (e: IllegalArgumentException) {
            Log.e("ImageUtils", "Invalid Base64 string: ${e.message}")
            return null
        } catch (e: Exception) {
            Log.e("ImageUtils", "Error decoding Base64: ${e.message}")
            return null
        }
    }


    private fun internalConvertImageToBase64(inputStream: InputStream?): String {
        if (inputStream == null) {
            return ""
        }
        val originalBitmap = BitmapFactory.decodeStream(inputStream)

        // Reduce the dimensions of the image
        val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 480, 480, true) // Adjust width/height

        // Compress the Bitmap
        val compressedStream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 70, compressedStream) // Lower quality to 30%
        val compressedByteArray = compressedStream.toByteArray()

        // Convert to Base64
        return Base64.encodeToString(compressedByteArray, Base64.DEFAULT)
    }

    fun convertImageToBase64(uri: Uri, context: Context): String {
        val inputStream = context.contentResolver.openInputStream(uri)
        return internalConvertImageToBase64(inputStream)
    }

    fun convertPhotoUrlToBase64(photoUrl: String): String {
        return try {
            // Load the image from the URL
            val inputStream = java.net.URL(photoUrl).openStream()
            internalConvertImageToBase64(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            "" // Return empty string in case of error
        }
    }
    fun convertDrawableToBase64(context: Context, drawableResId: Int): String {
        val bitmap = BitmapFactory.decodeResource(context.resources, drawableResId)
        return convertBitmapToBase64(bitmap)
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }
}