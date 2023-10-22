import java.util.Random;
import java.util.Scanner;

class Telefon{
    private static int count = 0;
    private String[] apelatori = new String[100];
    private String numeProprietar;


    //facem constructorul
    public Telefon(String numeProprietar){
        this.numeProprietar = numeProprietar;
    }

    public String getNumeProprietar()
    {
        return numeProprietar;
    }
    public boolean apeleaza(Telefon telefonProprietar){
        if(count<100)
        {
            telefonProprietar.adaugaNrTelefon(numeProprietar);
            return true;
        }
        return false;
    }


    public void adaugaNrTelefon(String nume){
        apelatori[count]=nume;
        count++;
    }

    public int nrDeApeluri(String nume){
        int i;
        int nrApeluri=0;
        for(i=0; i<count; i++)
        {
            if(nume.equals(apelatori[i]))
            {
                nrApeluri++;
            }
        }
        return nrApeluri;
    }

    public String toString(){
        String rezultat = "Proprietar: " + numeProprietar + "\n";
        rezultat = rezultat + "Apelatori: ";
        int i;
        for(i=0; i<count; i++)
        {
            if(apelatori[i]!=null)
            {
                rezultat = rezultat + apelatori[i];
                if(i<count-1)
                {
                    rezultat = rezultat + ",";
                }
            }
        }
        return rezultat;
    }
}

class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int i;
        String numeProprietar;
        int nrTelefoane;


        System.out.print("Introduceti numarul de de telefoane: ");
        nrTelefoane = scanner.nextInt();

        Telefon[] telefoane = new Telefon[nrTelefoane];

        System.out.println("Introduceti numele proprietarilor pentru telefoanele create");
        for(i=0; i<nrTelefoane; i++) {
            System.out.print("Proprietar " + (i+1) + ":");
            numeProprietar = scanner.next();
            telefoane[i] = new Telefon(numeProprietar);
        }

        System.out.print("Introduceti numarul de apeluri de efectuat(A): ");
        int A = scanner.nextInt();
        for(i=0; i<A; i++)
        {
            int x, y;
            x = random.nextInt(nrTelefoane);
            //ii dau un numar random care sa fie cuprins intre 0 si nrTelefoane
            y = random.nextInt(nrTelefoane);
            telefoane[x].apeleaza(telefoane[y]);
            // x = telefoane sursa
            //y = telefoane destinatie
        }

        System.out.print("Introduceti numele proprietarului carui doriti sa ii aflati datele:");
        String proprietarVerificare = scanner.next();
        System.out.println();

        for (i=0; i<nrTelefoane; i++)
        {
            System.out.println(telefoane[i]);
            int nrApeluri = telefoane[i].nrDeApeluri(proprietarVerificare);
            System.out.println("Număr de apeluri pentru " + proprietarVerificare + ": " + nrApeluri);
            System.out.println();
        }
    }
}
