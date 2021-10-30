package Project1_SistemDeGestiuniInscrieri;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Bun venit! Introduceti numarul de locuri disponibil: ");
        int maxNoOfGuests = sc.nextInt();
        GuestsList guestsList = new GuestsList(maxNoOfGuests-1);


        System.out.println();
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi...)");
        boolean quit = false;

        while (!quit) {
            String comanda = sc.next();

            switch (comanda) {
                case "help":
                    guestsList.help();
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "add":
                    System.out.println("Se adauga o persoana noua...   ");
                    Guest newGuest = addGuest(sc);
                    guestsList.add(newGuest);
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "remove":
                    Guest guestToRemove = removeGuest(sc);
                    guestsList.remove(guestToRemove);
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "quit":
                    quit = true;
                    break;
                case "update":
                    Guest guestToUpdate = updateGuest(sc);
                    Guest originalGuest = guestsList.findGuestInGuestList(guestToUpdate);
                    if (originalGuest == null) {
                        System.out.println("Guest " + guestToUpdate + " not found");
                        break;
                    }
                    Guest newGuestData = readNewGuestData(sc);
                    guestsList.updateGuest(originalGuest, newGuestData);
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                   break;
                case "displayGuestsList":
                    guestsList.displayGuestsList();
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "displayWaitingList":
                    guestsList.displayWaitingList();
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "available":
                    System.out.print("Numarul disponibil de locuri: " + guestsList.available());
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "guestsNo":
                    System.out.println("Numarul de participanti: " + guestsList.guestsNo());
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "waitingListNo":
                    System.out.println("Dimensiunea listei de asteptare: " + guestsList.waitingListNo());
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "subscribe_no":
                    System.out.println(guestsList.subscribeNo());
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "findByCharacters":
                    System.out.println("Introduceti sirul de caractere dupa conform invitatilor cautati");
                    String characters = sc.next();
                    guestsList.findByCharacters(characters);
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "findByName":
                    System.out.println();
                    System.out.println("Introduceti numele de familie:");
                    String lastName = sc.next();
                    System.out.println("Introduceti prenumele:");
                    String firstName = sc.next();
                    guestsList.findByNameInGuestsList(lastName, firstName);
                    guestsList.findByNameInGuestsList(lastName, firstName);
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "findByEmail":
                    System.out.println("Introduceti email:");
                    String email = sc.next();
                    guestsList.findByEmailInGuestsList(email);
                    guestsList.findByEmailInWaitingList(email);
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                case "findByPhone":
                    System.out.println("Introduceti numar de telefon (format „+40733386463“):");
                    String phoneNumber = sc.next();
                    guestsList.findByPhoneNumberInGuestsList(phoneNumber);
                    guestsList.findByPhoneNumberInWaitingList(phoneNumber);
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
                    break;
                default :
                    System.out.println("Nu ati introdus o comanda valida. Va rugam reincercati!");
                    System.out.println("\nAsteapta comanda: (help - Afiseaza lista de comenzi)");
            }
        }
    }

    public static Guest addGuest(Scanner sc) {
        System.out.println();
        System.out.println("Introduceti numele de familie:");
        String lastName = sc.next();
        System.out.println("Introduceti prenumele:");
        String firstName = sc.next();
        System.out.println("Introduceti email:");
        String email = sc.next();
        System.out.println("Introduceti numar de telefon (format „+40733386463“):");
        String phoneNumber = sc.next();

        System.out.println();
        Guest guest = new Guest(lastName, firstName, email, phoneNumber);
        return guest;
    }

    public static Guest removeGuest(Scanner sc) {
        System.out.println("Se sterge o persoana existenta din lista... ");
        System.out.println("Alege modul de autentificare tastand: ");
        System.out.println("\"1\" - Nume si prenume");
        System.out.println("\"2\" - Email");
        System.out.println("\"3\" - Numar de telefon");

        int choice = sc.nextInt();
        Guest guest = new Guest();

        switch (choice) {
            case 1:
                System.out.println("Introduceti numelele de familie:");
                String nume = sc.next();
                System.out.println("Introduceti prenumele:");
                String prenume = sc.next();

                guest.setFirstName(nume);
                guest.setLastName(prenume);

                break;
            case 2:
                System.out.println("Introduceti email:");
                String email = sc.next();
                guest.setEmail(email);

                break;
            case 3:
                System.out.println("Introduceti numarul de telefon:");
                String phone = sc.next();
                guest.setPhoneNumber(phone);

                break;
            default:
                System.out.println("Optiune invalida!");
                return new Guest();
        }

        return guest;
    }

    public static Guest updateGuest(Scanner sc) {
        System.out.println("Se actualizeaza detaliile unei persoane... ");
        System.out.println("Alege modul de autentificare tastand: ");
        System.out.println("\"1\" - Nume si prenume");
        System.out.println("\"2\" - Email");
        System.out.println("\"3\" - Numar de telefon");

        int choice = sc.nextInt();
        Guest guest = new Guest();

        switch (choice) {
            case 1:
                System.out.println("Introduceti numele de familie:");
                String nume = sc.next();
                System.out.println("Introduceti prenumele:");
                String prenume = sc.next();

                guest.setFirstName(prenume);
                guest.setLastName(nume);

                break;
            case 2:
                System.out.println("Introduceti email:");
                String email = sc.next();
                guest.setEmail(email);

                break;
            case 3:
                System.out.println("Introduceti numarul de telefon:");
                String phone = sc.next();
                guest.setPhoneNumber(phone);

                break;
            default:
                System.out.println("Optiune invalida!");
                return new Guest();
        }

        return guest;
    }

    public static Guest readNewGuestData(Scanner sc) {

        Guest guest = new Guest();

        System.out.println("Alege campul de actualizat, tastand:");
        System.out.println("\"1\" - Nume");
        System.out.println("\"2\" - Prenume");
        System.out.println("\"3\" - Email");
        System.out.println("\"4\" - Numar de telefon");
        int choice2 = sc.nextInt();
        switch (choice2) {
            case 1:
                System.out.println("Introduceti numele de familie:");
                String nume = sc.next();
                guest.setLastName(nume);

                break;
            case 2:
                System.out.println("Introduceti prenumele:");
                String prenume = sc.next();
                guest.setFirstName(prenume);

                break;
            case 3:
                System.out.println("Introduceti email:");
                String email = sc.next();
                guest.setEmail(email);

                break;
            case 4:
                System.out.println("Introduceti numarul de telefon:");
                String phone = sc.next();
                guest.setPhoneNumber(phone);

                break;
            default:
                System.out.println("Optiune invalida!");
                return new Guest();
        }

        return guest;
    }
}
