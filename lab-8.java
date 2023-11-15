
/*
    Consideram o colectie de greutati in cadrul careia elementele sunt retinute sub
forma unui tablou. Fiecare greutate are o anumita capacitate care poate fi obtinuta apeland metoda public int capacitate() pe care o are fiecare greutate.
    Greutatile pot fi de urmatoarele tipuri:
    • simple, a caror capacitate este setata prin constructor.
    • duble, adica formate din 2 greutati ce sunt stocate in doua campuri de tip Greutate.
    Aceste greutati sunt setate prin constructor dar pot sa fie modificate pe
parcursul existentei obiectelor de acest tip prin intermediul a doua metode accesor (public void setGreutate1(Greutate g), public void setGreutate2(Greutate
g)).
    Capacitatea acestui tip de greutate e egala cu suma capacitatilor celor doua greutati continute. Capacitatea acestui tip de greutate nu va fi retinuta
intr-un atribut, ci va fi calculata de fiecare data cand unui obiect de acest tip i se va solicita serviciul capacitate().
    • multiple, care reprezinta o insiruire de greutati simple, duble, si/sau eventual alte greutati multiple. Cu alte cuvinte, o greutate multipla reprezinta o
insiruire de greutati. Capacitatea unei greutati de acest tip este egala cu suma capacitatilor greutatilor componente. Componentele acestui tip de greutate
se seteaza prin constructorul clasei, dar se poate alege si o alta modalitate de inserare a componentelor. Ca si in cazul clasei descrise anterior, capacitatea
acestui tip de greutate nu va fi retinuta intr-un atribut, ci va fi calculata de fiecare data cand unui obiect de acest tip i se va solicita serviciul capacitate().
    Sistemul mai cuprinde si clasa ColectieGreutati care contine un tablou de greutati (acestea reprezinta continutul efectiv al colectiei).
     Clasa ColectieGreutati va contine urmatoarele metode:
    • public void adauga(Greutate g): are rolul de a adauga elemente in tabloul de greutati.
    Presupunem ca o colectie de greutati are o capacitate maxima de greutati care se seteaza prin intermediul constructorului.
    • public double medie(): returneaza greutatea medie a colectiei (capacitate/numar de greutati).
    Se cere:
    • o metoda main in care se va crea un obiect ColectieGreutati, cateva greutati simple, duble si multiple care vor fi adaugate colectiei de greutati. Se va afisa
greutatea medie a colectiei.
*/

abstract class Greutate{
    public abstract int capacitate();
}

class GreutateSimpla extends Greutate{
    private int capacitate;
    public GreutateSimpla(int capacitate){
        this.capacitate = capacitate;
    }

    public int capacitate(){
        return capacitate;
    }
}

class greutateDubla extends Greutate{
    private Greutate g1;
    private Greutate g2;

    //facem constructorul:
    public greutateDubla(Greutate g1, Greutate g2){
        this.g1=g1;
        this.g2=g2;
    }

    public void setGreutate1(Greutate g){
        g1=g;
    }

    public void setGreutate2(Greutate g){
        g2=g;
    }

    //override
    public int capacitate(){
        int suma_totala = 0;
        suma_totala = suma_totala + g1.capacitate() + g2.capacitate();
        return suma_totala;
    }
}

class greutatiMultiple extends Greutate{
    private Greutate[] greutati;

    //facem constructorul:
    public greutatiMultiple(Greutate... greutati){
        // ... (varargs syntax) let you pass any number of 'Greutate' objects when creating an instance of 'GreutateMultipla'.
        // You can pass zero or more 'Greutate' objects, and they will be treated as an array inside the method.
        this.greutati = greutati;
    }

    public int capacitate(){
        int suma_totala = 0;
        int i;
        for(i=0; i<greutati.length; i++){
            suma_totala = suma_totala + greutati[i].capacitate();
        }
        return suma_totala;
    }
}

class ColectieGreutati{
    private Greutate[] greutati;
    private int capacitateMaxima;
    private int index;

