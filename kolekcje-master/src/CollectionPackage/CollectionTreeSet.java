package CollectionPackage;

import tb.soft.Person;

import java.util.Comparator;
import java.util.TreeSet;
import tb.soft.PersonConsoleApp;

public class CollectionTreeSet implements InterfaceCollection{

    Comparator<Person> comparator = Comparator.comparing(Person::getFirstName);

    private TreeSet<Person> kolekcja;

    public CollectionTreeSet() {
        kolekcja = new TreeSet<>(comparator);
    }

    public void AddToCollection(Person person){
        kolekcja.add(person);
    }

    public void RemoveFromCollection(Person person){
        for (Person x:kolekcja) {
            if(x.equals(person)) {
                kolekcja.remove(person);
                break;
            }
        }
    }


    public void DisplayCollection(){
        int i=1;
        System.out.println("Zawartość kolekcji:");
        for (Person x:kolekcja) {
            System.out.print(i++ +". ");
            PersonConsoleApp.showPerson(x);
        }
    }
}
