import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.*;

// Аналог бази даних
public class DB {
    private static final String FILE_NAME = "DB.txt"; // Ім'я файлу де будуть зберігатись данні
    private static final String TYPE = "AnimalTypes"; // Тип тварини
    private static final String ANIMAL_NICKNAME = "animalNickname"; // Кличка тварини
    private static final String TIN_OF_THE_HOST = "TINOfTheHost"; // ІПН господаря
    private static final String FULL_NAME_OF_THE_HOST = "fullNameOfTheHost"; // ПІБ господаря
    private static final String DIAGNOSIS = "diagnosis"; // Діагноз
    private static final String WEIGHT = "weight"; // Вага
    private static final String TREATMENT = "treatment"; // Лікування
    private static final String LAST_VISIT_DATE = "lastVisitDate"; // Останній візит
    private static final String CONDITION_OF_BODY_PARTS = "map"; // Стан частин тіла
    private static final String VARIABLE_SEPARATOR = "\n"; // Роздільник між змінними
    private static final String SEPARATOR_BETWEEN_THE_VARIABLE_NAME_AND_IT_VALUE = ":"; // Роздільник між назвою змінної та її значенням
    private static final String SEPARATOR_BETWEEN_MAP_OBJECTS = "@"; // Роздільник між об'єктами карти
    private static final String CLASS_SEPARATOR = "}}"; // Роздільник між класами
    private static final String THE_SEPARATOR_BETWEEN_THE_KEY_AND_THE_VALUE_IN_THE_MAP = "="; // Роздільник між ключем та значенням в карті

    private final List<Animal> db = readFile(); // Якщо програма стартонула, то підтягне інформацію з файлу в цей масив

    public List<Animal> getDb() {
        return db;
    }

    // Пошук тварини по ІПН господаря
    public List<Animal> searchForAnimalsByTheOwnersPersonalIdentificationNumber(int[] TIN) {
        List<Animal> result = new ArrayList<>(); // Список на віддачу
        for (Animal animalTypes : db) { // Біг по всій базі даних
            int[] tinOfTheHost = animalTypes.getTINOfTheHost(); // Отримати ІПН господаря з позиції
            boolean checkResult = true; // Результат перевірки
            for (int i = 0; i < 10; i++) { // Біг по цифрах
                if (TIN[i] != tinOfTheHost[i]) { // Якщо цифри не збігатися
                    checkResult = false; // ІПН не схожі
                    break; // Завершити біг по цифрах
                }
            }
            if (checkResult) { // Якщо є відповідність
                result.add(animalTypes); // Додати в список на віддачу
            }
        }
        return result; // Віддати результат
    }

    // Оновити файл
    public void updateFile() {
        StringBuilder text = new StringBuilder();
        for (Animal animal : db) { // Біг по списку зареєстрованих тварин
            text.append(convertClassToText(animal)); // Перетворити клас в текст
        }
        File file = new File(FILE_NAME); // Екземпляр файлу
        try (FileWriter writer = new FileWriter(file)) { // Екземпляр записувача в тілі виключення
            writer.write(text.toString().toString()); // Виконати запис до файлу
            writer.flush(); // Закрити процес запису
        } catch (IOException e) { // Якщо під час запису виникла помилка
            System.out.println(e.getMessage()); // Друкувати повідомлення про помилку в консоль
        }
    }

    // Додати до файлу
    public void addToFile(Animal animal) {
        String text = convertClassToText(animal); // Перетворити клас в текст
        File file = new File(FILE_NAME); // Екземпляр файлу
        try (FileWriter writer = new FileWriter(file, true)) { // Екземпляр записувача в тілі виключення
            writer.write(text.toString()); // Виконати запис до файлу
            writer.flush(); // Закрити процес запису
        } catch (IOException e) { // Якщо під час запису виникла помилка
            System.out.println(e.getMessage()); // Друкувати повідомлення про помилку в консоль
        }
        db.add(animal); // Також зареєструвати в змінну, щоб не читати знову повністю файл
    }

