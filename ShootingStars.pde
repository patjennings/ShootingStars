import controlP5.*;

ControlP5 controlP5;
boolean showGUI = false;
Slider[] sliders;

//int tempo; // tempo varie, cf. draw()
int tempo = 5;
ArrayList<Rocket> rockets = new ArrayList<Rocket>();
float xoff = 0.0; // à utiliser pour le noise qui gère le tempo
int switcher = 0;
int quantity = 3;
int rocketSpeed = 3;
int backgroundAlpha = 75;


float X1, Y1, X2, Y2;

void setup(){
  size(2120, 360);
  frameRate(25);
  background(0);
  smooth(8);

   setupGUI();
}
void draw(){
  fill(0, backgroundAlpha);
  noStroke();
  rect(0, 0, width, height);

  xoff = xoff + .01;

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
    void display(){

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
