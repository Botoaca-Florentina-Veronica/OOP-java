// Clasa Fisier
class Fisier {
    private static int id=0;
    private String nume;
    private String continut;
    private Fisier versiuneAnterioara;

    public Fisier(String nume, String continut) {
        this.id++;
        this.nume = nume;
        this.continut = continut;
        this.versiuneAnterioara = null;
    }

    public void salveazaVersiune() {
        String numeNou = this.nume + "bak";
        Fisier nouaVersiune = new Fisier(numeNou, this.continut);
        nouaVersiune.versiuneAnterioara = this.versiuneAnterioara;
        //referinta anterioara a noului obiect fisier este setata sa fie versiunea anterioara a fisierului curent
        //Cu alte cuvinte, noul obiect Fisier va avea referința către versiunea anterioară a fișierului curent.
        this.versiuneAnterioara = nouaVersiune;
        //Referința versiuneAnterioara a fișierului curent este actualizată să fie noul obiect Fisier creat
        // în acest pas. Acest lucru înseamnă că acum fișierul curent are o versiune anterioară,
        // care este noul obiect Fisier creat în acest pas.

        /*  În esență, funcția salveazaVersiune() realizează două lucruri principale: creează o versiune nouă
        a fișierului curent și actualizează referința către versiunea anterioară a fișierului curent pentru a
        arăta către această versiune nouă.
            Astfel, se păstrează un istoric al modificărilor fișierului.*/
    }

    //metoda concateneaza continutul fisierului primit ca parametru cu acel continut al fisierului actual
    public void concateneaza(Fisier fisier) {
        this.continut = this.continut + fisier.continut;
        this.salveazaVersiune();
    }

    public String toString() {
        String result = id + " " + nume + " [ " + continut + " ] ";
        if (versiuneAnterioara != null) {
            result = result +  "<" + versiuneAnterioara.toString() + ">";
        }
        return result;
    }

    //apelam functia numarConcatenari recursiv, iar de fiecare data cand dam de o alta versiune anterioara
    //vom creste cu 1 numarul de concatenari, pana va ajunge la null
    public int numarConcatenari() {
        if (versiuneAnterioara != null) {
            return 1 + versiuneAnterioara.numarConcatenari();
        }
        else {
            return 0;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Fisier fisier1 = new Fisier("file1", "Continut1");
        Fisier fisier2 = new Fisier("file2", "Continut2");

        System.out.println("Fisier1 initial:");
        System.out.println(fisier1.toString());

        fisier1.salveazaVersiune();
        fisier1.concateneaza(fisier2);

        System.out.println("Fisier1 dupa concatenare:");
        System.out.println(fisier1.toString());

        System.out.println("Numar de concatenari pentru Fisier1: " + fisier1.numarConcatenari());
    }
}
