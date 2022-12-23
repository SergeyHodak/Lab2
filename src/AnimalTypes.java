// Типи тварин
public enum AnimalTypes {
    Invertebrates(new String[]{"Лапи", "Вуса", "Крила"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Безхребетні";
        }
        // Константа для розрахунку вартості прийому
        @Override public double getConstant() {
            return 0.1;
        }
    },
    Pisces(new String[]{"Плавники"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Риби";
        }
        // Константа для розрахунку вартості прийому
        @Override public double getConstant() {
            return 1.0;
        }
    },
    Amphibia(new String[]{"Очі", "Шкіра"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Земноводні";
        }
        // Константа для розрахунку вартості прийому
        @Override public double getConstant() {
            return 1.5;
        }
    },
    Reptiles(new String[]{"Луска"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Плазуни";
        }
        // Константа для розрахунку вартості прийому
        @Override public double getConstant() {
            return 2.0;
        }
    },
    Birds(new String[]{"Пір'я", "Дзьоб"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Птахи";
        }
        // Константа для розрахунку вартості прийому
        @Override public double getConstant() {
            return 1.4;
        }
    },
    Mammals(new String[]{"Лапи", "Хвіст"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Ссавці";
        }
        // Константа для розрахунку вартості прийому
        @Override public double getConstant() {
            return 3.0;
        }
    };

    private String[] partsOfTheBody; // Частини тіла

    // Отримати список частин тіла
    public String[] getPartsOfTheBody() {
        return partsOfTheBody;
    }

    // Встановити список частин тіла
    public void setPartsOfTheBody(String[] partsOfTheBody) {
        this.partsOfTheBody = partsOfTheBody;
    }

    // Конструктор, який приймає список частин тіла
    AnimalTypes(String[] names) {
        setPartsOfTheBody(names);
    }

    // Отримати ім'я екземпляра
    public String getName() {
        return null;
    }

    // Константа для розрахунку вартості прийому
    public double getConstant() {
        return 0;
    }
}
