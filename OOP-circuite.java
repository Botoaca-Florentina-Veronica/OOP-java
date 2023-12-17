/*În cadrul acestei probleme se vor proiecta clasele necesare asamblării circuitelor electrice
formate strict din rezistori împreună cu calcularea anumitor proprietăți ale acestora.

Orice CircuitElectric este caracterizat de următoarele operații:
* rezistențăEchivalentă(): double- determină rezistența echivalentă a circuitului
* curent(double tensiune): double- determină curentul ce trece prin circuit după legea lui Ohm
* conțineSubcircuit(CircuitElectric subcircuit): boolean - verifică dacă circuitul dat ca argument
este inclus ca parte din circuitul electric pe care se apelează metoda

CircuiteleElectrice concrete suntȘ

* Rezistență - este caracterizată de valoarea în Ohmi(double) a rezistenței, care se setează
la crearea obiectului. Rezistența echivalentă a unei rezistențe este direct această valoare.
Prin definiție, orice rezistență se conține pe ea însăși.

* CircuitSerie - o secvență de oricâte circuite electrice de orice fel, setate la crearea
obiectului, în acest caz considerate a fi legate în serie. Rezistența echivalentă a unui circuit
serie este suma rezistențelor echivalente ale sub-circuitelor conținute. Prin definiție, orice
circuit serie se conține pe el însuși. Adițional, un circuit serie conține alt circuit dacă unul
din circuitele înseriate conține acel circuit.

* CircuitParalel - o secvență de oricâte circuite electrice de orice fel, setate la crearea obiectului, în acest caz considerate
a fi legate în paralel. Rezistența echivalentă a unui circuit paralel se calculează conform:
Re = 1 / [(1/R1) + (1/R2) + ... + (1/Rn)]. Prin definiție, orice circuit paralel se conține pe
el însuși. Adițional, un circuit paralel conține alt circuit dacă unul din circuitele
paralelizate conține acel circuit.

Implementați și o clasă ce conține metoda main în care se construiește circuitul din figura
alăturată. Afișați pe ecran rezistența echivalentă a întregului circuit, curentul ce trece prin
circuit pentru o tensiune de 9V, și rezultatul căutării în circuit a rezistenței R1(circuitului
aferent acesteia).

             R1=2ohm        R2=1ohm
      ________________________________
-----|       |_____|      |_____|     |--------
     |       _____      _____         |
     |______|_____|____|_____|________|

            R3=4ohm     R4=2ohm

 */

abstract class CircuitElectric{
    public abstract double rezistentaEchivalenta();
    public abstract double curent(double tensiune);
    public abstract boolean contineSubCircuit(CircuitElectric subCircuit);
}


class Rezistenta extends CircuitElectric{
    private double valoareRezistenta;

    //facem constructorul:
    public Rezistenta(double valoareRezistenta){
        this.valoareRezistenta = valoareRezistenta;
    }

    @Override
    public double rezistentaEchivalenta(){
        return valoareRezistenta;
    }

    @Override
    public double curent(double tensiune){
        return tensiune/valoareRezistenta;
    }

    @Override
    public boolean contineSubCircuit(CircuitElectric subCircuit){
        return true;
    }
}

class CircuitSerie extends CircuitElectric{
    private CircuitElectric[] circuiteSerie;

    //facem constructorul:
    public CircuitSerie(CircuitElectric[] circuiteSerie){
        this.circuiteSerie = circuiteSerie;
    }

    @Override
    public double rezistentaEchivalenta(){
        int i;
        double rezultat = 0;
        for(i=0; i<circuiteSerie.length; i++)
        {
            rezultat = rezultat + circuiteSerie[i].rezistentaEchivalenta();
        }
        return rezultat;
    }

    @Override
    public double curent(double tensiune){
        return tensiune/rezistentaEchivalenta();
    }

    @Override
    public boolean contineSubCircuit(CircuitElectric subCircuit){
        int i;
        for(i=0; i<circuiteSerie.length; i++)
        {
            if(circuiteSerie[i]==subCircuit)
            {
                return true;
            }
        }
        return false;
    }
}

class CircuitParalel extends CircuitElectric{
    private CircuitElectric[] circuiteParalele;

    //facem constructorul:
    public CircuitParalel(CircuitElectric[] circuiteParalele){
        this.circuiteParalele = circuiteParalele;
    }

    @Override
    public double rezistentaEchivalenta(){
        int i;
        double rezultat = 0;
        for(i=0; i<circuiteParalele.length; i++)
        {
            rezultat = rezultat + 1/circuiteParalele[i].rezistentaEchivalenta();
        }
        return 1/rezultat;
    }

    @Override
    public double curent(double tensiune){
        return tensiune/rezistentaEchivalenta();  //se va utiliza metoda rezistentaEchivalenta() din aceasta clasa!
    }


    @Override
    public boolean contineSubCircuit(CircuitElectric subCircuit){
        int i;
        for(i=0; i<circuiteParalele.length; i++)
        {
            if(circuiteParalele[i] == subCircuit)
            {
                return true;
            }
        }
        return false;
    }
}

class Main{
    public static void main(String[] args){
        Rezistenta R1 = new Rezistenta(2);
        Rezistenta R2 = new Rezistenta(1);
        Rezistenta R3 = new Rezistenta(4);
        Rezistenta R4 = new Rezistenta(2);

        CircuitSerie serie1 = new CircuitSerie(new CircuitElectric[]{R1, R2});
        CircuitSerie serie2 = new CircuitSerie(new CircuitElectric[]{R3, R4});

        CircuitParalel paralel = new CircuitParalel(new CircuitElectric[]{serie1, serie2});
        //acesta este de asemenea circuitul principal care contine toate circuitele

        // Afișarea rezultatelor
        System.out.println("Rezistența echivalentă a întregului circuit: " + paralel.rezistentaEchivalenta() + " ohmi");
        System.out.println("Curentul pentru o tensiune de 9V: " + paralel.curent(9) + " A");
        System.out.println("Circuitul paralel alt circuit paralel  R1: " + paralel.contineSubCircuit(R1));
        System.out.println("Circuitul serie alt circuit serie  R1: " + serie1.contineSubCircuit(R1));
    }
}
