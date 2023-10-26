/*  Intr-un program pentru gestiunea finantelor personale avem doua clase: Card si Portofel.
    Un card e caracterizat de un numar (sir de caractere) si un sold (numar real).
    Aceste caracteristici sunt setate prin constructor. Clasa poate sa contina si alte elemente considerate necesare.
    Un portofel e caracterizat de un sir de caractere reprezentand numele proprietarului care este setat prin constructor,
    Fiecare portofel mai contine un tablou de referinte la obiecte de tip Card (eventual si un contor daca se considera necesar) reprezentand cardurile care 
    exista in portofel.
    Numarul maxim de carduri permise este de 6. Pe un portofel putem efectua urmatoarele operatii:
* adaugacard: ce primeste ca parametru un sir de caractere si o valoare reala reprezentand numarul cardului, respectiv soldul. Daca mai exista loc disponibil 
in tablou si daca un card cu numarul respectiv nu a fost adaugat deja,
 se instantiaza o referinta la un obiect card folosind datele primite ca parametru si metoda intoarce valoarea true.
    Altfel, adaugarea nu are loc si metoda intoarce valoarea false.
*   O operatie corespunzatoare care intoarce reprezentarea sub forma de sir de caractere a portofelului sub forma "Portofel
numeProprietar: Card1 Card2... CardN" unde CardX este reprezentarea sub forma de sir de caractere a unui card, iar N corespunde
numarului de carduri aflate in portofel la momentul respectiv. Un card e reprezentat sub forma "Numar -sold".
calculeazaSold: ce returneaza suma soldurilor tuturor cardurilor din portofel.
*   Se va implementa si o metoda main in care se va crea un portofel si se vor adauga la el 4 carduri dintre care unul cu
acelasi numar ca a unui card adaugat anterior. Apoi se afiseaza portofelul la iesirea standard si se calculeaza soldul total disponibil pentru portofeluil respectiv.
*/

class Card{
    private int sold = 0;
    private String numarCard;

    //facem constructorul
    public Card(String numarCard, int sold){
        this.sold = sold;
        this.numarCard = numarCard;
    }

    public int getSold() {
        return sold;
    }

    public String getNumarCard(){
        return numarCard;
    }
}

class Portofel{
    private String numeProprietar;
    private static int count = 0;
    private Card[] carduri = new Card[6];

    public Portofel(String numeProprietar) {
        this.numeProprietar = numeProprietar;
    }

    public boolean adaugaCard(String numarCard, int sold){
        int i;
        for(i=0; i<count; i++)
        {
            if(carduri[i].getNumarCard() == numarCard)
            {
                //inseamna ca avem acest card deja in portofel, deci returnez false
                return false;
            }
        }
        if(count<6)
        {
            //daca am trecut de bucla for de mai sus, inseamna ca acum am de a face cu un card nou, deci daca mai am
            //loc, il voi adauga si returnez true
            carduri[count++] = new Card(numarCard, sold);    //vezi dc nu ai facut asa si la ailalta prob
            return true;
        }
        return false;
    }

    public String toString(){
        String rezultat = "Portofel numeProprietar: " + numeProprietar + "\n";
        int i;
        for(i=0; i<count; i++)
        {
            rezultat = rezultat + "Numar Card " + (i+1) + ": " + carduri[i].getNumarCard() + "-sold: " + carduri[i].getSold();
            if(i<count-1){
                rezultat = rezultat +"," + "\n";
            }
        }
        return rezultat;
    }

    public int calculeazaSold()
    {
        int soldTotal = 0;
        int i;
        for(i=0; i<count; i++)
        {
            soldTotal =  soldTotal + carduri[i].getSold();
        }
        return soldTotal;
    }
}

class Main{
    public static void main(String[] args){
        Portofel portofel1 = new Portofel("vera");


        portofel1.adaugaCard("RO1CB213", 300);
        portofel1.adaugaCard("RO1CB345", 700);
        portofel1.adaugaCard("RO1CB567", 100);
        portofel1.adaugaCard("RO1CB567", 200);

        System.out.println(portofel1.toString());
        System.out.println("Suma soldurilor din toate cardurile este: " + portofel1.calculeazaSold());

    }
}
