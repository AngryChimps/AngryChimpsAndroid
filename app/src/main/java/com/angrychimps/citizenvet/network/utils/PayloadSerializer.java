package com.angrychimps.citizenvet.network.utils;

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
        JsonObject payload = new JsonObject();
        payload.add("payload", new Gson().toJsonTree(src, type));

        return payload;
    }

    @Override
    public T deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        // Get the "payload" element from the parsed JSON
        JsonElement payload = je.getAsJsonObject().get("payload");

        // Deserialize it. You use a new instance of Gson to avoid infinite recursion to this deserializer
        return new Gson().fromJson(payload, type);
    }
}