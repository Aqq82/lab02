package tb.soft;

import java.io.*;
import java.util.Arrays;

import CollectionPackage.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, prezentująca działanie kolekcji
 *    Plik: PersonConsoleApp.java
 *
 */
public class PersonConsoleApp {

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"Wybierz kolekcję, której chcesz użyć\n" +
			"1 - HashSet \n" +
			"2 - TreeSet        \n" +
			"3 - ArrayList   \n" +
			"4 - LinkedList   \n" +
			"5 - HashMap   \n" +
			"6 - TreeMap  \n" +
			"0 - Zakończ program        \n";

	private static final String MENU_PERSON =
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Podaj dane nowej osoby \n" +
			"2 - Usuń dane aktualnej osoby z kolekcji        \n" +
			"3 - Modyfikuj dane osoby   \n" +
			"4 - Wczytaj dane z pliku   \n" +
			"5 - Zapisz dane do pliku   \n" +
			"6 - Wyświetl kolekcje      \n" +
			"7 - Dodaj aktualna osobe do kolekcji      \n" +
			"0 - Wróć \n";
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Wróć\n";

	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new ConsoleUserDialog();
	
	
	public static void main(String[] args) {
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;
	private InterfaceCollection interfaceCollection;
	
	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		SelectCollection();
		readFile("dane1");
		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU_PERSON + "==>> ")) {
					case 1:
						// utworzenie nowej osoby
						currentPerson = createNewPerson();
						break;
					case 2:
						// usunięcie danych aktualnej osoby z kolekcji
						interfaceCollection.RemoveFromCollection(currentPerson);
						currentPerson = null;
						UI.printInfoMessage("Dane aktualnej osoby zostały usunięte z kolekcji");
						break;
					case 3:
						// zmiana danych dla aktualnej osoby
						if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
						changePersonData(currentPerson);
						break;
					case 4: {
						// odczyt danych z pliku tekstowego.
						String file_name = UI.enterString("Podaj nazwę pliku: ");
						for (int i = 0; i < 10; i++) {
							currentPerson = Person.readFromFile(file_name);
							interfaceCollection.AddToCollection(currentPerson);
						}

						UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
					}
						break;
					case 5: {
						//zapis danych aktualnej osoby do pliku tekstowego
						String file_name = UI.enterString("Podaj nazwę pliku: ");
						Person.printToFile(file_name, currentPerson);
						UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
					}
						break;
					case 6:
						//wyswietlanie aktualnej kolekcji
						interfaceCollection.DisplayCollection();
						break;
					case 7:
						//dodawanie aktualnej osoby do kolekcji
						interfaceCollection.AddToCollection(currentPerson);
						break;
					case 0:
						// powrót do wyboru kolekcji
						SelectCollection();
						readFile("dane1");
				} // koniec instrukcji switch
			} catch (PersonException e) {
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			} // koniec pętli while
		}
	}
	
	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby 
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		showPerson(currentPerson);
	} 

	
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	public static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			sb.append("Aktualna osoba: \n")
			  .append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try { 
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {    
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}
	
	
	/* 
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów 
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imię: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {     
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	/*
	*	Metoda odpowiadająca za wybór kolekcji, w której mają być zapisywane osoby
	*/
	void SelectCollection(){
		UI.clearConsole();
		showCurrentPerson();

		try {
			switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// wybór kolekcji HashSet
					interfaceCollection = new CollectionHashSet();
					break;
				case 2:
					// wybór kolekcji TreeSet
					interfaceCollection = new CollectionTreeSet();
					break;
				case 3:
					// wybór kolekcji ArrayList
					interfaceCollection = new CollectionArrayList();
					break;
				case 4:
					// wybór kolekcji LinkedList
					interfaceCollection = new CollectionLinkedList();
					break;
				case 5:
					// wybór kolekcji HashMap
					interfaceCollection = new CollectionHashMap();
					break;
				case 6:
					// wybór kolekcji TreeMap
					interfaceCollection = new CollectionTreeMap();
					break;
				default:
					SelectCollection();
				case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
			} // koniec instrukcji switch
		} catch (Exception e) {

		}
	}
	public void readFile(String file_name) {
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(file_name)))) {
			for(int i=0;i<10;i++){
				interfaceCollection.AddToCollection(Person.readFromFile(reader));
			}
		} catch (FileNotFoundException e){
		} catch(IOException e){
		}catch(PersonException e){

		}
	}

}  // koniec klasy PersonConsoleApp
