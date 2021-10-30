package Project1_SistemDeGestiuniInscrieri;

import java.util.ArrayList;

public class GuestsList {

    private final int maxNoOfGuests;
    private ArrayList<Guest> guestsList;
    private ArrayList<Guest> waitingList = new ArrayList<>();

    public GuestsList(int maxNoOfGuests){
        this.guestsList = new ArrayList<Guest>(maxNoOfGuests);
        this.maxNoOfGuests = maxNoOfGuests + 1;
    }

    public int getMaxNoOfGuests() {
        System.out.println("getMaxNoOfGuests: " + this.maxNoOfGuests);
        return this.maxNoOfGuests;
    }

    public static Guest findByName(String lastName, String firstName, ArrayList<Guest> guests) {
        for (Guest guest : guests) {
            if (guest.getFirstName().equals(firstName) && guest.getLastName().equals(lastName)) {
                return guest;
            }
        }
        return null;
    }

    public static Guest findByEmail(String email, ArrayList<Guest> guests) {
        for (Guest guest : guests) {
            if (guest.getEmail().equals(email)) {
                return guest;
            }
        }
        return null;
    }

    public static Guest findByPhoneNumber(String phoneNumber, ArrayList<Guest> guests) {
        for (Guest guest : guests) {
            if (guest.getPhoneNumber().equals(phoneNumber)) {
                return guest;
            }
        }
        return null;
    }

    public Guest findByNameInGuestsList(String lastName, String firstName){
        return findByName(lastName, firstName, guestsList);
    }

    public Guest findByNameInWaitingList(String lastName, String firstName){
        return findByName(lastName, firstName, waitingList);
    }

    public Guest findByEmailInGuestsList(String email){
        return findByEmail(email, guestsList);
    }

    public Guest findByEmailInWaitingList(String email){
        return findByEmail(email, waitingList);
    }

    public Guest findByPhoneNumberInGuestsList(String phoneNumber){
        return findByEmail(phoneNumber, guestsList);
    }

    public Guest findByPhoneNumberInWaitingList(String phoneNumber){
        return findByEmail(phoneNumber, waitingList);
    }

