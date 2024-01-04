package ua.kiev.prog;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactJson {

    public static void saveContactToFile(List<Contact> contactList, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(Contact.class, new ContactSerializer())
                    .create();

            writer.write(gson.toJson(contactList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Contact> getContactsFileJSON(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Gson gson = new GsonBuilder().registerTypeAdapter(Contact.class, new ContactDeserializer()).create();
            Type listType = new TypeToken<List<Contact>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Contact> contactList = getContactsFileJSON("src/main/webapp/WEB-INF/static/contacts.json");
        for(Contact contact : contactList){
            System.out.println(contact);
        }
    }
}
