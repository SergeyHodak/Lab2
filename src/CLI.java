import java.util.*;

// Інтерфейс командного рядка
public class CLI {
    private final List<AnimalTypes> list = new ArrayList<>(); // Було б не погано, тут використовувати базу даних
    private final Scanner scanner = new Scanner(System.in); // Сканер

    public List<AnimalTypes> getList() {
        return list;
    }

    // Головний метод класу
    public void run() {
        while(true) { // Нескінченний цикл
            // Інформація про доступні команди
            String commandsInfo =
                    """
                    Введіть номер команди:
                    1 - Додати нову тварину
                    2 - Вихід
                    """;
            System.out.println(commandsInfo); // Друкувати список доступних команд
            int command = getInt(); // Отримати команду від користувача
            switch (command) {
                case 1 -> { // Команда на реєстрацію нової тварини
                    AnimalTypes animalTypes = getAnimalTypes(); // Отримати тип тварини
                    System.out.println("Введіть кличку тварини:"); // Повідомити про те, якого вводу програма очікує
                    animalTypes.setAnimalNickname(getString()); // Кличка тварини
                    System.out.println("Введіть ІПН господаря:"); // Повідомити про те, якого вводу програма очікує
                    animalTypes.setTINOfTheHost(getTIN()); // ІПН господаря
                    System.out.println("Введіть ПІБ господаря:"); // Повідомити про те, якого вводу програма очікує
                    animalTypes.setFullNameOfTheHost(getString()); // ПІБ господаря
                    System.out.println("Введіть діагноз:"); // Повідомити про те, якого вводу програма очікує
                    animalTypes.setDiagnosis(getString()); // Діагноз
                    System.out.println("Введіть вагу:"); // Повідомити про те, якого вводу програма очікує
                    animalTypes.setWeight(getDouble()); // Вага
                    System.out.println("Введіть лікування:"); // Повідомити про те, якого вводу програма очікує
                    animalTypes.setTreatment(getString()); // Лікування
                    getInformationAboutTheInitialReview(animalTypes); // Опис первинного огляду, частин тіла тварини
                    list.add(animalTypes); // Додати нову тварину до бази даних
                    System.out.println("Нову тварину додано успішно!"); // Повідомити про успішну операцію користувача
                }
                case 2 -> { // Команда на вихід
                    scanner.close(); // Закрити сканер
                    return; // Вийти з циклу та методу
                }
                default -> System.out.println("Така команда не зареєстрована");
            }
        }
    }

    // Отримати інформацію про первинний огляд
    private void getInformationAboutTheInitialReview(AnimalTypes animalType) {
        HashMap<String, String> map = animalType.getDescriptionOfTheInitialExamination().getMap(); // Отримати карту частин тіла
        for (String key : map.keySet()) { // Біг по ключам
            System.out.println("Введіть первинний стан для такої частини тіла як: " + key); // Повідомити про те, якого вводу програма очікує
            animalType.setDescriptionOfTheInitialExamination(key, getString()); // Отримати та встановити значення по ключу
        }
    }

    // Отримати не ціле число
    private double getDouble() {
        while (true) { // Нескінченний цикл
            try {
                return Double.parseDouble(scanner.nextLine()); // Отримати число
            } catch (Exception ex) {
                System.out.println("Спробуйте ще раз, вірно ввести не ціле число, роздільник крапка!");
            }
        }
    }

    // Отримати 10 значне число ІПН
    private int[] getTIN() {
        while (true) { // Нескінченний цикл
            try {
                char[] chars = scanner.nextLine().toCharArray();// Отримати ввід від користувача
                int[] result = new int[10]; // Результат
                if (chars.length == 10) { // Якщо введене має 10 символів
                    for (int i = 0; i < chars.length; i++) { // Біг по введеному
                        result[i] = Character.getNumericValue(chars[i]); // Дублювати число
                    }
                    return result; // Отримати число
                } else {
                    System.out.println("Спробуйте ще раз, вірно ввести десять цифр!");
                }
            } catch (Exception ex) {
                System.out.println("Спробуйте ще раз, вірно ввести десять цифр!");
            }
        }
    }

    // Отримати рядок
    private String getString() {
        while (true) { // Нескінченний цикл
            try {
                return scanner.nextLine(); // Отримати текст
            } catch (Exception ex) {
                System.out.println("Ви унікальна людина, змогли зламати читання текстового рядка!");
            }
        }
    }

    // Отримати тип тварини
    private AnimalTypes getAnimalTypes() {
        // Друкувати список доступних типів тварин
        System.out.println("Оберіть тип тварини зі списку: " + Arrays.toString(AnimalTypes.values()));
        while (true) { // Нескінченний цикл
            try {
                return AnimalTypes.valueOf(scanner.nextLine()); // Отримати слово, яке можна привести до типу тварини
            } catch (Exception ex) {
                System.out.println("Спробуйте ще раз, вірно ввести назву типу тварини!");
            }
        }
    }

    // Отримати ціле число
    private int getInt() {
        while (true) { // Нескінченний цикл
            try {
                return Integer.parseInt(scanner.nextLine()); // Отримати ціле число від користувача
            } catch (Exception ex) {
                System.out.println("Спробуйте ще раз, вірно ввести ціле число!");
            }
        }
    }
}