    // Прочитати з файлу
    private List<Animal> readFile() {
        StringBuilder read = new StringBuilder(); // Приймач даних з файлу
        try (FileReader reader = new FileReader(FILE_NAME)) { // Екземпляр читача в середині виключення
            int c; // Змінна в яку буде записуватись значення прочитаного байту
            while ((c = reader.read()) != -1) { // Виконувати поки є що читати
                String s = String.valueOf((char) c); // Ціло-численний код трансформувати в символ
                    read.append(s); // Додати в приймач
            }
        } catch (IOException e) { // Якщо щось пішло не так в процесі читання, чи створенні цього процесу
            System.out.println(e.getMessage()); // Друкувати повідомлення помилки в консоль
        }
        List<Animal> result = new ArrayList<>(); // Для віддачі результату

        String[] objects = read.toString().strip().split(CLASS_SEPARATOR); // Поділити на класи
        for (String object : objects) { // Біг по класам
            object = object.replace("}", "").strip(); // Видалити вказаний символ, та очистити від невидимок з країв
            Animal animal = new Animal(getType(object)); // Об'єкт для збереження інформації про тварину
            String[] variables = object.split(VARIABLE_SEPARATOR); // Поділити на змінні
            for (String variable : variables) { // Біг по змінних
                String[] variableNameAndItValue = variable.strip().split(SEPARATOR_BETWEEN_THE_VARIABLE_NAME_AND_IT_VALUE); // Розділити на назву змінної та її значення
                variableNameAndItValue[0] = variableNameAndItValue[0].strip(); // Почистити назву змінної від невидимок з країв
                if (variableNameAndItValue.length > 1) {
                    variableNameAndItValue[1] = variableNameAndItValue[1].strip(); // Почистити значення для змінної від невидимок з країв
                }
                switch (variableNameAndItValue[0]) { // Яка назва змінної
                    case ANIMAL_NICKNAME -> { // Кличка тварини
                        animal.setAnimalNickname(variableNameAndItValue[1]);
                    }
                    case TIN_OF_THE_HOST -> { // ІПН господаря
                        int[] TIN = new int[10]; // Шаблон
                        char[] ints = variableNameAndItValue[1].toCharArray(); // Отримати масив чисел
                        for (int i = 0; i < 10; i++) { // Біг по цифрах
                            int numericValue = Character.getNumericValue(ints[i]);
                            TIN[i] = numericValue; // Записати число в потрібний масив
                        }
                        animal.setTINOfTheHost(TIN); // Встановити ІПН господаря
                    }
                    case FULL_NAME_OF_THE_HOST -> { // ПІБ господаря
                        animal.setFullNameOfTheHost(variableNameAndItValue[1]);
                    }
                    case DIAGNOSIS -> { // Діагноз
                        animal.setDiagnosis(variableNameAndItValue[1]);
                    }
                    case WEIGHT -> { // Вага
                        animal.setWeight(Double.parseDouble(variableNameAndItValue[1]));
                    }
                    case TREATMENT -> { // Лікування
                        animal.setTreatment(variableNameAndItValue[1]);
                    }
                    case LAST_VISIT_DATE -> { // Останній візит
                        if (variableNameAndItValue[1].equals("null")) { // Якщо пусто
                            animal.setLastVisitDate(null);
                        } else {
                            animal.setLastVisitDate(Date.valueOf(variableNameAndItValue[1]));
                        }
                    }
                    case CONDITION_OF_BODY_PARTS -> { // Стан частин тіла
                        if (variableNameAndItValue.length == 1) {
                            break;
                        }
                        String[] mapObjects = variableNameAndItValue[1].split(SEPARATOR_BETWEEN_MAP_OBJECTS); // Поділити на об'єкти карти
                        for (String mapObject : mapObjects) { // Біг по об'єктах карти
                            mapObject = mapObject.strip(); // Очистити об'єкт від невидимок з країв
                            String[] keyValue = mapObject.split(THE_SEPARATOR_BETWEEN_THE_KEY_AND_THE_VALUE_IN_THE_MAP); // Поділити на ключ та значення
                            animal.setDescriptionOfTheInitialExamination(keyValue[0].strip(), keyValue[1].strip()); // Зареєструвати в карту
                        }
                    }
                }
            }
            result.add(animal); // Додати клас в список
        }
        return result; // Віддати результат роботи прочитаної та трансформованої інформації
    }

