package com.example.retrofitrecyclerview.data

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter : TypeAdapter<LocalDate>() {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: LocalDate?) {
        if (value == null) {
            out.nullValue()
        } else {
            out.value(formatter.format(value))
        }
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): LocalDate? {
        if (`in`.peek() == com.google.gson.stream.JsonToken.NULL) {
            `in`.nextNull()
            return null
        }
        val dateStr = `in`.nextString()
        return LocalDate.parse(dateStr, formatter)
    }
}
