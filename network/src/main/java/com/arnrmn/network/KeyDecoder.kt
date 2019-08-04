package com.arnrmn.network

import android.util.Base64
import java.nio.charset.StandardCharsets

object KeyDecoder {
    fun decode(key: String) = String(Base64.decode(key, Base64.DEFAULT), StandardCharsets.UTF_8)
}