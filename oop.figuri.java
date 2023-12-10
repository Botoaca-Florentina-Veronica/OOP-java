/*   Fie o scena grafica care contine diferite tipuri de figuri. Concret, putem avea figuri
de tip Cerc, Patrat si Triunghi. Fiecare figura are o culoare, contine o metoda pentru
calculul perimetrului si poate fi afisata sub urmatoarea forma: Tip Figura, Culoare,
Perimetru (Culoare desemneaza valoare culorii(ex: galben, rosu) iar Perimetru valoarea perimetrului).
   Se considera ca doua figuri sunt egale daca au acelasi tip si
acelasi perimetru. Figurile de tip Cerc au o raza, cele de tip Patrat au o latura
iar cele de tip Triunghi au trei laturi. Toate figurile au metode pentru modificarea
atributelor prezentate anterior.
   Atunci cand se instantiaza, fiecare figura se inregistreaza la un observator.
   Un observator este o clasa ce are o singura instanta si contine urmatoarele metode:
 • o metoda pentru adaugarea oricarui tip de figura (Cerc, Patrat sau Triunghi)
intr-o colectie de obiecte, colectia fiind un atribut al clasei.
 • o metoda prin care se anunta observatorul ca starea unei figuri continute
(culoarea si/sau raza/latura/laturile) s-a modificat. Apelul acestei metode
e realizat de catre toate tipurile de figuri atunci cand se modifica caracteristica/caracteristicile acestora si determina afisarea tuturor figurilor monitorizate pe ecran.
 • o metoda pentru afisarea tuturor figurilor ce sunt monitorizate.

  Se cer:
 • Sa se implementeze conform cerintelor ierarhia de figuri precum si clasa ce
modeleaza conceptul de observator.
 • Sa se creeze intr-o metoda main cel putin o figura de fiecare tip, iar cel put¸in
uneia dintre ele sa i se modifice un atribut. Se va exemplifica rezultatul rularii
metodei main.
 • Sa se explice care sunt avantajele folosirii polimorfismului in cadrul acestei
aplicatii.*/

abstract class Figura{
    public String culoare;
    public abstract double calculPerimetru();

    public abstract String getCuloare();
}

class Patrat extends Figura{
    private int latura;

    //facem constructorul pentru patrat:
    public Patrat(String culoare, int latura){
        this.latura = latura;
        this.culoare = culoare;
    }

    public String getCuloare(){
        return culoare;
    }

    @Override
    public double calculPerimetru() {  //nu am voie sa ii pun parametrii!! cum ii pun in abstract class metoda asa o pun si aici
        //da ca sa fi sigura mai investigheaza!!
        return latura*4;
    }

    public String toString(){
        return "Tip figura: PATRAT, Culoare:" + culoare + " ,Perimetru: " + calculPerimetru();
    }
}

class Cerc extends Figura{
    private double raza;

    //facem constructorul pentru cerc:
    public Cerc(String culoare, double raza){
        this.culoare = culoare;
        this.raza = raza;
    }

    public String getCuloare(){
        return culoare;
    }
    public double calculPerimetru(){
        return raza * 2 * 3.14;
    }

    public String toString(){
        return "Tip figura: CERC, Culoare: " + culoare + " ,Perimetru: " + calculPerimetru();
    }
}

class Triunghi extends Figura{
    private int latura;

    //facem constructorul pentru triunghi:
    public Triunghi(String culoare, int latura){
        this.culoare = culoare;
        this.latura = latura;
    }

    public String getCuloare(){
        return culoare;
    }

    public double calculPerimetru(){
        return 3*latura;
    }

    public String toString(){
        return "Tip figura: TRIUNGHI, Culoare: " + culoare + " ,Perimetru: " + calculPerimetru();
    }
}

class Observator{

    private int nrFiguri;
    private Figura[] colectieFiguri = new Figura[10];  //presupunem ca nu putem sa avem mai mult de 10 figuri intr-o colectie
    public void adaugaFigura(Figura figura){
        if(nrFiguri < colectieFiguri.length)
        {
            //daca mai avem loc pentru a adauga noua figura data ca parametru, in colectie, atunci:
            colectieFiguri [nrFiguri++] = figura;
        }
        else
        {
            System.out.println("Nu mai avem loc in colectie pentru o alta figura!");
        }
    }

