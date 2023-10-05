
    //de retinut ca atunci cand declaram o variabila inauntrul unei clase folosind static,
    // java va initializa automat variabila adica daca ai un numar, acesta va fi automat initializat cu 0
    //daca am un sir de caractere, atunci il va initializa cu null

    //problema 5 de la lab, sapt 2
    // Definim clasa Complex
     class Complex {
        private double real;
        private double imaginar;

        // Constructorul cu doi parametri pentru setarea părților reală și imaginară
        public Complex(double real, double imaginar) {
            this.real = real;
            this.imaginar = imaginar;
        }

        // Metoda pentru calculul modulului unui număr complex
        public double calculModul() {
            return Math.sqrt(real * real + imaginar * imaginar);
        }

        // Metoda pentru afișarea numărului complex sub forma "real + i * imaginar"
        public void afisare() {
            System.out.println(real + " + i * " + imaginar);
        }

        // Metoda pentru adunarea a două numere complexe și returnarea rezultatului ca Complex
        public Complex aduna(Complex numarComplex) {
            double realRezultat = this.real + numarComplex.real;
            double imaginarRezultat = this.imaginar + numarComplex.imaginar;
            return new Complex(realRezultat, imaginarRezultat);
        }

        // Metoda pentru a afișa pe ecran valorile unui număr complex
        public static void afisareComplex(Complex numarComplex) {
            numarComplex.afisare();
        }
    }

    // Definim clasa ClientComplex pentru exemplificarea utilizării clasei Complex
     class ClientComplex {
        public static void main(String[] args) {
            // Exemplu de utilizare a clasei Complex
            Complex numar1 = new Complex(3.0, 4.0);
            Complex numar2 = new Complex(1.0, -2.0);

            System.out.println("Numarul 1:");
            numar1.afisare();
            System.out.println("Modulul numarului 1: " + numar1.calculModul());

            System.out.println("Numarul 2:");
            numar2.afisare();
            System.out.println("Modulul numarului 2: " + numar2.calculModul());

            Complex suma = numar1.aduna(numar2);
            System.out.println("Suma numerelor 1 și 2:");
            suma.afisare();

            // Exemplu de utilizare a metodei statice afisareComplex
            Complex.afisareComplex(suma);
        }
    }
