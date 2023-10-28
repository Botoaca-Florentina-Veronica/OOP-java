/*
    Intr-un program de desenat avem doua clase: Desen si Figura. O figura e caracterizata de o arie (numar real) setata prin constructor.
    Clasa poate sa contina si alte elemente considerate necesare.
    Un desen e caracterizat de un sir de caractere reprezentand titlul desenului si care este setat prin constructor.
    Fiecare desen mai contine un tablou de referinte la obiecte de tip figura (eventual si un contor daca se considera necesar) reprezentand figurile care exista in desen.
    Numarul maxim de figuri permise este de 1024.
    Pe un desen putem efectua urmatoarele operati:
*adaugaFigura: ce primeste ca parametru o referinta la un obiect figura. Daca exact acelasi obiect figura nu este deja prezent in acel desen
si daca mai exista loc disponibil in tabloul de figuri al desenului, figura e adaugata la desen si metoda intoarce valoarea true.
    Altfel, adaugarea nu are loc si metoda intoarce valoarea false.
*O operatie corespunzatoare care intoarce reprezentarea sub forma de sir de caractere a desenului sub forma "Titlu desen: Figura1 Figura2 FiguraN"
 unde FiguraX este reprezentarea sub forma de sir de caractere a unei figuri, iar N corespunde numarului de figuri aflate deja in desen a momentul respectiv.
    O figura e reprezentata ca sir de caractere sub forma "Figura- Arie:valoarea_ariei".
*medieArie: ce returneaza media arilor tuturor figurilor din desen (0 daca nu exista nici o figura in desen).
     Se va implementa si o metoda main in care se va crea un desen si se vor adauga la el 3 figuri.
     Apoi se va incerca adaugarea unuia din obiectele figura deja adaugate inca o data la desen. Apoi se afiseaza desenul la iesirea standard si se afiseaza media arilor tuturor figurilor din desen.
*/


class Figura{
    private double arie;
    private String numeFigura;

    //facem constructorul pentru figura
    public Figura(double arie, String numeFigura){
        this.arie = arie;
        this.numeFigura = numeFigura;
    }

    public double getArie() {
        return arie;
    }

    public String getNumeFigura(){
        return numeFigura;
    }
}


class Desen{
    private String numeDesen;
    private static int count = 0;     //numarul de figuri din desen
    private Figura[] figuri = new Figura[1024];

    //facem constructorul pt desen
    public Desen(String numeDesen) {
        this.numeDesen = numeDesen;
    }

    public boolean adaugaFigura(Figura figura) {
        int i;
        for(i=0; i<count; i++)
        {
            if(figuri[i] == figura)
            {
                //adica daca am gasit figura cautata in vector, vom returna fals, deoarece nu avem nimic de adaugat
                return false;
            }
        }
        if(count<1024)
        {
            //daca ajungem aici inseamna ca am trecut de bucla de mai jos, deci nu am intalnit figura cautata in vector
            //deci urmeaza sa o adaugam, deci returnam true
            figuri[count++] = figura;
            return true;
        }
        return false;
        //daca ajung aici inseamna ca deja nu mai am loc in desen pentru adugarea unei noi figuri
    }

    public String toString(){
        String rezultat = "Titlu desen: " + numeDesen + "\n";
        int i;
        for(i=0; i<count; i++)
        {
            rezultat = rezultat + "Figura " + (i+1) + " " + figuri[i].getNumeFigura() + "-arie: " + figuri[i].getArie();
            if(i<count-1){
                rezultat = rezultat + "," + "\n";
            }
        }
        return rezultat;
    }

    public double medieArie(){
        double medie = 0;
        int i;
        for(i=0; i<count; i++)
        {
            medie = medie + figuri[i].getArie();
        }
        double rezultatMedie = medie/count;
        return rezultatMedie;
    }
}

class Main{
    public static void main(String[] args){
        Desen desen1 = new Desen("Peisaj vara");

        Figura figura1 = new Figura(500,"patrat");
        Figura figura2 = new Figura(100, "cerc");
        Figura figura3 = new Figura(900, "dreptunghi");

        desen1.adaugaFigura(figura1);
        desen1.adaugaFigura(figura2);
        desen1.adaugaFigura(figura3);
        desen1.adaugaFigura(figura1);

        System.out.println(desen1);


        double medie = desen1.medieArie();
        System.out.println("Media ariilor tuturor figurilor din desen este: " + medie);
    }
}
