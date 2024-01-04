package ua.kiev.prog;

import com.google.gson.*;
import java.lang.reflect.Type;

public class ContactDeserializer implements JsonDeserializer<Contact> {

    @Override
    public Contact deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Long id = jsonObject.getAsJsonPrimitive("id").getAsLong();
        String name = jsonObject.getAsJsonPrimitive("name").getAsString();
        String surname = jsonObject.getAsJsonPrimitive("surname").getAsString();
        String phone = jsonObject.getAsJsonPrimitive("phone").getAsString();
        String email = jsonObject.getAsJsonPrimitive("email").getAsString();


        return new Contact(null, name, surname, phone, email);
    }
}

