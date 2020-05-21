package fr.iutbourg.sweetroutinemaker.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.widget.ImageView
import java.io.ByteArrayOutputStream


fun String.toImageView(imageView: ImageView) {
    val decodeBase64 = Base64.decode(this, Base64.DEFAULT)
    val decodeByte = BitmapFactory.decodeByteArray(decodeBase64, 0,decodeBase64.size)
    imageView.setImageBitmap(decodeByte)
}

fun ImageView.convertToBase64ForJPEG(): String{
    val bitmap = (this.drawable as BitmapDrawable).bitmap
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
}

fun ImageView.convertToBase64ForPNG(): String{
    val bitmap = (this.drawable as BitmapDrawable).bitmap
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
}

fun ImageView.convertToBase64ForWEBP(): String{
    val bitmap = (this.drawable as BitmapDrawable).bitmap
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.WEBP, 100, baos)
    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
}

fun Bitmap.toBase64(): String {
    val baos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 100, baos)
    return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
}