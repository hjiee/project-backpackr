package com.hjiee.appproject.ext

fun Int.isSuccess() : Boolean =
    when(this) {
        200 -> true
        else -> false
    }
