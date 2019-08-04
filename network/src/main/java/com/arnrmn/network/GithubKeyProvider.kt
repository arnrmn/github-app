package com.arnrmn.network

//This is just a dummy implementation. Real world case should not be implemented like this.
class GithubKeyProvider(decoder: KeyDecoder = KeyDecoder) {
    val key = decoder.decode(KEY)

    companion object {
        private const val KEY = "ZDdjOTgwNDBmMGM3ODc4MDVkYzY4OWQ1OGUwNGUyY2I3MjYxYzFjZQ=="
    }

}