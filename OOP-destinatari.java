/*Intr-un program de mesagerie, diversele feluri de destinatari de mesaje sunt caracterizate printre altele
de operatia receptionează(Utilizator, String): void reprezentând faptul ca destinatarul primeste de ia un
utilizator expeditor (iprimul argument) un mesaj sub formä de sir de caractere (al doilea argument).
Detalile pentru diferitele feluri de destinatari sunt prezentate în cele ce urmeazã.


Utilizator: este caracterizat de un nume care garantat identifica in mod unic un destinatar de mesaje, setat la
crearea obiectului.
Doi destinatari cu acelasi nume sunt considerati egali (unul gi acelasi destinatar).
Un utilizator este caracterizat si de un jurnal având forma unui sir de caractere în care se acumuleazã/concateneazã toate mesaje trimise si / sau receptionate de acel utilizator. De asemenea, un utilizator e caracterizat de operafia trimite(Destinatar, String): void în care i) se adaugă în jurnalul utilizatorului
un sir de nume_destinatar_indicat_de_primul caractere de forma argument_a_metodei "Trimis
către mesajul: sirul_ de_caractere_al_ mesajului indicat de_ al doilea argument_ a metodei" si ii) se apeleazã metoda recepfioneazã pe destinatarul mesajului având ca argumente utilizatorul curent ce face trimiterea si mesajul efectiv.
Când un utilizator receptionează un mesaj, se inregistreazã în jurnalul sãu un sir de caractere de forma "Primit de la nume_utilizator_expeditor mesajul: sirul_de_caractere_al_mesajului".
Un utilizator mai detine si o metodă adecvată pentru reprezentarea sub formă de sir de caractere a unui utilizator sub forma "nume_utilizator: fiecare sir de caractere din jurnalul utilizatorului pe câte o linie nouă".


Grup: reprezentând o secventa de oricâti destinatari de orice fel si în orice combinatie memoratã in starea obiectului. Un grup este caracterizat si de un nume care garantat identifică in mod unic
un destinatar de mesaje, setat la crearea obiectului.
Doi destinatari cu acelasi nume sunt considerati egali (unul si acelasi destinatar). Un destinatar poate fi adaugat la un grup prin intermediul unei metode înscrie(Destinatar): void însă numai o singură dată în mod direct
(nota: pentru simplitate se caută destinatarul de adaugat doar în secventa grupui curent nu si în eventuale alte subgrupuri).
Daca destinatarul e deja parte din grup, adaugarea nu se realizeazä si metoda se termina cu o exceptie verificata DestinatarDuplicat.
Când un grup receptioneaza un mesaj, fiecare membru al grupului receptioneazã acel mesaj(mai putin un eventual membru al grupului egal cu utilizatorul ce a trimis mesajul), apelând metoda corespunzătoare
folosind acelasi utilizator si sir de caractere primite ca argumente de metoda receptioneaza a grupului.


Se va implementa si o metodă main pentru exemplificarea functionarii. Se vor crea utilizatori "Dan", "Marius" si "Alex" si un grup "Carnivori" din care fac parte topi acesti utilizatori.
Se încearca adaugarea lui "Alex" de două ori afisându-se la iesirea standard un mesaj corespunzãtor.
Apoi "Alex" trimite catre grup mesajul '"Am deschis magazinul", apoi "Marius" trimite grupului mesajul "Vin acuma" după care se afisează la iesirea standard fiecare utilizator.
*/
import java.util.ArrayList;
import java.util.List;

// Clasa destinatar abstracta
abstract class Destinatar {
    public String nume;
    public List<String> jurnal = new ArrayList<>();

    public Destinatar(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public abstract void receptioneaza(Utilizator expeditor, String mesaj);

    @Override
    public boolean equals(Object obj) {
        // Pasul 1: Verificăm dacă obiectul de comparat este același cu obiectul curent.
        if (this == obj)
        {
            return true;
        }
        // Pasul 2: Verificăm dacă obiectul de comparat nu este nul și este o instanță a aceleiași clase.
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        // Pasul 3: Convingem compiler-ul că obiectul de comparat este o instanță a aceleiași clase.
        Destinatar destinatar = (Destinatar) obj;
        //Dacă obiectul de comparat a trecut de verificările anterioare, îl convertim la tipul Destinatar.
        //Aceasta este o conversie sigură, deoarece am verificat deja clasa obiectului în pasul anterior.

        // Pasul 4: Verificăm egalitatea numelor folosind Objects.equals.
        // Acest pas este sigur și evită NullPointerException în cazul în care nume este null.
        return nume.equals(destinatar.nume);
    }
}

// Clasa Utilizator extinde Destinatar
class Utilizator extends Destinatar {
    //facem constructorul:
    public Utilizator(String nume) {
        super(nume);
    }

    public void trimite(Destinatar destinatar, String mesaj) {
        jurnal.add("Trimis către " + destinatar.getNume() + " mesajul: " + mesaj);
        destinatar.receptioneaza(this, mesaj);
    }

    @Override
    public void receptioneaza(Utilizator expeditor, String mesaj) {
        jurnal.add("Primit de la " + expeditor.getNume() + " mesajul: " + mesaj);
    }

    public String toString() {
        String result = nume + ":\n";
        int i;
        for (i=0; i< jurnal.size(); i++) {
            result = result + jurnal.get(i)+ "\n";
        }
        return result;
    }
}

// Clasa Grup extinde Destinatar
class Grup extends Destinatar {
    private List<Destinatar> membri = new ArrayList<>();

    public Grup(String nume) {
        super(nume);
    }

    public void inscrie(Destinatar destinatar) throws DestinatarDuplicat {
        if (!membri.contains(destinatar))
        {
            membri.add(destinatar);
        }
        else
        {
            throw new DestinatarDuplicat();
        }
    }

    public void receptioneaza(Utilizator expeditor, String mesaj) {
        int i;
        for (i=0; i< membri.size(); i++) {
            if (!membri.get(i).equals(expeditor))
            {
                membri.get(i).receptioneaza(expeditor, mesaj);
            }
        }
    }
}

// Excepție pentru destinatar duplicat
class DestinatarDuplicat extends Exception {
    public DestinatarDuplicat()
    {
        super("Destinatarul este deja în grup.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Creare utilizatori
        Utilizator dan = new Utilizator("Dan");
        Utilizator marius = new Utilizator("Marius");
        Utilizator alex = new Utilizator("Alex");

        // Creare grup "Carnivori"
        Grup carnivori = new Grup("Carnivori");

        try {
            carnivori.inscrie(dan);
            carnivori.inscrie(marius);
            carnivori.inscrie(alex); // Se va afișa mesajul de excepție

            // Tentativa de a-l adăuga pe Alex din nou
            carnivori.inscrie(alex); // Se va afișa mesajul de excepție
        }
        catch (DestinatarDuplicat e)
        {
            System.out.println(e.getMessage());
        }

        // Utilizatorii trimit mesaje către grup
        dan.trimite(carnivori, "Am deschis magazinul");
        marius.trimite(carnivori, "Vin acum");

        // Afisare jurnal pentru fiecare utilizator
        System.out.println(dan);
        System.out.println(marius);
        System.out.println(alex); // Alex nu primește mesaje deoarece nu este membru al grupului
    }
}
