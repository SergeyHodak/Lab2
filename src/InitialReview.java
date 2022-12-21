import java.util.HashMap;

// Первинний огляд
public class InitialReview {
    private HashMap<String, String> map = new HashMap<>(); // Карта {ключ (частина тіла), значення (в якому стані)}

    // Отримати карту
    public HashMap<String, String> getMap() {
        return map;
    }

    // Додати запис до карти
    public void addInMap(String key, String value) {
        map.put(key, value);
    }
}
