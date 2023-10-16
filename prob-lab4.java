class Remorca{
    private static int nrCutiiUltimaRemorca =10;
    private int nrCutii;
    private String nrInmatriculare;

    //acum facem constructorii
    //primul constructor:
    public Remorca(int nrCutii, String nrInmatriculare){
        this.nrCutii=nrCutii;
        this.nrInmatriculare=nrInmatriculare;
        nrCutiiUltimaRemorca = nrCutii;
    }

    //al doilea constructor:
    public Remorca(String nrInmatriculare){
        this.nrInmatriculare=nrInmatriculare;
        this.nrCutii = nrCutiiUltimaRemorca + 1;
        nrCutiiUltimaRemorca = this.nrCutii;

    }
    
    public int getNrCutii() {
        return nrCutii;
    }

    public String getNrInmatriculare(){
        return nrInmatriculare;
    }

    public String toString(){
        return "R(" + nrInmatriculare + "," + nrCutii + ")";
    }
}

class Tir{
    private Remorca[] remorci;
    private int nrRemorci;

    public Tir(int maxRemorci) {
        remorci = new Remorca[maxRemorci];
        nrRemorci = 0; //initializam cu 0
    }
    //sau specific problemei putem avea constructorul:
    /*public Tir(){
        this.remorci = new Remorca[5];
        this.nrRemorci = 0;
    }*/

    public boolean adaugaRemorca(int nrCutii, String nrInmatriculare){
        if(nrRemorci< remorci.length) {
            //adica verific daca mai avem locuri in vectorul alocat remorci
            remorci[nrRemorci] = new Remorca(nrCutii, nrInmatriculare);
            nrRemorci++;
            return true;
        }
        return false;
    }

    public boolean adaugaRemorca(Remorca remorca){
        if(nrRemorci<remorci.length) {
            remorci[nrRemorci] = remorca;
            nrRemorci++;
            return true;
        }
        return false;
    }


    public boolean equals(Tir altTir){
        int sumaCutiiTirCurent=0;
        int sumaCutiiAltTir=0;
        int i;

        for (i=0; i<nrRemorci; i++) {
            sumaCutiiTirCurent = sumaCutiiTirCurent + remorci[i].getNrCutii();
        }

        for(i=0; i<altTir.nrRemorci; i++) {
            sumaCutiiAltTir = sumaCutiiAltTir + altTir.remorci[i].getNrCutii();
        }

        if(sumaCutiiAltTir == sumaCutiiTirCurent) {
            return true;
        }
        //nu mai pun ramura de else pentru ca daca deja am returnat true, voi iesi din functie
        //fiindca deja am un raspuns returnat
        //in caz contrar, ies din if si returnez false
        return false;
    }

    public Remorca stergeRemorca(String nrInmatriculare){
        int i, j;
        for(i=0; i<nrRemorci; i++) {
            if(remorci[i].getNrInmatriculare().equals(nrInmatriculare)) {
                Remorca remorcaStearsa = remorci[i];
                for(j=i; j<nrRemorci; j++) {
                    remorci[j]=remorci[j+1];
                }
                nrRemorci--;
                return remorcaStearsa;
            }
        }
        return null;
    }

    @Override
    public String toString(){
        int i;
        String rezultat = "T ->";
        for(i=0; i<nrRemorci; i++) {
            rezultat = rezultat + remorci[i].toString();
            if(i<nrRemorci-1){
                rezultat =  rezultat + "->";    //ca sa nu imi apara sageata si dupa ultimul element
            }
        }
        return rezultat;
    }
}


class Main {
    public static void main(String[] args) {
        Tir tir1 = new Tir(5);
        tir1.adaugaRemorca(20, "R1");
        tir1.adaugaRemorca(15, "R2");

        Tir tir2 = new Tir(3);
        tir2.adaugaRemorca(10, "R3");

        System.out.println("Tir1: " + tir1);
        System.out.println("Tir2: " + tir2);

        System.out.println("Tir1 egal cu Tir2 ? " + tir1.equals(tir2));

        Remorca remorcaStearsa = tir1.stergeRemorca("R2");
        System.out.println("Remorca stearsa: " + remorcaStearsa);

        System.out.println("Tir1: " + tir1);
    }
}
