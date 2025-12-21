package ec.edu.espe.AlertSystem.controller;

/**
 *
 * @author Paulo Ramos
 */
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;

public class Json<T> {

    private final String fileName;
    private final Type type;
    private final Gson gson = new Gson();

    public Json(String fileName, Type type) {
        this.fileName = fileName;
        this.type = type;
    }

    public T loadData() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                return getEmptyInstance();
            }

            InputStreamReader reader = new InputStreamReader(is);
            T data = gson.fromJson(reader, type);
            return data != null ? data : getEmptyInstance();

        } catch (IOException e) {
            return getEmptyInstance();
        }
    }

    public void saveData(T data) {
        try (FileWriter writer = new FileWriter("src/main/resources/" + fileName)) {
            gson.toJson(data, type, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private T getEmptyInstance() {
        if (type instanceof Class) {
            return null;
        } else {
            return gson.fromJson("[]", type);
        }
    }
}
