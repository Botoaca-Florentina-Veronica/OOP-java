/*
    Intr-un program ce simuleaza un joc avem doua clase: Echipa si Jucator. O echipa e formata din juacatorii titulari si din jucatori rererve.
Acestia vor fi memorati in starea unei ectipe prin intermeduil a doua tablouri de referinte la obiecte jacator ce vor fi setate prin intermediul unui constructor
(obs: se poate considera ca se dau tot timpul tablouri pline constructorului.
    Un jucator va avea doua atribute, un sir de caractere si un intreg care vor reprezenta numele respectiv numnarul de pe
    tricou al jucatorului. Aceste caracteristici vor fi setate in momentul in care se creeaza un nou obiect jucator.
    Se pot adauga acestor clase si alte elemente daca se considera necesar.

    Asupra unei echipe se pot efectua urmatoarele operatii:
        * efoctucazaSchimbare: care primeste ca parametru doua referinte la obiecte jucator, un titular si o rezerva.
    Metoda va verifica ca intradevar titularul se afa in tabloul de titulari iar rezerva in tabloul de rezerve, caz in care acestia vor fi
    mutati intre ei (adica pe pozitia jucatorului inlocuit trece rezerva iar pe pozitia rezervei trece jucatorul inlocuit) iar metoda va retura true.
    In cat contrar, metoda va intoarce false. Doua obiecte jucator vor fi comparate din punctul de vedere al continutului/starii, doua
    obiecte jucator find considerate egale daca au acelasi nume si acelasi numar pe tricou.

        * o operatie corespunzatoare pentru reprezentarea sub forma de sir de caractere a unei echipe, sub forma: "Titulari: <Ti>; Rezerve <Re>" unde
         Ti/Re este reprezentarea sub forma de sir de caractere a jucator, sub forma "(<Nume>, <Numar tricou>)".
     Se cere crearea unei clase Main in care sa se exemplifice functionalitatile definite anterior.
      Se vor creea cativa jucatori titulari si cativa jucatori rezerve, se va creea o echipa cu acestia, se va afisa echipa, se va efectua o schimbare iar apoi se va afisa din nou echipa.
 */

class Echipa{
    private Jucator[] titulari;
    private Jucator[] rezerve;

    //facem constructorul:
    public Echipa(Jucator[] rezerve,Jucator[] titulari)
    {
        this.rezerve=rezerve;
        this.titulari=titulari;
    }
    public boolean EfectueazaSchimbare(Jucator r, Jucator t)
    {
        int ok1=0, ok2=0;
        int i;
        Jucator aux;
        Jucator x = new Jucator(" ",10);
        Jucator y = new Jucator(" ",20);

        for(i=0; i<this.titulari.length; i++)
        {
            if((t.getNume().equals(titulari[i].getNume()))&&(t.getNumar() == titulari[i].getNumar()))
            {
                //verificam daca titularul se afla in tabloul de titulari
                x = new Jucator(titulari[i].getNume(), titulari[i].getNumar());
                //sau mai simplu x = this.titular[i];
                ok1++;
                break;
            }
        }
        for(i=0; i<this.rezerve.length; i++)
        {
            if((r.getNume().equals(rezerve[i].getNume()))&&(r.getNumar() == rezerve[i].getNumar()))
            {
                y = new Jucator(rezerve[i].getNume(), rezerve[i].getNumar());
                //si aici puteam scrie y = this.rezerve[i]
                ok2++;
                break;
            }
        }
        if(ok1+ok2==2)
        {
            //facem swap daca si titularul si rezerva se afla in tablourile aferente fiecaruia
            aux=x;
            x=y;
            y=aux;
            //acum x->rezerve
            //     y->titulari

            for(i=0 ; i<this.titulari.length;i++)
            {
                if((t.getNume().equals(titulari[i].getNume()))&&(t.getNumar() == titulari[i].getNumar()))
                {
                    titulari[i]=x;
                    break;
                }
            }
            for(i=0 ; i<this.rezerve.length;i++)
            {
                if((r.getNume().equals(rezerve[i].getNume()))&&(r.getNumar()== rezerve[i].getNumar()))
                {
                    rezerve[i]=y;
                    break;
                }
            }
            return true;
        }
        return false;
    }


    public String toString()
    {
        String t,r;
        int i;
        
        t = "Titularii sunt: ";
        for(i=0; i<this.titulari.length;i++) {
            t=t+" "+ titulari[i].getNume();
        }
        
        r = "Rezervele sunt: ";
        for(i=0; i<this.rezerve.length;i++) {
            r=r+" "+ rezerve[i].getNume();
        }
        return t+"\n"+r;
    }
}

class Jucator{
    private String nume;
    private int numar;

    public Jucator(String nume,int numar)
    {
        this.nume=nume;
        this.numar=numar;
    }
    public String getNume()
    {
        return nume;
    }
    public int getNumar()
    {
        return numar;
    }
}


class Echipele {
    public static void main(String args[]) {
        Jucator[] titularii = new Jucator[] {new Jucator("Vera",10),new Jucator("Anca",20)};
        Jucator[] rezervele = new Jucator[] {new Jucator("Sandra",10),new Jucator("Adina",20)};


        Echipa e1=new Echipa(titularii,rezervele);
        Jucator j1=new Jucator("Vera",10);
        Jucator j2=new Jucator("Sandra",10);

        System.out.println(e1);
        System.out.println(e1.EfectueazaSchimbare(j1,j2));
        System.out.println(e1);
    }
}
