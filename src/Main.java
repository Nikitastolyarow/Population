import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Collection<Person> persons = getPersons();

        long underAgeCount = persons.stream()
                .filter(person -> person.getAge() > 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + underAgeCount);

        List<String> conscriptNames = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() > 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Список фамилий призывников: " + conscriptNames.stream().limit(15).toList());


        List<Person> WorkerHigherEducation = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER &&
                        ((person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60) ||
                                (person.getSex() == Sex.MAN && person.getAge() <= 65)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Cписок потенциально работоспособных людей: " + WorkerHigherEducation.stream().limit(15).toList());
        
    }

    public static Collection<Person> getPersons() {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        return persons;
    }
}