    // Отримати тип тварини
    private AnimalTypes getType(String object) {
        String[] variables = object.split(VARIABLE_SEPARATOR); // Поділити на змінні
        for (String variable : variables) { // Біг по змінних
            variable = variable.strip(); // Очистити від невидимок з країв
            String[] variableNameAndItValue = variable.split(SEPARATOR_BETWEEN_THE_VARIABLE_NAME_AND_IT_VALUE); // Розділити на назву змінної та її значення
            variableNameAndItValue[0] = variableNameAndItValue[0].strip(); // Очистити від невидимок з країв
            variableNameAndItValue[1] = variableNameAndItValue[1].strip(); // Очистити від невидимок з країв
            if (Objects.equals(variableNameAndItValue[0], TYPE)) { // Якщо ця змінна відповідає за тип тварини
                return AnimalTypes.valueOf(variableNameAndItValue[1]);
            }
        }
        return null;
    }

    // Перетворити клас в текст
    private String convertClassToText(Animal animal) {
        StringBuilder text = new StringBuilder("}\n" + // Відкрити інформаційний запис
                TYPE + ":" + animal.getAnimalTypes().name() + "\n" + // Тип тварини
                ANIMAL_NICKNAME + ":" + animal.getAnimalNickname() + "\n" + // Кличка тварини
                TIN_OF_THE_HOST + ":" + animal.getTINOfTheHost()[0] + // (1) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[1] + // (2) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[2] + // (3) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[3] + // (4) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[4] + // (5) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[5] + // (6) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[6] + // (7) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[7] + // (8) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[8] + // (9) ІПН господаря (10 значне число)
                "" + animal.getTINOfTheHost()[9] + "\n" + // (10) ІПН господаря (10 значне число)
                FULL_NAME_OF_THE_HOST + ":" + animal.getFullNameOfTheHost() + "\n" + // ПІБ господаря
                DIAGNOSIS + ":" + animal.getDiagnosis() + "\n" + // Діагноз
                WEIGHT + ":" + animal.getWeight() + "\n" + // Вага
                TREATMENT + ":" + animal.getTreatment() + "\n" + // Лікування
                LAST_VISIT_DATE + ":" + animal.getLastVisitDate() + "\n" + // Останній візит
                CONDITION_OF_BODY_PARTS + ":"); // Карта стану частин тіла, відкрити перелік
        Set<String> strings = animal.getDescriptionOfTheInitialExamination().keySet(); // Отримали список ключів
        int size = strings.size(); // Кількість ключів
        int i = 1; // Лічильник кроків
        for (String string : strings) { // Біг по ключам
            text.append(string); // Ключ
            text.append(THE_SEPARATOR_BETWEEN_THE_KEY_AND_THE_VALUE_IN_THE_MAP); // Роздільник між ключ-значенням
            text.append(animal.getDescriptionOfTheInitialExamination().get(string)); // Значення
            if (i != size) { // Якщо це не остання частина тіла в цьому масиві
                text.append(SEPARATOR_BETWEEN_MAP_OBJECTS); // Роздільник між частинами тіла
            }
            i++; // Зарахувати крок
        }
        text.append("\n"); // Закрили перелік стану частин тіла
        text.append("}"); // Закрили інформаційний запис
        return text.toString();
    }
}
