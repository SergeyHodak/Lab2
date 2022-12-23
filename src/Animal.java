import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Тварина
public class Animal {
    private final AnimalTypes animalTypes; // Тип тварини
    private String animalNickname; // Кличка тварини
    private int[] TINOfTheHost; // ІПН господаря (10 значне число)
    private String fullNameOfTheHost; // ПІБ господаря
    private String diagnosis; // Діагноз
    private double weight; // Вага
    private String treatment; // Лікування
    private Date lastVisitDate; // Дата останнього візиту
    private final HashMap<String, String> map = new HashMap<>(); // Карта що зберігає в собі інформацію про стан частин тіла

    // Конструктор що приймає на вході тип тварини
    public Animal(AnimalTypes type) {
        animalTypes = type;
    }

    // Отримати тип тварини
    public AnimalTypes getAnimalTypes() {
        return this.animalTypes;
    }

    // Встановити кличку
    public void setAnimalNickname(String nickname) {
        this.animalNickname = nickname;
    }

    // Отримати кличку
    public String getAnimalNickname() {
        return this.animalNickname;
    }

    // Встановити ІПН господаря
    public void setTINOfTheHost(int[] TIN) {
        this.TINOfTheHost = TIN;
    }

    // Отримати ІПН господаря
    public int[] getTINOfTheHost() {
        return this.TINOfTheHost;
    }

    // Встановити ПІБ господаря
    public void setFullNameOfTheHost(String fullName) {
        this.fullNameOfTheHost = fullName;
    }

    // Отримати ПІБ господаря
    public String getFullNameOfTheHost() {
        return this.fullNameOfTheHost;
    }

    // Встановити діагноз
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    // Отримати діагноз
    public String getDiagnosis() {
        return this.diagnosis;
    }

    // Встановити вагу
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Отримати вагу
    public double getWeight() {
        return this.weight;
    }

    // Встановити лікування
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    // Отримати лікування
    public String getTreatment() {
        return this.treatment;
    }

    // Встановити дату останнього візиту
    public void setLastVisitDate(Date visitDate) {
        this.lastVisitDate = visitDate;
    }

    // Отримати дату останнього візиту
    public Date getLastVisitDate() {
        return this.lastVisitDate;
    }

    // Встановити опис первинного огляду
    public void setDescriptionOfTheInitialExamination(String bodyPart, String state) {
        map.put(bodyPart, state);
    }

    // Отримати опис первинного огляду
    public HashMap<String, String> getDescriptionOfTheInitialExamination() {
        return map;
    }

    @Override
    public String toString() {
        StringBuilder TIN = new StringBuilder(); // ІПН господаря
        for (int i : getTINOfTheHost()) { // Біг по цифрах
            TIN.append(i); // Додати в рядок
        }
        StringBuilder theStateOfBodyParts = new StringBuilder("{"); // Стан частин тіла
        int size = getDescriptionOfTheInitialExamination().size(); // Розмір масиву
        int count = 1; // Лічильник
        for (Map.Entry<String, String> stringStringEntry : getDescriptionOfTheInitialExamination().entrySet()) {
            theStateOfBodyParts.append(stringStringEntry.getKey()); // Ключ
            theStateOfBodyParts.append("=\""); // Роздільник
            theStateOfBodyParts.append(stringStringEntry.getValue()).append("\""); // Значення
            if (count != size) { // Не остання позиція
                theStateOfBodyParts.append(","); // Роздільник між об'єктами масиву
            }
            count++; // Підвищити значення лічильнику на одиницю
        }
        theStateOfBodyParts.append("}"); // закрити перелік
        return "Тварина{" + '\n' +
                "    Тип=\"" + getAnimalTypes().getName() + "\"," + '\n' +
                "    Кличка=\"" + getAnimalNickname() + "\"," + '\n' +
                "    ІПН_господаря=\"" + TIN + "\"," + '\n' +
                "    ПІБ_господаря=\"" + getFullNameOfTheHost() + "\"," + '\n' +
                "    Діагноз=\"" + getDiagnosis() + "\"," + '\n' +
                "    Вага=\"" + getWeight() + "\"кг," + '\n' +
                "    Лікування=\"" + getTreatment() + "\"," + '\n' +
                "    Дата_останнього_візиту=\"" + getLastVisitDate() + "\"," + '\n' +
                "    Стан_частин_тіла=" + theStateOfBodyParts + '\n' +
                '}';
    }
}
