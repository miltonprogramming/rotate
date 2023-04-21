public class MyVector {
    public double theta,magnitude;
    public double tailX,tailY,headx,heady,newheadx,newheady;
    double angleInRadians ;
    double angleInDegrees ;
    double shiftX,shiftY;
    public MyVector(double mag, double dir){
        theta= dir;
        magnitude= mag;
    }
    public MyVector(double x1,double y1,double x2, double y2){
        if(x1!=0){
            shiftX=x1;
            x1=x1-shiftX;
            x2=x2-shiftX;
        }
        if(y1!=0){
            shiftY=y1;
            y1=y1-shiftY;
            y2=y2-shiftY;
        }
        magnitude= Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));

         angleInRadians = Math.atan2(y2 - y1, x2 - x1);
         angleInDegrees = Math.toDegrees(angleInRadians);
         headx=x2;
         heady=y2;
         tailX=x1;
         tailY=y1;
        theta= angleInDegrees;
        rotate(0);
    }

    public void rotate(double deg){


            //normalize(vector); // No  need to normalize, vector is already ok...

            newheadx = (double)(headx * Math.cos(Math.toRadians(deg)) - heady * Math.sin(Math.toRadians(deg)));

            newheady = (double)(headx * Math.sin(Math.toRadians(deg)) + heady * Math.cos(Math.toRadians(deg))) ;

            headx=newheadx;
            heady= newheady;
            System.out.println("my mag = "+magnitude);

    }

}
