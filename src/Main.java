import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class SchoolManagementSystem {
    public static void main(String[] args) {
        School school = new School("Гимназия №3", "Бухар Жырау 19", 530, 2009);

        Teacher teacher1 = new Teacher("Ирина Николаевна", 1955, 30);
        Teacher teacher2 = new Teacher("Марал Инырбаевна", 1968, 20);
        Teacher teacher3 = new Teacher("Татьяна Михайловна", 1960, 25);

        Student student1 = new Student("Санжар Сагатов", 2006, 4);
        Student student2 = new Student("Даниал Нуртаза", 2006, 5);
        Student student3 = new Student("Танбай Елнур", 2007, 4);

        List<Person> people = new ArrayList<>();
        people.add(teacher1);
        people.add(teacher2);
        people.add(teacher3);
        people.add(student1);
        people.add(student2);
        people.add(student3);

        System.out.println(school);
        System.out.println("\nИнформация о всех людях:");
        for (Person person : people) {
            System.out.println(person);
        }

        System.out.println("\nСортирова студентов по среднему баллу:");
        people.stream()
                .filter(p -> p instanceof Student)
                .map(p -> (Student) p)
                .sorted(Comparator.comparingInt(Student::getAverageScore).reversed())
                .forEach(System.out::println);
    }
}

abstract class Person {
    private String name;
    private int birthYear;

    public Person(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", Год рождения: " + birthYear;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return birthYear == person.birthYear && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + birthYear;
    }

    public abstract void displayInfo();
}

class Teacher extends Person {
    private int workExperience;

    public Teacher(String name, int birthYear, int workExperience) {
        super(name, birthYear);
        this.workExperience = workExperience;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    @Override
    public void displayInfo() {
        System.out.println("Учитель: " + this);
    }

    @Override
    public String toString() {
        return super.toString() + ", Опыт работы: " + workExperience + " лет";
    }
}

class Student extends Person {
    private int averageScore;

    public Student(String name, int birthYear, int averageScore) {
        super(name, birthYear);
        this.averageScore = averageScore;
    }

    public int getAverageScore() {
        return averageScore;
    }

    @Override
    public void displayInfo() {
        System.out.println("Ученик: " + this);
    }

    @Override
    public String toString() {
        return super.toString() + ", Средний балл: " + averageScore;
    }
}

class School {
    private String name;
    private String address;
    private int numberOfStudents;
    private int foundationYear;

    public School(String name, String address, int numberOfStudents, int foundationYear) {
        this.name = name;
        this.address = address;
        this.numberOfStudents = numberOfStudents;
        this.foundationYear = foundationYear;
    }

    @Override
    public String toString() {
        return "Школа: " + name + ", Адрес: " + address + ", Ученики: " + numberOfStudents + ", Выпуск: " + foundationYear;
    }
}
