package com.sd.moviedb.callback

interface EmailVerificationCallback {

    fun resend()
    fun cancel()
}