package com.arnrmn.githubapp.utils.network

import com.apollographql.apollo.response.CustomTypeAdapter
import com.apollographql.apollo.response.CustomTypeValue
import java.net.URI

object UriAdapter : CustomTypeAdapter<URI> {
    override fun encode(value: URI): CustomTypeValue<*> {
        return CustomTypeValue.GraphQLString(value.toString())
    }

    override fun decode(value: CustomTypeValue<*>): URI {
        return URI.create(value.value.toString())
    }
}