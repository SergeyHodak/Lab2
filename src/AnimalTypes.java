// Типи тварин
public enum AnimalTypes {
    Invertebrates(new String[]{}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Безхребетні";
        }
    },
    Pisces(new String[]{"Плавники"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Риби";
        }
    },
    Amphibia(new String[]{}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Земноводні";
        }
    },
    Reptiles(new String[]{}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Плазуни";
        }
    },
    Birds(new String[]{"Пір'я", "Дзьоб"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Птахи";
        }
    },
    Mammals(new String[]{"Лапи", "Хвіст"}) {
        // Отримати ім'я екземпляра
        @Override public String getName() {
            return "Ссавці";
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
}
