import java.util.Date;

// Типи тварин
public enum AnimalTypes implements Animal {
    Invertebrates(new String[]{}), // Безхребетні
    Pisces(new String[]{"Плавники"}), // Риби
    Amphibia(new String[]{}), // Земноводні
    Reptiles(new String[]{}), // Плазуни
    Birds(new String[]{"Пір'я", "Дзьоб"}), // Птахи
    Mammals(new String[]{}); // Ссавці

    // Конструктор, який приймає список частин тіла як масив
    AnimalTypes(String[] keys) {
        for (String key : keys) { // Біг по масиву частин тіла
            map.addInMap(key, null); // Додати в карту первинного огляду
        }
    }

    private String animalNickname; // Кличка тварини
    private int[] TINOfTheHost = new int[10]; // ІПН господаря (10 значне число)
    private String fullNameOfTheHost; // ПІБ господаря
    private String diagnosis; // Діагноз
    private double weight; // Вага
    private String treatment; // Лікування
    private Date lastVisitDate; // Дата останнього візиту
    private final InitialReview map = new InitialReview(); // Клас що зберігає в собі інформацію про стан частин тіла


    // Встановити кличку
    @Override public void setAnimalNickname(String nickname) {
        this.animalNickname = nickname;
    }

    // Отримати кличку
    @Override public String getAnimalNickname() {
        return this.animalNickname;
    }

    // Встановити ІПН господаря
    @Override public void setTINOfTheHost(int[] TIN) {
        this.TINOfTheHost = TIN;
    }

    // Отримати ІПН господаря
    @Override public int[] getTINOfTheHost() {
        return this.TINOfTheHost;
    }

    // Встановити ПІБ господаря
    @Override public void setFullNameOfTheHost(String fullName) {
        this.fullNameOfTheHost = fullName;
    }

    // Отримати ПІБ господаря
    @Override public String getFullNameOfTheHost() {
        return this.fullNameOfTheHost;
    }

    // Встановити діагноз
    @Override public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    // Отримати діагноз
    @Override public String getDiagnosis() {
        return this.diagnosis;
    }

    // Встановити вагу
    @Override public void setWeight(double weight) {
        this.weight = weight;
    }

    // Отримати вагу
    @Override public double getWeight() {
        return this.weight;
    }

    // Встановити лікування
    @Override public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    // Отримати лікування
    @Override public String getTreatment() {
        return this.treatment;
    }

    // Встановити дату останнього візиту
    @Override public void setLastVisitDate(Date visitDate) {
        this.lastVisitDate = visitDate;
    }

    // Отримати дату останнього візиту
    @Override public Date getLastVisitDate() {
        return this.lastVisitDate;
    }

    // Встановити опис первинного огляду
    @Override public void setDescriptionOfTheInitialExamination(String bodyPart, String state) {
        map.addInMap(bodyPart, state);
    }

    // Отримати опис первинного огляду
    @Override public InitialReview getDescriptionOfTheInitialExamination() {
        return map;
    }
}
