import java.util.List;

// Пророцтво
public class Prophecy {
    private static final String p1 = "Сміливо ви беріть вила – гребіть бабки що є сили!";
    private static final String p2 = "Вас чекає безліч цікавих і захоплюючих … побутових справ.";
    private static final String p3 = "На вас готується варварське напад. Щастя і удача вже поспішають до вас. Опиратися безглуздо.";
    private static final String p4 = "Зміни близько, забудь про смуток.";
    private static final String p5 = "Не сумуй і посміхнися!";

    private final List<String> list = List.of(p1, p2, p3, p4, p5); // Список пророцтв

    // Отримати випадкове пророцтво
    public String getRandomProphecy() {
        return list.get(randomIndex(list.size()));
    }

    // Отримати випадковий індекс в діапазоні
    private int randomIndex(int max) {
        return (int) (Math.random() * max); // Отримати випадкове число від 0 до max(не включно), перевести з double в int
    }
}
