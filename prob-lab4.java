 class Remorca {
    private int nrCutii;
    private String nrInmatriculare;


    public int getNrCutii(){
        return this.nrCutii;
    }

    public String getNrInmatriculare(){
        return this.nrInmatriculare;
    }
    public Remorca(int nrCutii, String nrInmatriculare) {
        this.nrCutii = nrCutii;
        this.nrInmatriculare = nrInmatriculare;
    }

    public Remorca(String nrInmatriculare) {
        this.nrCutii = 10; // Valoarea implicită a cutiilor
        this.nrInmatriculare = nrInmatriculare;
    }

    // Getteri pentru nrCutii și nrInmatriculare

    @Override
    public String toString() {
        return "R(" + nrInmatriculare + ", " + nrCutii + ")";
    }
}


 class Tir {
     private Remorca[] remorci;
     private int numRemorci;

     public Tir(int maxRemorci) {
         remorci = new Remorca[maxRemorci];
         numRemorci = 0;
     }

     public boolean adaugaRemorca(int nrCutii, String nrInmatriculare) {
         if (numRemorci < remorci.length) {
             remorci[numRemorci] = new Remorca(nrCutii, nrInmatriculare);
             numRemorci++;
             return true;
         }
         return false;
     }

     public boolean adaugaRemorca(Remorca remorca) {
         if (numRemorci < remorci.length) {
             remorci[numRemorci] = remorca;
             numRemorci++;
             return true;
         }
         return false;
     }

     public Remorca stergeRemorca(String nrInmatriculare) {
         int i, j;
         for (i = 0; i < numRemorci; i++) {
             if (remorci[i].getNrInmatriculare().equals(nrInmatriculare)) {
                 Remorca remorcaStearsa = remorci[i];
                 //memoram in variabila remorcaStearsa valoarea de la indexul cautat pentru a-l putea ulterior returna la final
                 // Mutam remorca din spate pentru a umple locul gol
                 for (j = i; j < numRemorci; j++) {
                     remorci[j] = remorci[j + 1];
                 }
                 numRemorci--;
                 //remorci[numRemorci] = null; // Ștergem ultimul element(optional)
                 return remorcaStearsa;
             }
         }
         return null;
     }

     public boolean equals(Tir altTir) {
         int sumaCutiiThis = 0;
         int sumaCutiiOther = 0;
         int i;

         for (i = 0; i < numRemorci; i++) {
             sumaCutiiThis = sumaCutiiThis + remorci[i].getNrCutii();
         }

         for (i = 0; i < altTir.numRemorci; i++) {
             sumaCutiiOther = sumaCutiiOther + altTir.remorci[i].getNrCutii();
         }

         return sumaCutiiThis == sumaCutiiOther;
     }


     @Override
     public String toString() {
         int i;
         String result = "T -> ";
         for (i = 0; i < numRemorci; i++)
         {
             result = result + remorci[i].toString();
             if (i < numRemorci - 1)
             {
                 result = result + " -> ";
             }
         }
         return result;
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
