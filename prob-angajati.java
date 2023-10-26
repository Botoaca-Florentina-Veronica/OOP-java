
/*   Intr-un program de gestionare a unei firme avem doua clase: Firma si Angajat. Un angajat este
caracterizat de nume (sir de caractere) si salariu (int). Aceste caracteristici sunt setate prin constructor.
Clasa poate sa contina si alte elemente considerate necesare.
O firma este caracterizata de nume (sir de caractere) si de un buget (int) care sunt setate prin
constructor. Fiecare firma mai contine un tablou de referinte la obiecte de tip angajat (eventual si un
contor daca se considera necesar) reprezentand angajatii din cadrul firmei. Numarul maxim de
angajati este 30.
    Asupra unei firme se pot efectua urmatoarele operatii:
adaugaAngajat: ce primeste ca parametru o referinta la un obiect angajat. Daca mai este loc disponibil in tabloul de angajati
si daca acel angajat nu este deja prezent in firma (adica exact acel obiect nu este deja prezent in tabloul firmei), angajatul
este adaugat in tablou si metoda intoarce true. Altfel, adaugarea nu are loc si metoda intoarce false.

o operatie corespunzatoare care intoarce reprezentarea sub forma de sir de caractere a firmei sub forma "Nume firma: Angajat1
Angajat2..AngajatN" unde AngajatX este reprezentarea sub forma de sir de caractere a unui angajat, iar N corespunde numarului de
angajati aflati deja in firma la momentul respectiv. Un angajat e reprezentat ca sir de caractere sub forma "Angajat<nume>-<salariu>".
platesteSalarii: ce calculeaza bugetul ce ramane / lipseste daca vor fi platite salariile angajatilor
din cadrul firmei.
    Se va implementa si o metoda main in care se va crea o firma si se vor adauga 3 angajati. Apoi se va incerca adaugarea unuia
din obiectele angajat deja adaugate inca o data in cadrul firmei. Se va afisa firma la iesirea standard si apoi bugetul ramas /
lipsa daca se vor plati salarile angajatilor.
*/


class Firma{
    private String numeFirma;
    private Angajat[] angajati = new Angajat[30];  //maxim 30 de angajati
    private int buget = 0;
    private static int count = 0;   //reprezinta numarul angajatilor


    public Firma(String numeFirma, int buget)
    {
        this.numeFirma = numeFirma;
        this.buget = buget;
    }

    public int getBuget()
    {
        return buget;
    }

    public boolean adaugaAngajat(Angajat angajat)
    {
        int i;
        for(i=0; i<count; i++)
        {
            if(angajat.getNumeAngajat().equals(angajati[i].getNumeAngajat()))
            {
                //adica angajatul pe care il cautam este deja in vector, deci nu mai trebuie sa il adaugam, returnam false
                return false;
            }
        }
        if(count<30)
        {
            //daca mai avem loc in vector, si ajung in acest punct, inseamna ca angajatul cautat nu se afla in vectorul
            //meu, deci ies din bucla for de mai sus si ajung aici
            angajati[count++] = angajat;
            return true;
        }
        //daca ajung aici inseamna ca nu mai am loc sa pun angajatul in vector!
        return false;
    }

    public String toString(){
        String rezultat = "Nume firma : "  + numeFirma + "\n";
        int i;
        for(i=0; i<count; i++)
        {
            rezultat = rezultat + "Angajat " + (i+1) + ":" + angajati[i].getNumeAngajat() + "-salariu :" + angajati[i].getSalariuAngajat();
            if(i<count - 1)
            {
                rezultat = rezultat + "," + "\n";
            }
        }
        return rezultat;
    }

    public int platesteSalarii()
    {
        int i;
        int bugetNou = buget;
        for(i=0; i<count; i++)
        {
            bugetNou = bugetNou - angajati[i].getSalariuAngajat();
        }
        return bugetNou;
    }

}


class Angajat{
    private String numeAngajat;
    private int salariu =  0;

    //facem constructorul:
    public Angajat(String nume, int salariu){
        this.numeAngajat = nume;
        this.salariu = salariu;
    }

    public String getNumeAngajat()
    {
        return numeAngajat;
    }

    public int getSalariuAngajat()
    {
        return salariu;
    }
}

class Main{
    public static void main(String[] args)
    {
        Firma firma1 = new Firma("Lenovo", 300);
        Angajat a1 = new Angajat("Veronela", 100);
        Angajat a2 = new Angajat("Sandra", 50);
        Angajat a3 = new Angajat("Claudia", 30);

        firma1.adaugaAngajat(a1);
        firma1.adaugaAngajat(a2);
        firma1.adaugaAngajat(a3);

        System.out.println(firma1.toString());
        int bugetRamas = firma1.platesteSalarii();
        System.out.println("Buget ramas dupa plata salariilor: " + bugetRamas);
    }
}