    //facem constructorul:
    public ColectieGreutati(int capacitateMaxima){
        this.capacitateMaxima = capacitateMaxima;
        this.greutati = new Greutate[capacitateMaxima];
        index =0;
    }

    public void adauga(Greutate g){
        if(index<greutati.length){
            greutati[index++] = g;
        }
        else{
            System.out.println("Nu mai este loc pentru a aduga alta greutate!");
        }
    }

    //sau:
   /* public void adauga(Greutate g){
        boolean flag = false;
        int i;
        for(i=0; i<greutati.length; i++){
            if(greutati[i] == null){
                //aici verific daca ma aflu la inceputul vectorului, pentru a adauga noua mea greutate.
                //daca greutati[i] este diferit de null, inseamna ca am deja o greutate la acea adresa, deci nu vreau sa suprascriu
                //peste greutatea veche pentru a o introduce pe cea noua, asa ca verific sa nu am nimic(null) la momentul adaugarii
                greutati[i] = g;
                flag=true;
                break;
            }
        }
        if(!flag){
            System.out.println("Nu mai este loc pentru a aduga alta greutate!");
        }
    }
    */

    public double medie(){
        int suma=0;
        double medie;
        int i;
        if (greutati.length == 0)
        {
            return 0; // To avoid division by zero
        }

        for(i=0; i<greutati.length; i++){
            //aici la lab mi-a dat eroare si a mers cand am scris in loc de greutati.length, index...investigheaza!!
            if(greutati[i] != null){
                suma = suma + greutati[i].capacitate();
            }
        }
        medie = (double) suma/greutati.length;
        return medie;
    }
}

public class Main {
    public static void main(String[] args) {
        ColectieGreutati colectie = new ColectieGreutati(10);

        Greutate greutate1 = new GreutateSimpla(5);
        Greutate greutate2 = new GreutateSimpla(8);

        greutateDubla greutateDubla = new greutateDubla(greutate1, greutate2);

        Greutate greutate3 = new GreutateSimpla(10);
        Greutate greutate4 = new GreutateSimpla(7);

        greutatiMultiple greutateMultipla = new greutatiMultiple(greutate3, greutate4);

        colectie.adauga(greutate1);
        colectie.adauga(greutateDubla);
        colectie.adauga(greutateMultipla);

        System.out.println("Greutatea medie a colectiei: " + colectie.medie());
    }
}



/*
Se cere sa se modeleze o garnitura de tren. Se va defini in acest scop o clasa Tren.
Un obiect de tip Tren contine mai multe referinte spre obiecte de tip Vagon care
sunt pastrate intr-un tablou. Un vagon poate fi de 3 tipuri: CalatoriA, CalatoriB
si Marfa. Despre garnitura de tren si vagoane mai cunoastem urmatoarele:
• un tren poate contine maxim 15 vagoane, indiferent de tipul vagoanelor. Vagoanele
sunt atasate trenului la crearea lui.
• un vagon de tip CalatoriA
– are capacitatea de 40 pasageri si 300 colete postale.
– furnizeaza doua servicii pentru deschiderea, respectiv inchiderea automata
a usilor.
• un vagon de tip CalatoriB
– are capacitatea de 50 pasageri si 400 colete postale.
– furnizeaza doua servicii pentru deschiderea, respectiv inchiderea automata
a usilor.
– fiind un vagon mai nou, furnizeaza un serviciu automat pentru blocarea
geamurilor.
• un vagon de tip Marfa
– are capacitatea de 400 colete po¸stale.
– nu furnizeaza servicii pentru deschiderea, respectiv inchiderea automata a
usilor, aceste operatii executandu-se manual. Atentie: se interzice existenta
metodelor pentru deschiderea, respectiv inchiderea automata a usilor in
clasa ce modeleaza acest tip de vogon.
Se cere:
• sa se construiasca diagrama UML pentru clasele identificate pe baza descrierii
anterioare.
• sa se implementeze clasa care modeleaza conceptul de vagon impreuna cu eventualele sale clase derivate. Se mentioneaza ca apelurile serviciilor pentru deschiderea, respectiv
inchiderea usilor, blocarea geamurilor vor afisa pe ecran
un mesaj corespunzator, spre exemplu, apelul serviciului pentru blocarea geamurilor ar putea tipari “Geamurile s-au blocat”. Implementarea se va face
astfel incat valorile asociate numarului de pasageri, colete sa nu fie stocate in diverse campuri ale vagoanelor.
 */

 class Vagon{

     public int getNumarColete(){
         return 0;
     }
     public void deschidereUsi(){
         System.out.println("Usile s-au deschis!");
     }

     public void inchidereUsi(){
         System.out.println("Usile s-au inchis!");
     }

     public String getTipVagon(){
         return "Vagon normal";
     }
 }

 class CalatoriA extends Vagon{
     private int numarPasageri =40;
     private int numarColete = 300;
     //nu folosesc constructori fiindca valorile date de numarul de colete/pasageri e mereu constant (in functie de tipul de vagon)
 }

 class CalatoriB extends Vagon{
    private int numarPasageri = 50;
    private int numarColete = 400;

     public void inchidereGeamuri(){
         System.out.println("Geamurile s-au blocat!");
     }

     public int getNumarColete(){
         return numarColete;
     }

     //suprascriere
     public String getTipVagon(){
         return "Vagon Calatori B";
     }
 }

 class Marfa extends Vagon{
    private int numarColete = 400;

    public String getTipVagon(){
        return "Vagon Marfa";
    }

    public int getNumarColete(){
        return numarColete;
    }
 }


