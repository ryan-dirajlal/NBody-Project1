package com.company;
import javax.swing.*;        
import java.awt.*;          // for Graphics C:\Users\ryan\Desktop\nbody
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.lang.*;



public class NBody<E> extends JPanel implements ActionListener  //from YouTube tutorial
{

    List<CelestialBody> planets = null;

    public void readFile() throws IOException 
    {
        File textFile = new File("/Users/ryan/Desktop/nbody/NBody.txt");
        BufferedReader fileScanner = new BufferedReader(new FileReader(textFile));

        String listType = fileScanner.readLine();                   // reads first line
        if (listType.equals("LinkedList")) 
        {
            planets = new LinkedList<CelestialBody>();
        } 
        else if (listType.equals("ArrayList")) 
        {
            planets = new ArrayList<CelestialBody>();
        }

        double scale = Double.valueOf(fileScanner.readLine());      // reads second line

        String lines;
        while ((lines = fileScanner.readLine()) != null)
         {
            String[] temp = lines.split(","); 
      
            planets.add(new CelestialBody(temp[0], Double.parseDouble(temp[1]), Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), scale * Double.parseDouble(temp[4]), scale * Double.parseDouble(temp[5]), Integer.parseInt(temp[6])));
                                         // name,  mass,                         xCoord,                   yCoord,                   xVelocity,                            yVelocity,                           radius
        }
     

    }

    public void paintComponent(Graphics g) 
    { //from the YouTube tutorial
        Timer timer = new Timer(1100, this);
        super.paintComponent(g);        

        for (int i=0; i<planets.size(); i++) 
        {
            g.setColor(Color.YELLOW);

            if (i == 1) 
            {
                g.setColor(Color.BLUE);
            } else if (i == 2)
            {
                g.setColor(Color.RED);
            } else if (i == 3)
            {
                g.setColor(Color.BLACK);
            } else if (i >= 4) 
            {
                g.setColor(Color.PINK);
            }

            CelestialBody cb = planets.get(i);
            g.fillOval(cb.getxCoordinate(), cb.getyCoordinate(), cb.getRadius(), cb.getRadius());
        }

        timer.start();
    }


    public void actionPerformed(ActionEvent e) 
    {
        int xCoordinate, yCoordinate;     // cb
        int xCoord1, yCoord1;   // cb1
        double xVelocity, yVelocity;    // cb
        double xVelocity1, yVelocity1;  // cb1
        double mass1, mass2, distance, force;  
        double gravity = 6.674 * Math.pow(10, -11);


        for (int i=0; i<planets.size(); i++) 
        {
            CelestialBody cb = planets.get(i);
            mass1 = cb.getMass();

            for (int j=1; j<planets.size() && i != j; j++) 
            {
                CelestialBody cb1 = planets.get(j);
                mass2 = cb1.getMass();
                distance = Math.sqrt(Math.pow((cb1.getyCoordinate() - cb.getyCoordinate()), 2) + Math.pow((cb1.getxCoordinate() - cb.getxCoordinate()), 2));
                force = (gravity*(mass1*mass2))/Math.pow(distance, 2); //calculation from the assignment

                if (cb.getxCoordinate() > cb1.getxCoordinate()) 
                {        //whether the other x is < > to cb
                    cb1.setxVelocity(cb1.getxVelocity() + (force/mass1));
                } 
                else if (cb.getxCoordinate() < cb1.getxCoordinate()) 
                {
                    cb1.setxVelocity(cb1.getxVelocity() - (force/mass1));
                }

                if (cb.getyCoordinate() > cb1.getyCoordinate()) 
                {
                    cb1.setyVelocity(cb1.getyVelocity() + (force/mass1));
                } 
                else if (cb.getyCoordinate() < cb1.getyCoordinate()) 
                {
                    cb1.setyVelocity(cb1.getyVelocity() - (force/mass1));
                }

                xCoord1 = cb1.getxCoordinate();
                xVelocity1 = cb1.getxVelocity();
                cb1.setxCoordinate((int) (xCoord1 + xVelocity1));

                yCoord1 = cb1.getyCoordinate();
                yVelocity1 = cb1.getyVelocity();
                cb1.setyCoordinate((int) (yCoord1 + yVelocity1));

            }

            repaint();

        }
    }

 
    public static void main(String[] args) throws IOException 
    {

     
        NBody nBody = new NBody();
        nBody.readFile();

        JFrame jFrame = new JFrame();
        jFrame.setTitle("NBody");
        jFrame.setSize(768, 768);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(nBody);
        jFrame.validate();

    }
}





