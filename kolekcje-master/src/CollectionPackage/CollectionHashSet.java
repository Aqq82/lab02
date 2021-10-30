package CollectionPackage;

import tb.soft.Person;
import tb.soft.PersonConsoleApp;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class CollectionHashSet implements InterfaceCollection{

    private HashSet<Person> kolekcja;

    public CollectionHashSet() {
        kolekcja = new HashSet<>();
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
