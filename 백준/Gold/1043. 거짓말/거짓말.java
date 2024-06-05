import java.util.*;

public class Main{

    static class Person{
        int num;
        Person present;
        boolean isKnown;

        public Person(int num, Person present, boolean isKnown) {
            this.num = num;
            this.present = present == null ? this : present;
            this.isKnown = isKnown;
        }

        public Person union(Person person){
            if(person == null) return this;

            setIsKnown(person);

            if(num > person.num){
                this.present = person;
                return person;
            }

            person.present = this;
            return this;
        }

        public Person findPresent(){
            if(this == this.present){
                return this;
            }

            Person present = this.present.findPresent();
            this.present = present;

            return present;
        }

        private void setIsKnown(Person person){
            boolean isKnown = person.isKnown || this.isKnown;
            this.isKnown = isKnown;
            person.isKnown = isKnown;
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int m = scan.nextInt();

        Map<Integer, Person> people = new HashMap<>();

        for (int i = 1; i <= 50; i++) {
            people.put(i, new Person(i, null, false));
        }

        int knowNum = scan.nextInt();
        Person present = null;
        for (int i = 0; i < knowNum; i++) {
            Person person = people.get(scan.nextInt());
            person.isKnown=true;
            present = person.findPresent().union(present);
        }

        int[] parties = new int[m];
        for (int i = 0; i < m; i++) {
            int num = scan.nextInt();
            present = null;
            for (int j = 0; j < num; j++) {
                parties[i] = scan.nextInt();
                Person person = people.get(parties[i]);
                present = person.findPresent().union(present);
            }
        }

        int answer = 0;
        for (int party : parties) {
            answer += people.get(party).findPresent().isKnown ? 0 : 1;
        }

        System.out.println(answer);
    }
}