// clasa Tren care contine o colectie de vagoane
class Tren{
    private Vagon[] vagoane;
    private int numarVagoane;

    //facem constructorul:
    public Tren(){
        this.vagoane = new Vagon[15];
        numarVagoane = 0;
    }

    public void adaugaVagon(Vagon vagon){
        if(numarVagoane < vagoane.length){
            //inseamna ca mai avem loc pentru a adauga inca un vagon:
            vagoane[numarVagoane++] = vagon;
        }
        else {
            System.out.println("Trenul este plin! Nu se mai pot adauga alte vagoane!");
        }
    }

    public void afiseazaVagoane(){
        int i;
        System.out.println("Tipurile de vagoane din tren sunt: ");
        for(i=0; i<vagoane.length; i++){
            if(vagoane[i] != null){
                System.out.println(vagoane[i].getTipVagon());
            }
        }
    }

    public boolean equals(Tren tren){
        int i;
        int numarColeteTren1 = 0;
        int numarColeteTren2 = 0;

        //calculam numarul de colete pentru trenul dat spre verificare ca parametru:
        for(i=0; i<tren.numarVagoane; i++){
            numarColeteTren1 = numarColeteTren1 + tren.vagoane[i].getNumarColete();
        }

        //calculam numarul de colete pentru trenul nostru creeat anterior
        for(i=0; i<numarVagoane; i++){
            numarColeteTren2 = numarColeteTren2 + vagoane[i].getNumarColete();
        }

        if(numarColeteTren1 == numarColeteTren2){
            return true;
        }
        else {
             return false;
        }
    }
}

class Main{
     public static void main(String[] args){
         Tren tren1 = new Tren();

         Vagon calatoriA1 = new CalatoriA();
         Vagon calatoriB1 = new CalatoriB();
         Vagon marfa1 = new Marfa();

         tren1.adaugaVagon(calatoriA1);
         tren1.adaugaVagon(calatoriB1);
         tren1.adaugaVagon(marfa1);

         Tren tren2 = new Tren();

         Vagon calatoriA2 = new CalatoriA();
         Vagon calatoriB2 = new CalatoriB();
         Vagon marfa2 = new Marfa();

         tren2.adaugaVagon(calatoriA2);
         tren2.adaugaVagon(calatoriB2);
         tren2.adaugaVagon(marfa2);

         //testare metoda equals
         System.out.println("Cele 2 trenuri sunt egale: " + tren1.equals(tren2));

         //testare metoda afiseaza tipuri vagoane
         tren1.afiseazaVagoane();

     }
}
