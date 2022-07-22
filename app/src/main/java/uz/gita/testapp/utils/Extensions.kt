package uz.gita.testapp.utils

fun String.changeSize(): String {
    var text = StringBuilder(this)

    text = text.replace(text.lastIndexOf('/') - 4, text.length, "200/300")

    return text.toString()
}