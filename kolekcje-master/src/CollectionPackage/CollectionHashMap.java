package CollectionPackage;

import tb.soft.Person;
import tb.soft.PersonConsoleApp;

import java.util.HashMap;

public class CollectionHashMap implements InterfaceCollection{

    private HashMap<Integer, Person> kolekcja;

    public CollectionHashMap() {
        this.kolekcja = new HashMap<Integer, Person>();
    }

    public void AddToCollection(Person person){
        kolekcja.put(kolekcja.size(),person);
    }

    public void RemoveFromCollection(Person person){
        for (int i=0;i<kolekcja.size();i++) {
            if(kolekcja.get(i).equals(person)) {
                kolekcja.remove(person);
                break;
            }
        }
    }

    public void DisplayCollection(){
        System.out.println("Zawartość kolekcji:");
        for (int i=0;i<kolekcja.size();i++) {
            System.out.print(i+1 +". ");
            PersonConsoleApp.showPerson(kolekcja.get(i));
        }
    }
}
