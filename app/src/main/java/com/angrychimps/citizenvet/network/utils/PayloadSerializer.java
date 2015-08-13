package com.angrychimps.citizenvet.network.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class PayloadSerializer<T> implements JsonSerializer, JsonDeserializer<T> {

    @Override
    public JsonElement serialize(Object src, Type type, JsonSerializationContext context) {
        Log.i(null, "serializing "+new Gson().toJson(src));
        JsonObject payload = new JsonObject();
        payload.add("payload", new Gson().toJsonTree(src, type));

        Log.i(null, "serialized to "+payload.toString());
        return payload;
    }

    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Log.i(null, "deserializing "+je.toString());
        // Get the "payload" element from the parsed JSON
        JsonElement payload = je.getAsJsonObject().get("payload");
        Log.i(null, "deserialized to "+new Gson().fromJson(payload, type));
        // Deserialize it. You use a new instance of Gson to avoid infinite recursion to this deserializer
        return new Gson().fromJson(payload, type);
    }
}