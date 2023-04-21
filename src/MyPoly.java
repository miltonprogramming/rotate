import java.awt.*;
import java.util.ArrayList;

public class MyPoly {
    public Polygon polygon;
    public int centerx,centery;
    public int totalx,totaly;
    public ArrayList<MyVector> rays = new ArrayList<MyVector>();
    public int[] xp,yp;
    public int dx,dy;

    public MyPoly(int[] x, int[] y){
        dx=1;
        dy=2;
        xp=x;
        yp=y;
        polygon= new Polygon(x,y,x.length);
        for(int a=0;a<x.length;a++){
            totalx=totalx+x[a];
            totaly=totaly+y[a];
        }
        centerx=totalx/x.length;
        centery=totaly/y.length;
        for(int a=0;a<x.length;a++){
            rays.add(new MyVector((double)centerx,(double)centery,(double)x[a],(double)y[a]));
        }
    }
    public void update(){

      for(int x=0;x<rays.size();x++){
          xp[x]= (int) (rays.get(x).headx + rays.get(x).shiftX);
          yp[x]=(int)(rays.get(x).heady + rays.get(x).shiftY);


      }
      polygon= new Polygon(xp,yp,xp.length);
    }
    public void rotate(double theata){
        for(MyVector vec:rays){
            vec.rotate(theata);
        }
    }
    public void move(){
        centerx=centerx+dx;
        centery=centery+dy;
        rays = new ArrayList<MyVector>();

        for(int x=0;x<xp.length;x++){
            xp[x]= xp[x]+dx;
            yp[x]=yp[x]+dy;
            System.out.println("dx"+dx);



        }
        for(int a=0;a<xp.length;a++){
            rays.add(new MyVector((double)centerx,(double)centery,(double)xp[a],(double)yp[a]));
        }

        polygon= new Polygon(xp,yp,xp.length);

    }
}
