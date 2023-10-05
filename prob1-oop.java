//Write a Java program to create a class called
//"Rectangle" with width and height attributes.
//Calculate the area and perimeter of the rectangle.
class Rectangle{
    private double width;
    private double height;

    public Rectangle(double width, double height){
        this.width=width;
        this.height=height;
    }


    public void setWidth(double width){
        this.width=width;
    }

    public void setHeight(double height){
        this.height=height;
    }

    public double getArea(){
        return width*height;
    }

    public double getPerimeter(){
        return 2* (width + height);
    }
}

class Main{

    public static void main(String[] args){
        Rectangle rectangle = new Rectangle(7, 12);
        System.out.println("The area of the rectangle is " + rectangle.getArea());
        System.out.println("The perimeter of the recangle is " + rectangle.getPerimeter());

        rectangle.setWidth(6);
        rectangle.setHeight(12);

        System.out.println("\nThe area is now: " + rectangle.getArea());
        System.out.println("The perimeter is now: " + rectangle.getPerimeter());
    }
}