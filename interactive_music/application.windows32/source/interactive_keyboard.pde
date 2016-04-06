// Declare the processing sound variables 
import processing.sound.*;
SoundFile sample;
Amplitude rms;
//Pendulum intiliazation 
Spring2D s1, s2;
float f1 = 1.0;
float m1 = 0.1;
Spring2D[] s = new Spring2D[10];
float gravity = -3.0;
float rad = 0;


// RMS scaling factor
float scale=5;
// Declare a smooth factor
float smooth_factor=0.25;
// Used for smoothing
float sum;
int bpnum = 35;
BounceParticle[] bounce1 = new BounceParticle[bpnum];

int particleLines = 500;
displayLines[] lineDis1 = new displayLines[particleLines];
displayLines[] lineDis2 = new displayLines[particleLines];
displayLines[] lineDis3 = new displayLines[particleLines];

public void setup() {
    size(640,360, P3D);
    w = width+16;
    dx = (TWO_PI / period) * xspacing;
    yvalues = new float[w/xspacing];
    keyboard();
    
    //Load and play a soundfile and loop it
    sample = new SoundFile(this, "hawaii.wav");
    sample.loop();
   
    
    // Create and patch the rms tracker
    rms = new Amplitude(this);
    rms.input(sample);
    
    /*Spring2D(x, y, rx, ry, mass);
    s1 = new Spring2D(width/2.0, 100.0, m1);
    s1.setPosition(width/2, 0);
    for (int i = 0; i < s.length; i++) {
      s[i] = new Spring2D(width/2, i*(height/s.length), m1);
    }
    */
    for (int i = 0; i < bounce1.length; i++) {
      float vx = random(-300, 500);
      float vy = random(-500, 500);
      bounce1[i] = new BounceParticle(180, 150, vx, vy, 5.0);
    }
    
    for (int i = 0; i < lineDis1.length; i++) {
      float vx = random(-10, 10);
      float vy = random(-10, -2);
      lineDis1[i] = new displayLines(330, 350, vx, vy, 2.0);
      lineDis2[i] = new displayLines(150, 350, vx, vy, 2.0);
      lineDis3[i] = new displayLines(550, 350, vx, vy, 2.0);
    }
}      

public void draw() {
    // Set background color, noStroke and fill color
    background(value[0],value[1],value[2]);
    rad += .03;  
    /*
    s1.applyForces(15*sin(2.92*rad), -f1*2*cos(2*rad*2.92));
    s1.display(width/2, height);//0.0);
    */
    noFill();
    stroke(255,0,150);
    // smooth the rms data by smoothing factor
    sum += (rms.analyze() - sum) * smooth_factor;  
    // rms.analyze() return a value between 0 and 1. It's
    // scaled to height/2 and then multiplied by a scale factor
    float rms_scaled=sum*(height/2)*scale;
    calcWave();
    renderWave(rms_scaled);
    // We draw an ellispe coupled to the audio analysis
    
    for (BounceParticle particle : bounce1) {
      particle.applyForces(0.0, gravity);
      particle.display();
    }

    for (displayLines particle : lineDis1) {
      particle.applyForces(0.0, gravity);
      particle.display();
    }
    
    for (displayLines particle : lineDis2) {
      particle.applyForces(0.0, gravity);
      particle.display();
    }
    
    for (displayLines particle : lineDis3) {
      particle.applyForces(0.0, gravity);
      particle.display();
    }
}