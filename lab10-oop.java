/*   Intr-un program de gestiune a angajat¸ilor unei firme exista mai multe feluri de
angajati. Fiecare obiect angajat este caracterizat de numele sau si are o metoda calculSalar care intoarce un double reprezentand salariul acelui angajat.
   Tipurile concrete de angajati si modul specific de calculare a salariilor (pe langa alte informatii) sunt urmatoarele:
	• AngajatCuSalarFix - la crearea unui astfel de obiect se da numele angajatului
si un double reprezentand salariul sau fix. Aceste date vor fi memorate in
starea obiectului. In cazul unui astfel de angajat, metoda calculSalar intoarce
valoarea salariului data la crearea obiectului. Clasa mai define¸ste o metoda denumita schimbaSalarFix prin intermediul parametrului careia se poate schimba
valoarea salariului fix specificat la crearea obiectului.
	• AngajatCuOra - la crearea unui astfel de obiect se da numele angajatului si un double reprezentand salariul pe ora primit de angajat. Aceste date vor fi
memorate intern in starea obiectului. Clasa mai defineste metoda adaugaOre care primeste ca parametru un double reprezentand un numar de ore lucrate.
   Toate orele lucrate vor fi memorate intern intr-un obiect de acest fel, intr-un tablou de maxim 31 de intrari (se presupune ca nu se adauga mai mult de 31 de ore).
   Tot aceasta clasa defineste metoda schimbaSalarPeOra prin intermediul
parametrului careia se poate schimba salariul pe ora setat initial (se poate da alta valoare prin intermediul parametrului metodei). In cazul unui astfel de
angajat metoda calculSalar intoarece ca valoare numarul total de ore (suma orelor din tablou) inmultita cu salariul pe ora.
   Sistemul mai defineste o clasa Firma. Aceasta defineste o metoda angajeaza prin
care un angajat de orice fel (dat ca parametru metodei) este memorat ca angajat al acelei firme.
   Angajatii unei firme sunt memorati intern intr-un tablou de maxim 1024 intrari. Daca se incearca angajarea a mai mult de 1024 de angajati, metoda intoarce valoarea -1.
   Mai mult, metoda intoarce valoarea -2 in cazul in care se
incearca adaugarea aceluia si angajat la aceeasi firma (un angajat nu poate fi adaugat de doua ori la aceea¸si firma).
   Se considera ca doua obiecte angajat sunt egale atunci cand numele angajatilor sunt egale (reprezinta aceleasi secvente de caractere).
   In situatia in ca e angajarea se realizeaza cu success metoda angajeaza intoarce valoarea 0.
   Clasa Firma mai define¸ste o metod˘a salariuMediu care ˆıntoarce un double reprezentˆand salariul mediu calculat pentru toti angajatii sai (media salariilor).
   Daca obiectul de tip Firma apelat nu are nici un angajat, metoda intoarce valoarea 0.
   Se cer:
	• Implementarea claselor descrise mai sus si a altora necesare.
	• O metoda main in care se instantiaza cate doua obiecte angajat de fiecare fel
si o firma. Se adauga obiectele angajati la firma creata si se calculeaza (si afiseaza) salariul mediu pentru acea firma. Se va exemplifica apoi cazul in care se incearca angajarea aceleiasi
persoane de mai multe ori la aceeasi firma. Se va schimba apoi salariul fix sau pe ora a unui angajat din cei creati anterior si se va reafisa salariul mediu pentru acea firma.
   Nota: Se pot adauga si noi membri la clasele amintite mai sus daca se considera necesar. Schimbarea valorii salariului unui angajat trebuie sa fie vizibila dintr-un
obiect Firma care contine obiectul angajat mentionat anterior.*/


class Angajat {
    private String nume;

    public Angajat(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public double calculSalar() {
        return 0;
    }
}

class AngajatCuSalarFix extends Angajat {
    private double salariuFix;

    public AngajatCuSalarFix(String nume, double salariuFix) {
        super(nume);
        this.salariuFix = salariuFix;
    }

    @Override
    public double calculSalar() {
        return salariuFix;
    }

    public void schimbaSalarFix(double nouSalariuFix) {
        this.salariuFix = nouSalariuFix;
    }
}

class AngajatCuOra extends Angajat {
    private double salariuPeOra;
    private double[] oreLucrate = new double[31];
    private int numarOreLucrate = 0;

    public AngajatCuOra(String nume, double salariuPeOra) {
        super(nume);
        this.salariuPeOra = salariuPeOra;
    }

    @Override
    public double calculSalar() {
        int i;
        double totalOreLucrate = 0;
        double rezultat;
        for (i=0; i<numarOreLucrate; i++) {
            totalOreLucrate = totalOreLucrate + oreLucrate[i];
        }
        rezultat = totalOreLucrate * salariuPeOra;
        return rezultat;
    }

    public void adaugaOre(double ore) {
        if (numarOreLucrate < 31) {
            oreLucrate[numarOreLucrate++] = ore;
        }
    }

    public void schimbaSalarPeOra(double nouSalariuPeOra) {
        this.salariuPeOra = nouSalariuPeOra;
    }
}

class Firma {
    private Angajat[] angajati = new Angajat[1024];
    private int numarAngajati;

    //facem constructorul pentru firma:
    public Firma() {
        this.angajati = angajati;
        this.numarAngajati = 0;
    }

    public int angajeaza(Angajat angajat) {
        int i;
        if (numarAngajati >= 1024) {
            return -1; // Nu se pot adauga mai mult de 1024 de angajati
        }

        for (i=0; i<numarAngajati; i++) {
            if (angajati[i].getNume().equals(angajat.getNume())) {
                return -2; // Angajatul este deja in firma
            }
        }

        //daca am ajuns pana aici, inseamna ca angajatul dat ca parametru este unul nou si avem loc pentru acesta
        //deci il adaugam:
        angajati[numarAngajati++] = angajat;
        return 0;
    }

    public double salariuMediu() {
        int i;
        if (numarAngajati == 0) {
            return 0;
        }

        double totalSalarii = 0;
        for (i=0;  i<numarAngajati; i++) {
            totalSalarii += angajati[i].calculSalar();
        }

        return totalSalarii / numarAngajati;
    }
}

public class Main {
    public static void main(String[] args) {
        Firma firma = new Firma();

        AngajatCuSalarFix a1 = new AngajatCuSalarFix("Angajat1", 2000);
        AngajatCuSalarFix a2 = new AngajatCuSalarFix("Angajat2", 2500);

        AngajatCuOra a3 = new AngajatCuOra("Angajat3", 15);
        AngajatCuOra a4 = new AngajatCuOra("Angajat4", 20);

        firma.angajeaza(a1);
        firma.angajeaza(a2);
        firma.angajeaza(a3);
        firma.angajeaza(a4);

        System.out.println("Salariul mediu initial al firmei: " + firma.salariuMediu());

        // Incercare de angajare aceeasi persoana de mai multe ori
        int rezultatAngajare = firma.angajeaza(a1);
        if (rezultatAngajare == -2) {
            System.out.println("Eroare: Angajatul deja exista in firma.");
        }

        // Schimbare salariu fix si pe ora
        a1.schimbaSalarFix(2200);
        a3.schimbaSalarPeOra(18);

        System.out.println("Salariul mediu dupa schimbarea salariilor: " + firma.salariuMediu());
    }
}
