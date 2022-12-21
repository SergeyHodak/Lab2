import java.util.Date;

// Тварина
public interface Animal {
    void setAnimalNickname(String nickname); // Встановити кличку
    String getAnimalNickname(); // Отримати кличку
    void setTINOfTheHost(int[] TIN);// Встановити ІПН господаря
    int[] getTINOfTheHost(); // Отримати ІПН господаря
    void setFullNameOfTheHost(String fullName); // Встановити ПІБ господаря
    String getFullNameOfTheHost(); // Отримати ПІБ господаря
    void setDiagnosis(String diagnosis); // Встановити діагноз
    String getDiagnosis(); // Отримати діагноз
    void setWeight(double weight); // Встановити вагу
    double getWeight(); // Отримати вагу
    void setTreatment(String treatment); // Встановити лікування
    String getTreatment(); // Отримати лікування
    void setLastVisitDate(Date visitDate); // Встановити дату останнього візиту
    Date getLastVisitDate(); // Отримати дату останнього візиту
    void setDescriptionOfTheInitialExamination(String bodyPart, String state); // Встановити опис первинного огляду
    InitialReview getDescriptionOfTheInitialExamination(); // Отримати опис первинного огляду
}
