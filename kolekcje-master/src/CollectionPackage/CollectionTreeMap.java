package CollectionPackage;

import tb.soft.Person;
import tb.soft.PersonConsoleApp;

import java.util.TreeMap;

public class CollectionTreeMap implements InterfaceCollection{

    private TreeMap<Integer, Person> kolekcja;

    public CollectionTreeMap() {
        kolekcja = new TreeMap<Integer, Person>();
    }

    public void AddToCollection(Person person){

        kolekcja.put(kolekcja.size(),person);

    }
    public void RemoveFromCollection(Person person){
        for (int i=0;i<kolekcja.size();i++) {
            if(kolekcja.get(i).equals(person)) {
                kolekcja.remove(i);
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
