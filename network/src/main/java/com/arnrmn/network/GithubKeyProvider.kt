package com.arnrmn.network

class GithubKeyProvider(decoder: KeyDecoder = KeyDecoder) {

    val key = decoder.decode(KEY)

    companion object {
        private const val KEY = "ZDdjOTgwNDBmMGM3ODc4MDVkYzY4OWQ1OGUwNGUyY2I3MjYxYzFjZQ=="
    }

}