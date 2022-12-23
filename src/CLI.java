import java.util.*;

// Інтерфейс командного рядка
public class CLI {
    private final Scanner scanner = new Scanner(System.in); // Сканер
    DB db = new DB();

    // Головний метод класу
    public void run() {
        while(true) { // Нескінченний цикл
            // Інформація про доступні команди
            String commandsInfo =
                    """
                    Введіть номер команди:
                    1 - Додати нову тварину
                    2 - Знайти тварин по ІПН господаря
                    3 - Вихід
                    """;
            System.out.println(commandsInfo); // Друкувати список доступних команд
            int command = getInt(); // Отримати команду від користувача
            switch (command) {
                case 1 -> { // Команда на реєстрацію нової тварини
                    Animal animal = new Animal(getAnimalTypes()); // Отримати тип тварини та створити екземпляр тварини
                    System.out.println("Введіть кличку тварини:"); // Повідомити про те, якого вводу програма очікує
                    animal.setAnimalNickname(getString()); // Кличка тварини
                    System.out.println("Введіть ІПН господаря:"); // Повідомити про те, якого вводу програма очікує
                    animal.setTINOfTheHost(getTIN()); // ІПН господаря
                    System.out.println("Введіть ПІБ господаря:"); // Повідомити про те, якого вводу програма очікує
                    animal.setFullNameOfTheHost(getString()); // ПІБ господаря
                    System.out.println("Введіть діагноз:"); // Повідомити про те, якого вводу програма очікує
                    animal.setDiagnosis(getString()); // Діагноз
                    System.out.println("Введіть вагу:"); // Повідомити про те, якого вводу програма очікує
                    animal.setWeight(getDouble()); // Вага
                    System.out.println("Введіть лікування:"); // Повідомити про те, якого вводу програма очікує
                    animal.setTreatment(getString()); // Лікування
                    getInformationAboutTheInitialReview(animal); // Опис первинного огляду, частин тіла тварини
                    db.addToFile(animal); // Додати нову тварину до бази даних
                    System.out.println("Нову тварину додано успішно!"); // Повідомити про успішну операцію користувача
                }
                case 2 -> { // Знайти тварин по ІПН господаря
                    System.out.println("Введіть ІПН господаря:"); // Повідомити про те, якого вводу програма очікує
                    List<Animal> animalTypes = db.searchForAnimalsByTheOwnersPersonalIdentificationNumber(getTIN()); // ІПН господаря
                    for (Animal animalType : animalTypes) { // Біг по знайденому
                        System.out.println(animalType); // Друкувати в консоль
                    }
                }
                case 3 -> { // Команда на вихід
                    scanner.close(); // Закрити сканер
                    return; // Вийти з циклу та методу
                }
                default -> System.out.println("Така команда не зареєстрована");
            }
        }
    }

    // Отримати інформацію з консолі про первинний огляд
    private void getInformationAboutTheInitialReview(Animal animal) {
        for (String key : animal.getAnimalTypes().getPartsOfTheBody()) { // Біг по ключам
            System.out.println("Введіть первинний стан для такої частини тіла як: " + key); // Повідомити про те, якого вводу програма очікує
            animal.setDescriptionOfTheInitialExamination(key, getString()); // Отримати та встановити значення по ключу
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
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    return line; // Отримати текст
                } else {
                    System.out.println("Значення не може бути пустим!");
                }
            } catch (Exception ex) {
                System.out.println("Ви унікальна людина, змогли зламати читання текстового рядка!");
            }
        }
    }

    // Отримати тип тварини
    private AnimalTypes getAnimalTypes() {
        // Друкувати список доступних типів тварин
        List<String> names = new ArrayList<>(); // Список назв типів
        for (AnimalTypes value : AnimalTypes.values()) { // Біг по всім enums
            names.add(value.getName()); // Отримати ім'я екземпляра
        }
        System.out.println("Оберіть тип тварини зі списку: " + names);
        while (true) { // Нескінченний цикл
            try {
                String name = scanner.nextLine(); // Отримати ввід від користувача
                for (AnimalTypes value : AnimalTypes.values()) { // Біг по всім enums
                    if (value.getName().equals(name)) { // Якщо є такий
                        return value;
                    }
                }
                System.out.println("Спробуйте ще раз, вірно ввести назву типу тварини!");
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
