package com.hyden.util


import android.util.Log
import com.hjiee.appproject.BuildConfig

object LogUtil {
    private const val TAG = "hjiee"
    
    fun LogV(msg : String) {
        if(BuildConfig.DEBUG) {
            Log.v(TAG,msg)
        }
    }

    fun LogD(msg : String) {
        if(BuildConfig.DEBUG) {
            Log.d(TAG,msg)
        }
    }

    fun LogI(msg : String) {
        if(BuildConfig.DEBUG) {
            Log.i(TAG,msg)
        }
    }
    
    fun LogW(msg : String) {
        if(BuildConfig.DEBUG) {
            Log.w(TAG,msg)
        }
    }
    
    fun LogE(msg : String) {
        if(BuildConfig.DEBUG) {
            Log.e(TAG,msg)
        }
    }
}


