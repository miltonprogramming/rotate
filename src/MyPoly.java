import java.awt.*;
import java.util.ArrayList;

public class MyPoly {
    public Polygon polygon;
    public double centerx,centery;
    public double totalx,totaly;
    public ArrayList<MyVector> rays = new ArrayList<MyVector>();
    public int[] xpi,ypi;
    public double[] xp,yp;
    public int dx,dy;

    public MyPoly(double[] x, double[] y){
        dx=1;
        dy=2;
        xp=x;
        yp=y;
        xpi=new int[xp.length];
        ypi=new int[yp.length];

        for(int a=0;a<x.length;a++){
            totalx=totalx+x[a];
            totaly=totaly+y[a];
            xpi[a]=(int)xp[a];
            ypi[a]=(int)yp[a];
        }
        polygon= new Polygon(xpi,ypi,x.length);

        centerx=totalx/x.length;
        centery=totaly/y.length;
        for(int a=0;a<x.length;a++){
            rays.add(new MyVector((double)centerx,(double)centery,(double)x[a],(double)y[a]));
        }
    }
    public void update(){
        totalx=0;
        totaly=0;
      for(int x=0;x<rays.size();x++){
          xp[x]= (rays.get(x).headx + rays.get(x).shiftX);
          yp[x]=(rays.get(x).heady + rays.get(x).shiftY);
          xpi[x]=(int)xp[x];
          ypi[x]=(int)yp[x];
          totalx=totalx+xp[x];
          totaly=totaly+yp[x];
      }
        centerx=totalx/xp.length;
        centery=totaly/yp.length;
      polygon= new Polygon(xpi,ypi,xp.length);
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
            xpi[x]=(int)xp[x];
            ypi[x]=(int)yp[x];
            System.out.println("dx"+dx);



        }
        for(int a=0;a<xp.length;a++){
            rays.add(new MyVector((double)centerx,(double)centery,(double)xp[a],(double)yp[a]));
        }

        polygon= new Polygon(xpi,ypi,xpi.length);

    }
}
