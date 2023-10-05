/*
Print the average of three numbers entered by user
by creating a class named 'Average' having a method to calculate and print the average
*/

class Average{
    private double a;
    private double b;
    private double c;

    public Average(double a, double b, double c){
        this.a=a;
        this.b=b;
        this.c=c;
    }
    public double getAverage(){
        return (a+b+c)/3;
    }
}

class Main{

    public static void main(String[] args){
        Average average = new Average(12, 4, 2);
        System.out.println("The average of the 3 numbers is: " + average.getAverage());
    }
}