    public int add(Guest guest){
        if ((this.guestsList.size() < this.maxNoOfGuests) &&
                (
                        findByNameInGuestsList(guest.getLastName(), guest.getFirstName()) == null ||
                        findByPhoneNumberInGuestsList(guest.getPhoneNumber()) == null ||
                        findByEmailInGuestsList(guest.getEmail()) == null
                )
        ) {

            this.guestsList.add(guest);
            System.out.println(guest.getLastName() + " " + guest.getFirstName() + " - Felicitari! Locul tau la eveniment este confirmat! Te asteptam!");
            return 0;
        } else if ((this.guestsList.size() >= this.maxNoOfGuests) &&
                (findByNameInWaitingList(guest.getLastName(), guest.getFirstName()) == null ||
                findByPhoneNumberInWaitingList(guest.getPhoneNumber()) == null ||
                findByEmailInWaitingList(guest.getEmail()) == null)
        ) {
            this.waitingList.add(guest);
            System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine <" + this.waitingList.size() +
                    ">. Te vom notifica daca un loc devine disponibil.");
            return this.waitingList.size();
        } else {
            System.out.println("Deja ati fost inscris la eveniment! Va multumim!");
            return -1;
        }
    }

    public boolean remove (Guest guestToRemove) {

        Guest guest = new Guest();
        if (guestToRemove.getFirstName() != null &&
            guestToRemove.getLastName() != null &&
            !guestToRemove.getFirstName().equals("") &&
            !guestToRemove.getLastName().equals("")) {
            guest = this.findByNameInGuestsList(guestToRemove.getLastName(), guestToRemove.getFirstName());
        } else if (guestToRemove.getEmail() != null && !guestToRemove.getEmail().equals("")) {
            guest = this.findByEmailInGuestsList(guestToRemove.getEmail());
        } else if (guestToRemove.getPhoneNumber() != null){
            guest = this.findByPhoneNumberInGuestsList(guestToRemove.getPhoneNumber());
        }

        if (guest != null) {
            this.guestsList.remove(guest);

            if (this.waitingList.size() != 0) {
                this.guestsList.add(this.waitingList.get(0));
                this.waitingList.remove(0);
            }
            System.out.println("Persoana " + guest + " a fost stearsa cu succes!");
            return true;
        }

        if (guestToRemove.getFirstName() != null &&
            guestToRemove.getLastName() != null &&
            !guestToRemove.getFirstName().equals("") &&
            !guestToRemove.getLastName().equals("")) {
            guest = this.findByNameInWaitingList(guestToRemove.getLastName(), guestToRemove.getFirstName());
        }else if (guestToRemove.getEmail() != null && !guestToRemove.getEmail().equals("")) {
            guest = this.findByEmailInWaitingList(guestToRemove.getEmail());
        } else if (guestToRemove.getPhoneNumber() != null){
            guest = this.findByPhoneNumberInWaitingList(guestToRemove.getPhoneNumber());
        }

        if (guest != null) {
            this.waitingList.remove(guest);

            System.out.println("Persoana " + guest + " a fost stearsa cu succes!");
            return true;
        } else {
            System.out.println("Persoana nu a fost inscrisa la eveniment");
            return false;
        }
    }

    public Guest findGuestInGuestList(Guest guestToUpdate) {
        Guest guest = new Guest();
        if (guestToUpdate.getFirstName() != null &&
            guestToUpdate.getLastName() != null &&
            !guestToUpdate.getFirstName().equals("") &&
            !guestToUpdate.getLastName().equals("")) {
            guest = this.findByNameInGuestsList(guestToUpdate.getLastName(), guestToUpdate.getFirstName());
        } else if (guestToUpdate.getEmail() != null && !guestToUpdate.getEmail().equals("")) {
            guest = this.findByEmailInGuestsList(guestToUpdate.getEmail());
        } else if (guestToUpdate.getPhoneNumber() != null) {
            guest = this.findByPhoneNumberInGuestsList(guestToUpdate.getPhoneNumber());
        }

        return guest;
    }

    public boolean updateGuest(Guest originalGuest, Guest newGuestData) {
        int originalGuestIndex = this.guestsList.indexOf(originalGuest);
        if (newGuestData.getPhoneNumber() != null && !newGuestData.getPhoneNumber().equals("")){
            originalGuest.setPhoneNumber(newGuestData.getPhoneNumber());
        }

        if (newGuestData.getEmail() != null && !newGuestData.getEmail().equals("")){
            originalGuest.setEmail(newGuestData.getEmail());
        }

        if (newGuestData.getFirstName() != null && !newGuestData.getFirstName().equals("")){
            originalGuest.setFirstName(newGuestData.getFirstName());
        }

        if (newGuestData.getLastName() != null && !newGuestData.getLastName().equals("")){
            originalGuest.setLastName(newGuestData.getLastName());
        }

        guestsList.set(originalGuestIndex, originalGuest);

        return true;
    }

    public void displayGuestsList(){
        if (this.guestsList.size() == 0){
            System.out.println("Niciun participant inscris…");
        } else {
            for (int i = 0; i < this.guestsList.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + this.guestsList.get(i).toString());
            }
        }
    }

    public void displayWaitingList(){
        if (this.waitingList.size() == 0) {
            System.out.println("Lista de asteptare este goala...");
        } else {
            for (int i = 0; i < this.waitingList.size(); i++) {
                    System.out.println("(" + (i  + 1) + ") " + this.waitingList.get(i).toString());
            }
        }
    }

    public int available(){
        if (this.maxNoOfGuests > this.guestsList.size()) {
//            System.out.print("Numarul disponibil de locuri: ");
//            System.out.println(this.maxNoOfGuests - this.guestsList.size());
            return (maxNoOfGuests - guestsNo());
        } else {
            System.out.print("Nu mai sunt locuri disponibile pe lista de invitati!");
            return -1;
        }
    }

    public int guestsNo(){
//        System.out.println("Numarul de participanti este: " + this.guestsList.size());
        return this.guestsList.size();
    }

    public int waitingListNo(){
//        System.out.println("Numarul de persoane din lista de asteptare este de: " + this.waitingList.size());
        return this.waitingList.size();
    }

    public int subscribeNo(){
        System.out.print("Numarul total de persoane din ambele liste este: ");
        return guestsNo() + waitingListNo();
    }

    public String findByCharacters(String characters) {
        characters = characters.toLowerCase();
        for (int i = 0; i < this.guestsList.size(); i++) {
            if (this.guestsList.get(i).getLastName().toLowerCase().contains(characters)) {
                System.out.println("Contact " + (i + 1) + "- din guests list - : contine lastName = " + this.guestsList.get(i).getLastName());
                return this.guestsList.get(i).getLastName();
            }
            if (this.guestsList.get(i).getFirstName().toLowerCase().contains(characters)) {
                System.out.println("Contact " + (i + 1) + "- din guests list - : contine firstName = " + this.guestsList.get(i).getFirstName());
                return this.guestsList.get(i).getFirstName();
            }
            if (this.guestsList.get(i).getEmail().toLowerCase().contains(characters)) {
                System.out.println("Contact " + (i + 1) + "- din guests list - : contine email = " + this.guestsList.get(i).getEmail());
                return this.guestsList.get(i).getEmail();
            }
            if (this.guestsList.get(i).getPhoneNumber().toLowerCase().contains(characters)) {
                System.out.println("Contact " + (i + 1) + "- din guests list - : contine phoneNumber = " + this.guestsList.get(i).getPhoneNumber());
                return this.guestsList.get(i).getPhoneNumber();
            }
        }
        for (int i = 0; i < this.waitingList.size(); i++) {
            if (this.waitingList.get(i).getLastName().toLowerCase().contains(characters)){
                System.out.println("Contact " + (i + 1) + "- din waiting list - : contine lastName = " + this.waitingList.get(i).getLastName());
                return this.waitingList.get(i).getLastName();
            }
            if (this.waitingList.get(i).getFirstName().toLowerCase().contains(characters)){
                System.out.println("Contact " + (i + 1) + "- din waiting list - : contine firstName = " + this.waitingList.get(i).getFirstName());
                return this.waitingList.get(i).getFirstName();
            }
            if (this.waitingList.get(i).getEmail().toLowerCase().contains(characters)){
                System.out.println("Contact " + (i + 1) + "- din waiting list - : contine email = " + this.waitingList.get(i).getEmail());
                return this.waitingList.get(i).getEmail();
            }
            if (this.waitingList.get(i).getPhoneNumber().toLowerCase().contains(characters)){
                System.out.println("Contact " + (i + 1) + "- din waiting list - : contine phoneNumber = " + this.waitingList.get(i).getPhoneNumber());
                return this.waitingList.get(i).getPhoneNumber();
            }
        }
        return null;
    }

    public void quit(){
        System.out.println("Aplicatia se inchide...");
    }

    public void help(){
        System.out.println("help - Afiseaza aceasta lista de comenzi\n" +
                "add - Adauga o noua persoana (inscriere)\n" +
                "findByName/ findByEmail/ findByPhoneNumber - Verifica daca o persoana este inscrisa " +
                "la eveniment\n" +
                "remove - Sterge o persoana existenta din lista\n" +
                "update - Actualizeaza detaliile unei persoane\n" +
                "displayGuestsList - Lista de persoane care participa la eveniment\n" +
                "displayWaitingList - Persoanele din lista de asteptare\n" +
                "available - Numarul de locuri libere\n" +
                "guestsNo - Numarul de persoane care participa la eveniment\n" +
                "waitingListNo - Numarul de persoane din lista de asteptare\n" +
                "subscribe_no - Numarul total de persoane inscrise\n" +
                "findByCharacters - Cauta toti invitatii conform sirului de caractere introdus\n" +
                "quit - Inchide aplicatia");
    }
}

