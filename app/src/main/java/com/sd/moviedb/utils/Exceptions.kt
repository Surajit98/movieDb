package com.sd.moviedb.utils

import java.io.IOException

class EndOfList() : Exception("You have reached the end")

class NoInternetConnection() : IOException("No Internet connectivity")