import java.util.Random;
import java.util.Scanner;

class Telefon {
    private String numeProprietar;
    private static int count = 0;
    private String[] apelatori = new String[100];

    public Telefon(String numeProprietar) {
        this.numeProprietar = numeProprietar;
    }

    public void adaugaApelator(String nume) {
        apelatori[count] = nume;
        count++;
    }

    public boolean apeleaza(Telefon telefonApelat) {
        if (count < 100) {
            telefonApelat.adaugaApelator(numeProprietar);
            return true;
        }
        return false;
    }

    public int numarDeApeluri(String nume) {
        int nrApeluri = 0;
        int i;
        for (i=0; i<count; i++) {
            if (nume.equals(apelatori[i])) {
                nrApeluri++;
            }
        }
        return nrApeluri;
    }

    public String toString() {
        int i;
        String rezultat = "Proprietar: " + numeProprietar + "\n";
        rezultat = rezultat + "Apelatori: ";
        for (i=0; i<count; i++) {
            if (apelatori[i] != null) {
                rezultat = rezultat + apelatori[i];
                if (i < count - 1) {
                    rezultat = rezultat + ",";
                }
            }
        }
        return rezultat;
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int i;

        System.out.println("Cate telefoane trebuie create: ");
        int numarTelefoane = scanner.nextInt();
        Telefon[] telefoane = new Telefon[numarTelefoane];

        for (i = 0; i < numarTelefoane; i++) {
            System.out.print("Numele proprietarului telefonului " + (i + 1) + ": ");
            String numeProprietar = scanner.next();
            telefoane[i] = new Telefon(numeProprietar);
        }

        System.out.print("Numarul de apeluri de efectuat (A): ");
        int A = scanner.nextInt();

        for (i=0; i<A; i++) {
            int x = random.nextInt(numarTelefoane);   //x=telefoane sursa
            int y = random.nextInt(numarTelefoane);   //y=telefoane destinatie
            telefoane[x].apeleaza(telefoane[y]);
        }

        System.out.print("Numele proprietarului pentru a verifica apelurile: ");
        String numeVerificare = scanner.next();

        for (Telefon telefon : telefoane) {
            System.out.println(telefon);
            int nrApeluri = telefon.numarDeApeluri(numeVerificare);
            System.out.println("Număr de apeluri pentru " + numeVerificare + ": " + nrApeluri);
            System.out.println();
        }
    }
}
