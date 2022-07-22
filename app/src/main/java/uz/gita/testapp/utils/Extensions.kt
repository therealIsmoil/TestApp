package uz.gita.testapp.utils

import android.util.Log

fun String.changeSize(): String {

    var left = 2
    var found = false
    while(left < length) {
        if(found && this[left] == '/')
            break
        if(this[left - 1] == 'd' && this[left - 2] == 'i' && this[left] == '/')
            found = true
        left++
    }
    left++

    Log.d("TTT", this.substring(0, left) + "200/200" )
    return this.substring(0, left) + "72/72"
}