    public void anuntaModificare(Figura figura){
        System.out.println("Starea figurii a fost modificata: " + figura.toString());
    }

    public void afiseazaColectieFiguri(){
        int i;
        System.out.println("Figurile din colectie sunt: ");
        for(i=0; i<colectieFiguri.length; i++)
        {
            System.out.println(this.colectieFiguri[i].toString());
        }
    }
}

class Main{
    public static void main(String[] args){
        Observator observator = new Observator();

        Figura f1 = new Patrat("rosu", 5);
        Figura f2 = new Triunghi("albastru", 10);
        Figura f3 = new Cerc("mov", 3);

        observator.adaugaFigura(f1);
        observator.adaugaFigura(f2);
        observator.adaugaFigura(f3);

        System.out.println("Starea initiala a figurilor:");
        observator.afiseazaColectieFiguri();

        // Modificam o caracteristica a unei figuri
        f1.culoare = "galben";
        observator.anuntaModificare(f1);

        // Afisam starea actualizata a figurilor
        System.out.println("Starea actualizata a figurilor:");
        observator.afiseazaColectieFiguri();

    }

    /*
       In the provided application, polymorphism allows the Observator class to work with objects of the Figura base class,
    treating them uniformly regardless of their specific types (e.g., Cerc, Patrat, Triunghi).
    This flexibility and abstraction contribute to a more maintainable and extensible design.
     */
}

/*
METODA 2:
// Interfața pentru toate formele geometrice
interface Forma {
    String getTip();
    String getCuloare();
    double getPerimetru();
    void modificaAtribut();
}

// Clasa pentru cerc
class Cerc implements Forma {
    private String culoare;
    private double raza;

    Cerc(String culoare, double raza) {
        this.culoare = culoare;
        this.raza = raza;
        Observator.getInstance().adaugaFigura(this);
    }

    @Override
    public String getTip() {
        return "Cerc";
    }

    @Override
    public String getCuloare() {
        return culoare;
    }

    @Override
    public double getPerimetru() {
        return 2 * Math.PI * raza;
    }

    @Override
    public void modificaAtribut() {
        // Logica pentru modificarea atributelor cercului
        Observator.getInstance().notificaModificare(this);
    }
}

// Clasa pentru patrat
class Patrat implements Forma {
    private String culoare;
    private double latura;

    Patrat(String culoare, double latura) {
        this.culoare = culoare;
        this.latura = latura;
        Observator.getInstance().adaugaFigura(this);
    }

    @Override
    public String getTip() {
        return "Patrat";
    }

    @Override
    public String getCuloare() {
        return culoare;
    }

    @Override
    public double getPerimetru() {
        return 4 * latura;
    }

    @Override
    public void modificaAtribut() {
        // Logica pentru modificarea atributelor patratului
        Observator.getInstance().notificaModificare(this);
    }
}

// Clasa pentru observator
class Observator {
    private static final Observator instance = new Observator();
    private Forma[] figuri = new Forma[10];
    private int index = 0;

    private Observator() {}

    public static Observator getInstance() {
        return instance;
    }

    public void adaugaFigura(Forma forma) {
        if (index < figuri.length) {
            figuri[index++] = forma;
        } else {
            System.out.println("Nu se poate adauga mai multe figuri. Array-ul este plin.");
        }
    }

    public void notificaModificare(Forma forma) {
        // Logica pentru notificarea observatorului despre modificarea stării unei figuri
        afiseazaFiguri();
    }

    public void afiseazaFiguri() {
        System.out.println("Figurile monitorizate:");
        for (int i = 0; i < index; i++) {
            Forma forma = figuri[i];
            System.out.println(forma.getTip() + ", " + forma.getCuloare() + ", " + forma.getPerimetru());
        }
    }
}

// Metoda main pentru testare
public class Main {
    public static void main(String[] args) {
        Cerc cerc = new Cerc("Rosu", 5.0);
        Patrat patrat = new Patrat("Albastru", 4.0);

        cerc.modificaAtribut(); // Se va afisa lista cu cele doua figuri
    }
}
*/