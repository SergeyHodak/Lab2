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
                    intermediateMenu(animalTypes); // Запустити проміжне меню
                }
                case 3 -> { // Команда на вихід
                    scanner.close(); // Закрити сканер
                    return; // Вийти з циклу та методу
                }
                default -> System.out.println("Така команда не зареєстрована");
            }
        }
    }

    // Проміжне меню
    private void intermediateMenu(List<Animal> animals) {
        while(true) { // Нескінченний цикл
            // Текст про команди проміжного меню
            String commandsInfo =
                    """
                    Введіть номер команди:
                    1 - Перейти в меню прийому
                    2 - Скасовувати дію
                    """;
            System.out.println(commandsInfo); // Друкувати список доступних команд
            int command = getInt(); // Отримати команду від користувача
            switch (command) {
                case 1 -> { // Перейти в меню прийому
                    System.out.println("Введіть кличку тварини, для початку прийому:"); // Повідомити про те, якого вводу програма очікує
                    String nickName = getString(); // Отримати ввід
                    Animal result = null; // Тварина для прийому
                    for (Animal animal : animals) {
                        if (animal.getAnimalNickname().equals(nickName)) { // Якщо все ок, і зі списку обрано потрібну тварину
                            result = animal; // Ініціалізувати
                        }
                    }
                    if (result != null) {
                        receptionMenu(result); // Перейти в меню прийому
                    } else {
                        System.out.println("Ви ввели не вірно кличку, активуйте команду знову");
                    }
                }
                case 2 -> { // Скасовувати дію
                    return;
                }
                default -> System.out.println("Така команда не зареєстрована");
            }
        }
    }

    // Меню прийому
    private void receptionMenu(Animal animal) {
        while(true) { // Нескінченний цикл
            // Текст про команди меню прийому
            String commandsInfo =
                    """
                    Введіть номер команди:
                    1 - Змінити діагноз
                    2 - Змінити вагу
                    3 - Змінити опис лікування
                    4 - Змінити стан частин тіла
                    5 - Завершити прийом
                    """;
            System.out.println(commandsInfo); // Друкувати список доступних команд
            int command = getInt(); // Отримати команду від користувача
            switch (command) {
                case 1 -> { // Змінити діагноз
                    System.out.println("Введіть оновлення для поля діагноз:"); // Повідомити про те, якого вводу програма очікує
                    animal.setDiagnosis(getString());
                }
                case 2 -> { // Змінити вагу
                    System.out.println("Введіть оновлення для поля вага:"); // Повідомити про те, якого вводу програма очікує
                    animal.setWeight(getDouble());
                }
                case 3 -> { // Змінити опис лікування
                    System.out.println("Введіть оновлення для поля лікування:"); // Повідомити про те, якого вводу програма очікує
                    animal.setTreatment(getString());
                }
                case 4 -> { // Змінити стан частин тіла
                    for (Map.Entry<String, String> stringStringEntry : animal.getDescriptionOfTheInitialExamination().entrySet()) {
                        // Повідомити про те, якого вводу програма очікує
                        System.out.println("Попередній вміст: \"" + stringStringEntry.getValue() +
                                "\". Введіть оновлення для " + stringStringEntry.getKey() + ":");
                        animal.setDescriptionOfTheInitialExamination(stringStringEntry.getKey(), getString());
                    }
                }
                case 5 -> { // Завершити прийом
                    animal.setLastVisitDate(new Date()); // Встановити поточну дату
                    StringBuilder TIN = new StringBuilder(); // ІПН господаря
                    for (int i : animal.getTINOfTheHost()) { // Біг по цифрах
                        TIN.append(i); // Додати в рядок
                    }
                    System.out.println("ІПН:" + TIN);
                    System.out.println("Назва клініки:" + "Та що придбала цей проект");
                    System.out.println("Сума до оплати:" + animal.getAPrice());
                    System.out.println("Пророцтво на сьогодні:" + new Prophecy().getRandomProphecy());
                    db.updateFile(); // Оновити файл бази даних
                    return;
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
