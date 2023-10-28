/*
Într-un program de gestiune avem douä clase: Benzinärie si Masinä. O Masinä e caracterizatä de numärul ei de înmatriculare (String) si cantitatea de benzinä în litrii (int) care fi este necesarä pentru alimentare. Aceste caracteristici sunt setate prin constructor.
 Clasa poate sä continã si alte elemente considerate necesare.
        O Benzinärie e caracterizatä de cantitatea de benzinä disponibilä în litrii (int).
        Fiecare benzinärie mai contine un tablou de referinte la obiecte Masinã (eventual si un contor dacã se considerä necesar), reprezentand masinile care asteaptä la coadä pentru a fi alimentate.
        Dimensiunea maximã a cozii este de 10 masini.
        Pe o benzinärie putem efectua urmatoarele operatii:
  *alimenteazaMasina: ce primeste ca parametru o referintä la un obiect masinä. Daca existä suficientä benzinä în benzinarie pentru a alimenta acea masina complet, se actualizeaza corespunzator cantitatea de benzinä din benzinarie si metoda întoarce valoarea true.
  Dacã nu existã suficienta benzinä în benzinarie pentru a alimenta total acea masinä, se încearcã amplasarea masinii în tabloul de asteptare. Daca mai este loc, masina e pusa în coada de asteptare iar metoda întoarce valoarea true.
  Altfel,alimentarea masinii e abandonatä iar metoda întoarce valoarea false.
        O operatie corespunzatoare care intoarce reprezentarea sub formä de sir de caractere a benzinäriei sub forma "Coada
 benzinarie: Masina1 Masina2... MasinaN" unde MasinaX este reprezentarea sub forma de sir de caractere a masinii ce asteaptä la coadä pe pozitia X la benzinarie, iar N corespunde numarului de masini ce stau la coadä.
        O masina e reprezentatä sub forma"Numãr înmatriculare - Cantitate necesarã".
   *alimenteazaenzinarie: ce primeste ca parametru o cantitate de combustibil ce e adaugat la cantitatea de benzinä curentä din benzinãrie. Apoi, dacä exista masini la coada, metoda alimenteazä masinile in ordinea în care ele stau la coadä.
   Procedura se opreste la prima masina care nu mai poate fi alimentata complet sau când nu mai sunt masini în coadä. Masinile alimentate se eliminä din coada de asteptare iar cele nealimentate ramân la coadä.
     Se va implementa si o metoda main unde se va crea o benzinarie si 3 masini cu numere de înmatriculare la alegere si având un necesar de combustibil de 5, 10 respectiv 15 litrii.
        Se va apela metoda alimenteazaMasinä pe benzinarie avand ca parametru masinile create anterior (în ordinea indicatã a necesarului de combustibil de mai sus). Se tipareste la iesirea standard benzinäria.
        Apoi se alimenteza benzinaria cu 15 litri de combustibil si apoi se tipareste iar la iesirea standard benzinäria.
*/



class Masina{
    private String nrInmatriculare;
    private int capacitate = 0;

    //facem constructorul
    public Masina(int capacitate, String nrInmatriculare){
        this.capacitate = capacitate;
        this.nrInmatriculare = nrInmatriculare;
    }

    public int getCapacitate(){
        return capacitate;
    }

    public String getNrInmatriculare(){
        return nrInmatriculare;
    }
}

class Benzinarie{
    private int cantitateDisponibila = 0;
    private static int count = 0;  //numarul de masini aflate la coada
    private Masina[] coada = new Masina[10];


    public Benzinarie(int cantitateDisponibila)
    {
        this.cantitateDisponibila = cantitateDisponibila;
    }
    public int getCantitateDisponibila(){
        return cantitateDisponibila;
    }
    public boolean alimenteazaMasina(Masina masina){
        if(cantitateDisponibila >= masina.getCapacitate() )
        {
            cantitateDisponibila = cantitateDisponibila - masina.getCapacitate();
            return true;
        }
        else if(count<10)
        {
            //daca ajung aici inseamna ca nu mai exista suficienta benzina in benzinarie, deci recurg la coada de asteptare
            //si imi adaug masina primita ca parametru in aceasta coada
            coada[count++] = masina;
        }
        return false;  //daca ajung aici inseamna ca nu mai e loc nici la coada de asteptare
    }

    public String toString(){
        String rezultat = "Coada benzinarie: " + "\n";
        int i;
        for(i=0; i<count; i++)
        {
            //ma plimb cu idexul i pana la finalul cozii
            rezultat = rezultat + "Masina: " + (i+1) + "-numar inmatriculare: " + coada[i].getNrInmatriculare() + " -cantitate necesara "
             + coada[i].getCapacitate();
            if(i<count-1)
            {
                rezultat = rezultat + "," + "\n";
            }
        }
        return rezultat;
    }


    public void alimenteazaBenzinarie(int cantitateDeAdaugat) {
        cantitateDisponibila = cantitateDisponibila + cantitateDeAdaugat;
        int i = 0;
        //dupa ce am primit noua cantitate de combustibil, vreau acum sa il distribui din nou
        //masinilor de la coada, in ordinea acestora la rand si in limita stocului disponibil
        for(i=0; i<count; i++){
            if (cantitateDisponibila >= coada[i].getCapacitate())
            {
                cantitateDisponibila = cantitateDisponibila - coada[i].getCapacitate();
            }
        }
        // Eliminăm mașinile alimentate din coada de așteptare
        int j;
        if (i > 0)
        {
            for (j = i; j<count; j++)
            {
                coada[j - i] = coada[j];
            }
            for (j = count - i; j<count; j++)
            {
                coada[j] = null;
            }
            count -= i;
        } // mai lucreaza la functia asta!!
    }
}

class Main{
    public static void main(String[] args){
        Benzinarie benzinarie = new Benzinarie(5);

        Masina mercedes = new Masina(5, "21VSB45");
        Masina audi = new Masina(10 , "34GTR67");
        Masina bmw = new Masina(15, "69GHT21");

        benzinarie.alimenteazaMasina(mercedes);
        benzinarie.alimenteazaMasina(audi);
        benzinarie.alimenteazaMasina(bmw);
        System.out.println(benzinarie);
        System.out.println("Combustibil ramas: " + benzinarie.getCantitateDisponibila() + " litri" + "\n");

        benzinarie.alimenteazaBenzinarie(15);
        System.out.println(benzinarie);
        System.out.println("Combustibil ramas: " + benzinarie.getCantitateDisponibila()  + " litri" + "\n");
    }
}
