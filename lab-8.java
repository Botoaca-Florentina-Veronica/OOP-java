
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

class Greutate{
    private int capacitate;
    public Greutate(int capacitate){
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
        super(0);
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
        super(0);
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

        Greutate greutate1 = new Greutate(5);
        Greutate greutate2 = new Greutate(8);

        greutateDubla greutateDubla = new greutateDubla(greutate1, greutate2);

        Greutate greutate3 = new Greutate(10);
        Greutate greutate4 = new Greutate(7);

        greutatiMultiple greutateMultipla = new greutatiMultiple(greutate3, greutate4);

        colectie.adauga(greutate1);
        colectie.adauga(greutateDubla);
        colectie.adauga(greutateMultipla);

        System.out.println("Greutatea medie a colectiei: " + colectie.medie());
    }
}
