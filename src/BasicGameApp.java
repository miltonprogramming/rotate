//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image astroPic;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    public MyPoly astro;
    public MyPoly rastro;


    public boolean startGame=false;

    public static void main(String[] args) {
        BasicGameApp ex ;
        ex= new BasicGameApp();   //creates a new instance of the game
      //  new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
         ex.run();
    }
    // Main method definition
    // This is the code that runs first and automatically
    // Constructor Method
    // Constructor Method
    public int red=255;
    public boolean redison =false;
    public MyVector firstVector;


    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        astroPic = Toolkit.getDefaultToolkit().getImage("astronaut.png"); //load the picture
        int[] x= {100,300,100,300};
        int[] y= {100,100,300,300};
        astro = new MyPoly(x, y);


        int[] a= {500,600,500,600};
        int[] b= {100,100,300,300};
        rastro=new MyPoly(a,b);
        firstVector= new MyVector(200,200,300,300);


    }// BasicGameApp()
    public void crash(){


       // System.out.println("crash");

    }


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

                moveThings();  //move all the game objects


            crash();
            render();  // paint the graphics
            pause(20); // sleep for 10 ms
        }
    }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.green);
        g.drawPolygon(astro.polygon);
        g.drawPolygon(rastro.polygon);

        g.setColor(Color.red);
        g.fillRect(astro.centerx-2,astro.centery-2,4,4);
        g.fillRect(rastro.centerx-2,rastro.centery-2,4,4);
        System.out.println(firstVector.headx);
        g.drawLine((int)(firstVector.tailX+firstVector.shiftX),(int)(firstVector.tailY+firstVector.shiftY),(int)(firstVector.headx+firstVector.shiftX),(int)(firstVector.heady+ firstVector.shiftY));
/*
        for(int a=0;a< astro.rays.size();a++) {
            g.drawLine((int) (astro.rays.get(a).tailX + astro.rays.get(a).shiftX), (int) (astro.rays.get(a).tailY + astro.rays.get(a).shiftY), (int) (astro.rays.get(a).headx + astro.rays.get(a).shiftX), (int) (astro.rays.get(a).heady + astro.rays.get(a).shiftY));
        //    astro.rays.get(a).rotate(1);
           // astro.
        }
*/
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      //  g.drawOval(astro.centerx,astro.centery,(int)(141.4213562373095*2),(int)(141.4213562373095*2));
      //  g.drawOval(0+astro.centerx/2,astro.centery/2,(int)(141.4213562373095*2),(int)(141.4213562373095*2));

        firstVector.rotate(1);
      //  astro.rotate(1);
        astro.update();

        rastro.move();
        rastro.rotate(3);
        rastro.update();



        g.dispose();

        bufferStrategy.show();
    }

    public void moveThings() {
        //calls the move( ) code in the objects


    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addKeyListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        canvas.addKeyListener(this);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        int keycode;
        keycode= e.getKeyCode();

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}