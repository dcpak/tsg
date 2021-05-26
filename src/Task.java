import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task {

    // don't modify main method
    public static void main(String[] args) {
        List<Person> participants = getParticipantsFromCsvFile();
        List<Person> winners = andTheWinnersAre(participants);

        System.out.println("Winners:");
        winners.forEach(System.out::println);
    }

    private static List<Person> andTheWinnersAre(List<Person> participants) {
        return participants;
    }

    // don't modify getParticipantsFromCsvFile method
    private static List<Person> getParticipantsFromCsvFile() {
        List<Person> participants = new ArrayList<>();

        File file = new File("resources\\participants.csv");
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String line;
            while ((line = buffer.readLine()) != null) {
                if (line != null) {
                    String[] split = line.split(";");
                    participants.add(new Person(split[0], split[1], Integer.parseInt(split[2]), split[3]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return participants;
    }
}

class Person {
    private static final String TPL_AS_STRING = "%s %s, %d, email: %s";

    private String name;
    private String surname;
    private int age;
    private String email;

    public Person(String name, String surname, int age, String email) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return String.format(TPL_AS_STRING, this.getName(), this.getSurname(), this.getAge(), this.getEmail());
    }

}