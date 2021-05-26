import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task {
    /**
     * Minimalny wiek dopuszczający uczestnika do konkursu (lata)
     */
    private static int MIN_AGE = 18;

    private static final String REGEXP_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    // don't modify main method
    public static void main(String[] args) {
        List<Person> participants = getParticipantsFromCsvFile();
        List<Person> winners = andTheWinnersAre(participants);

        System.out.println("Winners:");
        winners.forEach(System.out::println);
    }

    /**
     * Zwraca listę zwycięzców
     *
     * @param participants Lista uczestników
     * @return Lista zwycięzców
     */
    private static List<Person> andTheWinnersAre(List<Person> participants) {
        List<Person> validParticipants = getValidParticipants(participants);
        return validParticipants;
    }

    /**
     * Zwraca listę uczestników, którzy podali prawidłowe informacje osobiste i spełniają warunki uczestnictwa w konkursie
     *
     * @param participants Lista uczestników
     * @return Lista uczestników kwalifikujących się do konkursu
     */
    private static List<Person> getValidParticipants(List<Person> participants) {
        Pattern patternEmail = Pattern.compile(REGEXP_EMAIL);
        List<Person> validParticipants = participants.stream()
                .filter(participant ->
                        participant.getAge() >= MIN_AGE
                                && Character.isUpperCase(participant.getName().charAt(0))
                                && Character.isUpperCase(participant.getSurname().charAt(0))
                                && patternEmail.matcher(participant.getEmail()).matches()
                )
                .collect(Collectors.toList());
        return validParticipants;
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