package CollectionPackage;

import tb.soft.Person;
import tb.soft.PersonConsoleApp;

import java.util.ArrayList;

public class CollectionArrayList implements InterfaceCollection{

    private ArrayList<Person> kolekcja;

    public CollectionArrayList() {
        this.kolekcja = new ArrayList<>();
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
        System.out.println("");
        for (Person x:kolekcja) {
            System.out.println("");
            PersonConsoleApp.showPerson(x);
        }
    }
}
