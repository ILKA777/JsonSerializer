package ru.ilka.app.Utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ilka.app.Model.Person;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class JsonSerializer {

    public static void convertToJson(Person person) throws JsonProcessingException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Указываем, какие поля следует включить в JSON.
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("first_name", "firstName");
        inputMap.put("last_name", "lastName");
        inputMap.put("age", "age");

        // Сериализуем объект в JSON с помощью Jackson.
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> serialized = new HashMap<>();
        for (Map.Entry<String, String> entry : inputMap.entrySet()) {
            String objectFieldName = entry.getValue();
            JsonProperty jsonPropertyAnnotation = Person.class.getDeclaredField(objectFieldName)
                    .getAnnotation(JsonProperty.class);
            String objectPropertyDisplayName = jsonPropertyAnnotation != null ? jsonPropertyAnnotation.value() : objectFieldName;
            Object value = Person.class.getDeclaredMethod("get" + objectFieldName.substring(0, 1).toUpperCase() + objectFieldName.substring(1)).invoke(person);
            serialized.put(objectPropertyDisplayName, value);
        }

        String json = mapper.writeValueAsString(serialized);
        System.out.println(json);
    }
}