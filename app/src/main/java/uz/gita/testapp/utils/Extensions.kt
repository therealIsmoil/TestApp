package uz.gita.testapp.utils

fun String.changeSize(): String {

    var left = 2
    var found = false
    while (left < length) {
        if (found && this[left] == '/')
            break
        if (this[left - 1] == 'd' && this[left - 2] == 'i' && this[left] == '/')
            found = true
        left++
    }
    left++

    return this.substring(0, left) + "100/100"
}