import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import controlP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ShootingStars extends PApplet {



ControlP5 controlP5;
boolean showGUI = false;
Slider[] sliders;

//int tempo; // tempo varie, cf. draw()
int tempo = 5;
ArrayList<Rocket> rockets = new ArrayList<Rocket>();
float xoff = 0.0f; // \u00e0 utiliser pour le noise qui g\u00e8re le tempo
int switcher = 0;
int quantity = 3;
int rocketSpeed = 3;
int backgroundAlpha = 75;


float X1, Y1, X2, Y2;

public void setup(){
  
  frameRate(25);
  background(0);
  

   setupGUI();
}
public void draw(){
  fill(0, backgroundAlpha);
  noStroke();
  rect(0, 0, width, height);

  xoff = xoff + .01f;

  //tempo = ceil(noise(xoff)*1);

  if(frameCount%tempo == 1){

    for(int i = 0 ; i<=quantity ; i++){
      rockets.add(new Rocket(random(width), random(height), random(width), random(height), random(rocketSpeed-5, rocketSpeed+5)));
    }
  }

  for(int i = 0 ; i < rockets.size() ; i++)
  {
    Rocket part = rockets.get(i);
    part.display();
  }

  drawGUI();
}

class Rocket {
   float x1, y1, x2, y2;
   float ax, ay, speed, iterate;
   float px, py; // previous position

    Rocket (float X1, float Y1, float X2, float Y2, float Speed){
        x1 = X1;
        y1 = Y1;
        x2 = X2;
        y2 = Y2;

        ax = x1;
        ay = y1;

        px = ax;
        py = ay;

        speed = Speed;
        iterate = height/speed;
    }
    public void display(){

        if(x2 > x1) ax += (abs(x1-x2))/iterate;
        if(x1 > x2) ax -= (abs(x1-x2))/iterate;
        if(y2 > y1) ay += (abs(y1-y2))/iterate;
        if(y1 > y2) ay -= (abs(y1-y2))/iterate;

        /*fill(255);
        noStroke();
        ellipse(ax, ay, 2, 2);*/

        stroke(255);
        strokeWeight(2);
        line(px, py, ax, ay);

        px = ax;
        py = ay;


    }
}
public void setupGUI(){
  int activeColor = color(0,130,164);
  controlP5 = new ControlP5(this);
  //controlP5.setAutoDraw(false);
  controlP5.setColorActive(activeColor);
  controlP5.setColorBackground(color(170));
  controlP5.setColorForeground(color(50));
  //controlP5.setColorLabel(color(50));
  //controlP5.setColorValue(color(255));

  ControlGroup ctrl = controlP5.addGroup("menu",15,25,35);
  ctrl.setColorLabel(color(255));
  ctrl.close();

  sliders = new Slider[10];

  int left = 0;
  int top = 5;
  int len = 150;

  int si = 0;
  int posY = 0;

  sliders[si++] = controlP5.addSlider("tempo",1,50,left,top+posY+0,len,15);
  posY += 20;
  sliders[si++] = controlP5.addSlider("quantity",1,100,left,top+posY+0,len,15);
  posY += 20;
  sliders[si++] = controlP5.addSlider("rocketSpeed",1,50,left,top+posY+0,len,15);
  posY += 20;
  sliders[si++] = controlP5.addSlider("backgroundAlpha",0,255,left,top+posY+0,len,15);

  for (int i = 0; i < si; i++) {
    sliders[i].setGroup(ctrl);
    /*
    sliders[i].captionLabel().toUpperCase(true);
    sliders[i].captionLabel().style().padding(4,3,3,3);
    sliders[i].captionLabel().style().marginTop = -4;
    sliders[i].captionLabel().style().marginLeft = 0;
    sliders[i].captionLabel().style().marginRight = -14;
    sliders[i].captionLabel().setColorBackground(0x99ffffff);
    */
  }

}

public void drawGUI(){
  controlP5.show();
  controlP5.draw();
}
  public void settings() {  size(2120, 360);  smooth(8); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ShootingStars" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
