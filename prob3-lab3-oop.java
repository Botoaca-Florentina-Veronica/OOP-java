/*
Print the average of three numbers entered by user
by creating a class named 'Average' having a method to calculate and print the average
*/
//Observations!
/*
When an instance is declared but not constructed, it holds a special value called null

example:
circle c1, c2, c3;
here we have declared the variables (c1 = c2 = c3 = null)

c1= new Circle()
c2= new Circle(2)
c3= new Circle(3,14)
here we constructed the variables

 */
// problema 3, lectia 3, labul 3
/*
    Un patrat este caracterizat de latura sa. Scrieti o clasa Patrat ce are 2 constructori, un constructor
 fara niciun parametru, care seteaza latura patratului ca fiind 10, iar altul care seteaza latura cu o valoare
 egala cu cea a unui paramtru transmis constructorului. Atasati clasei o metoda potrivita pentru tiparirea unui
 patrat sub forma "Patrat" l "Aria" a unde l este valoarea laturii, iar a este valoarea ariei patratului.
    Creati intr-o metoda main diferite obiecte de tip Patrat si tipariti-le.
*/

 class Patrat{
    private double latura;
    private static double arie;

    public Patrat(double latura){   //primul constructor
        this.latura=latura;
    }

    public Patrat(){    //al doilea constructor
        this.latura=10;
    }

    public double CalculeazaArie(){
        return latura*latura;
    }
    public void afisare(){
        System.out.println("Patrat " + latura + " Arie " + CalculeazaArie());
    }
}

 class TestPatrat{
    public static void main(String[] args){
        // Creare obiecte de tip Patrat folosind cei doi constructori
        Patrat p2=new Patrat(15);   //pentru primul constructor
        Patrat p1=new Patrat();         //pentru al doilea constructor

        p1.afisare();
        p2.afisare();
    }
}
