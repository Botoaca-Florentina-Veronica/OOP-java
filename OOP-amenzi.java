/*
Politia comunitara acorda diferite tipuri de amenzi pentru urmatoarele categorii de
fapte, dupa cum urmeaza:
• parcarea ilegala a masinii – amenda fixa 500 RON
• aruncatul gunoaielor pe jos – amenda 200 RON * factorZonal, unde factorZonal
este un coeficient cuprins intre 1-4, corespunzator zonei in care s-a produs fapta
• distrugerea bunurilor din parcuri – amenda 300 RON * valoarea bunului

Fiecare tip de amenda are cel putin urmatoarele caracteristici:
• contine codul numeric personal (CNP) al cetateanului caruia a fost aplicata
• are o metoda ce returneaza valoarea amenzii
• poate fi afisata sub urmatoarea forma: CNP Tip Amenda, Valoare. Tip
Amenda desemneaza cauza pentru care amenda a fost acordata iar Valoare
reprezinta suma care trebuie incasata de la cetatean.

In cadrul sistemului folosit exista o clasa pentru gestiunea tuturor amenzilor acordate.
Aceasta clasa contine:
• o colectie de obiecte pentru stocarea tuturor amenzilor acordate
• o metoda pentru introducerea unei noi amenzi (de orice tip)
• o metoda public int valoare(Strategie strategie), unde Strategie e o interfata
ce are metoda public boolean conditie(Amenda amenda). Aceasta metoda
(int valoare(Strategie strategie)) returneaza suma amenzilor corespunzatoare
elementelor din colectie ce satisfac proprietatea definita de strategie. Concret,
parcurge toate elementele colectiei si pentru fiecare element apeleaza metoda
conditie a strategiei (evident, avand ca parametru elementul curent (amenda
curenta)) si daca aceasta returneaza true, adauga valoarea amenzii la suma ce
va fi returnata.
Se cer:
• Sa se implementeze conform cerintelor ierarhia de amenzi precum si clasa ce
gestioneaza amenzile acordate.
• Sa se implementeze interfata Strategie de catre diferite clase, astfel incat sa
existe:
– o clasa ce primeste printr-un constructor un String corespuzator unui cod
numeric personal. In acest caz metoda public boolean conditie(Amenda
amenda) returneaza true daca codul numeric personal primit ca parametru
prin constructor este egal cu cel stocat in cadrul obiectului tip Amenda
referit de catre parametru.
– o clasa pentru care metoda public boolean conditie(Amenda amenda) returneaza true
daca amenda a fost generata dotorita nerespectarii semnului
Parcarea Interzisa.
• Sa se exemplifice printr-o metoda main functionarea sistemului. Se vor crea
diferite tipuri de amenzi (cel putin cate una de fiecare tip) si se va returna suma
amenzilor datorate de cetateanul ce are codul numeric personal 1031274456709.
• Sa se explice care sunt avantajele folosirii polimorfismului in cadrul acestei
aplicatii.*/

import java.util.List;
import java.util.ArrayList;

abstract class Amenda{
    public String CNP;
    public abstract int getValoareAmenda();
    public abstract String toString();
}

class ParcareIlegala extends Amenda{
    private  int valoareAmenda;

    //facem constructorul:
    public ParcareIlegala(String CNP){
        this.CNP = CNP;
        this.valoareAmenda = 500;
    }

    @Override
    public int getValoareAmenda(){
        return valoareAmenda;
    }

    @Override
    public String toString(){
        return "CNP: " + CNP + ", tip amenda: parcare ilegala, valoare amenda: " + this.valoareAmenda;
    }
}

class AruncatGunoaie extends Amenda{
    private int valoareAmenda;
    private int factorZonal;

    //facem constructorul:
    public AruncatGunoaie(String CNP, int factorZonal){
        this.CNP = CNP;
        this.valoareAmenda = 200;
        this.factorZonal = factorZonal;
    }

    @Override
    public int getValoareAmenda(){
        return this.valoareAmenda*factorZonal;
    }

    @Override
    public String toString(){
        return "CNP: " + this.CNP + ", tip amenda: aruncatul de gunoaie pe jos, valoare amenda: " + getValoareAmenda();
    }
}


class DistrugereaBunurilor extends Amenda{
    private static int valoareAmenda;
    private static int valoareBun;

    //facem constructorul:
    public DistrugereaBunurilor(String CNP, int valoareBun){
        this.CNP = CNP;
        valoareAmenda = 300;
        this.valoareBun = valoareBun;
    }

    @Override
    public int getValoareAmenda(){
        return this.valoareAmenda * valoareBun;
    }

    @Override
    public String toString(){
        return "CNP: " + this.CNP + ", tip amenda: distrugera bunurilor, valoare amenda: " + getValoareAmenda();
    }
}


interface Strategie{
    public boolean conditie(Amenda amenda);  //public e optional
}

class Gestiune{
    private List<Amenda> amenzi = new ArrayList<>();

    //creeam o metoda pentru introducerea unei noi amenzi:
    public void introducereAmenda(Amenda amenda){
        amenzi.add(amenda);
    }

    public int valoare(Strategie strategie){
        int i, suma = 0;
        for(i=0; i<amenzi.size(); i++)
        {
            if(strategie.conditie(amenzi.get(i)))
            {
                suma = suma + amenzi.get(i).getValoareAmenda();
            }
        }
        return suma;
    }
}

class CNPstrategie implements Strategie{
    private String CNP;
    //facem constructorul:
    public CNPstrategie(String CNP){
        this.CNP = CNP;
    }

    @Override
    public boolean conditie(Amenda amenda){
        if(CNP==amenda.CNP)
        {
            return true;
        }
        return false;
    }
}

class SemnParcareStrategie implements Strategie{
    @Override
    public boolean conditie(Amenda amenda){
        return true;
    }
}

class Main {
    public static void main(String[] args) {
        Gestiune gestiune = new Gestiune();

        Amenda amenda1 = new ParcareIlegala("1031274456709");
        Amenda amenda2 = new AruncatGunoaie("1031274456709", 2);
        Amenda amenda3 = new DistrugereaBunurilor("1031274456709", 5);

        gestiune.introducereAmenda(amenda1);
        gestiune.introducereAmenda(amenda2);
        gestiune.introducereAmenda(amenda3);

        Strategie cnpStrategie = new CNPstrategie("1031274456709");
        Strategie semnParcareStrategie = new SemnParcareStrategie();

        int sumaAmenziCNP = gestiune.valoare(cnpStrategie);
        int sumaAmenziSemnParcare = gestiune.valoare(semnParcareStrategie);

        System.out.println("Suma amenzilor pentru CNP 1031274456709: " + sumaAmenziCNP);
        System.out.println("Suma amenzilor pentru nerespectarea semnului de parcare: " + sumaAmenziSemnParcare);
    }
}
