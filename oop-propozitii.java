/*
În cadrul acestei probleme se vor proiecta clasele necesare definirii de propoziții în logica propozițională.
   Orice Propoziție este caracterizată de operația fărăImplicații(): Propoziție, care construiește o altă Propoziție având
grijă să se elimine utilizarea operatorilor/conectorilor de implicație logică.

   *PropozițieAtomică: este cea mai granulară propoziție. O propoziție atomică este caracterizată
de un nume(String), pe care îl primește la crearea obiectului și îl memorează în starea sa.
Operația fărăImplicații întoarce o referință către un nou obiect PropozițieAtomică cu același
nume. Obiectul mai deține și o operație pentru reprezentarea lui ca șir de caractere  sub forma
"numele_propoziției_atomice".
   *Negație: o propoziție ce reprezintă negația unei alte propoziții de orice alt fel. Propoziția
negată este dată ca argument la crearea unui astfel de obiect și memorată în starea obiectului
negație. Operația fărăImplicații elimină implicațiile din propoziția conținută de obiectul
pe care s-a apelat metoda(apelând metoda fărăImplicații), după care întoarce referință la un
nou obiect Negație care va conține propoziția obținută astfel. Reprezentarea sub formă de șir
de caractere pentru un obiect negație are forma "!reprezentarea_sub_forma_de_șir_a_propoziției_negate".

   *SauMultiplu:este o propoziție reprezentând o disjuncție/sau logic între mai multe alte
propoziții de orice fel. Această secvență de propoziții este dată la crearea obiectului SauMultiplu
și e memorată în starea sa. Operația fărăImplicații obține pentru fiecare propoziție conținută
propoziția echivalentă din care au fost eliminate implicațiile (apelând metoda fărăImplicații)
și introduce rezultatul într-un nou obiect SauMultiplu. În final, se returnează referință
la acest nou obiect creat. Reprezentarea sub formă de șir de caractere pentru un obiect
SauMultiplu are forma "(Prop1 | Prop2| ...|PropN)" unde PropX este reprezentarea ca șir de
caractere a propoziției X iar N este numărul de propoziții conținute de respectivul obiect
SauMultiplu.
   *Implicație: o propoziție ce reprezintă implicația logică între două propoziții de orice alt
fel, specificate la crearea unui obiect Implicație și memorate intern de acesta. Operația
fărăImplicații elimină implicațiile din propozițiile conținute (folosind tot metoda fărăImplicații)
obținând propozițiile tmpA și tmpB. Apoi construiește un obiect SauMultiplu în care introduce
negata lui tmpA și apoi îl introduce pe tmpB (notă: tmpA->tmpB este echivalent cu !tmpA|tmpB).
Reprezentarea sub formă de șir de caractere pentru un obiect Implicație are forma "(Prop1->Prop2)"
unde Prop1 respectiv Prop2 sunt reprezentările sub formă de șir de caractere pentru propozițiile
conținute de obiectul implicație.

Implementați o clasă ce conține metoda main în care se va crea propoziția a cărei reprezentate
sub formă de șir de caractere va fi "(A | !(B->C))". Se tipărește la ieșirea standard expresia
iar apoi se tipărește expresia construită în urma eliminării implicațiilor din prima expresie.

*/

abstract class Propozitie {
    public abstract Propozitie faraImplicatii();

    @Override
    public abstract String toString();
}

class PropozitieAtomica extends Propozitie {
    private String nume;

    public PropozitieAtomica(String nume) {
        this.nume = nume;
    }

    @Override
    public Propozitie faraImplicatii() {
        return new PropozitieAtomica(nume);
    }

    @Override
    public String toString() {
        return nume;
    }
}

class Negatie extends Propozitie {
    private Propozitie propozitieNegata;

    public Negatie(Propozitie propozitieNegata) {
        this.propozitieNegata = propozitieNegata;
    }

    @Override
    public Propozitie faraImplicatii() {
        return new Negatie(propozitieNegata.faraImplicatii());
    }

    @Override
    public String toString() {
        return "!" + propozitieNegata.toString();
    }
}

class SauMultiplu extends Propozitie {
    private Propozitie[] propozitii;

    public SauMultiplu(Propozitie[] propozitii) {
        this.propozitii = propozitii;
    }

    @Override
    public Propozitie faraImplicatii() {
        Propozitie[] propozitiiFaraImplicatii = new Propozitie[propozitii.length];
        int i;
        for (i=0; i<propozitii.length; i++) {
            propozitiiFaraImplicatii[i] = propozitii[i].faraImplicatii();
        }
        return new SauMultiplu(propozitiiFaraImplicatii);
    }


    @Override
    public String toString() {
        String result = "(";
        int i;
        for (i=0; i<propozitii.length; i++) 
        {
            result =result + propozitii[i].toString();
            if (i < propozitii.length - 1) 
            {
                //printam | pana la pelultimul rezultat, nu vreau si la finalul lui
                result = result + "|";
            }
        }
        result = result + ")";
        return result;
    }
}

class Implicatie extends Propozitie {
    private Propozitie propozitie1;
    private Propozitie propozitie2;

    public Implicatie(Propozitie propozitie1, Propozitie propozitie2) {
        this.propozitie1 = propozitie1;
        this.propozitie2 = propozitie2;
    }

    @Override
    public Propozitie faraImplicatii() {
        Propozitie tmpA = propozitie1.faraImplicatii();
        Propozitie tmpB = propozitie2.faraImplicatii();
        Negatie negatieTmpA = new Negatie(tmpA);
        return new SauMultiplu(new Propozitie[]{negatieTmpA, tmpB});
    }

    @Override
    public String toString() {
        return "(" + propozitie1.toString() + "->" + propozitie2.toString() + ")";
    }
}

class Main {
    public static void main(String[] args) {
        // Crearea propozitiei initiale
        Propozitie propInitiala = new SauMultiplu(new Propozitie[]{
                new PropozitieAtomica("A"),
                new Negatie(new Implicatie(new PropozitieAtomica("B"), new PropozitieAtomica("C")))
        });

        // Afisarea propozitiei initiale
        System.out.println("Expresia initiala: " + propInitiala);

        // Eliminarea implicatiilor
        Propozitie propFaraImplicatii = propInitiala.faraImplicatii();

        // Afisarea propozitiei fara implicatii
        System.out.println("Expresia fara implicatii: " + propFaraImplicatii);
    }
}
