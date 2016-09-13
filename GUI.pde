void setupGUI(){
  color activeColor = color(0,130,164);
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

void drawGUI(){
  controlP5.show();
  controlP5.draw();
}