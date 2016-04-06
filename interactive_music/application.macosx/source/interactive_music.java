import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 
import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class interactive_music extends PApplet {

// Declare the processing sound variables 

SoundFile sample;
Amplitude rms;
//Pendulum intiliazation 
Spring2D s1, s2;
float f1 = 1.0f;
float m1 = 0.1f;
Spring2D[] s = new Spring2D[10];
float gravity = -3.0f;
float rad = 0;


// RMS scaling factor
float scale=5;
// Declare a smooth factor
float smooth_factor=0.25f;
// Used for smoothing
float sum;
int bpnum = 35;
BounceParticle[] bounce1 = new BounceParticle[bpnum];

int particleLines = 500;
displayLines[] lineDis1 = new displayLines[particleLines];
displayLines[] lineDis2 = new displayLines[particleLines];
displayLines[] lineDis3 = new displayLines[particleLines];

public void setup() {
    
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
      bounce1[i] = new BounceParticle(180, 150, vx, vy, 5.0f);
    }
    
    for (int i = 0; i < lineDis1.length; i++) {
      float vx = random(-10, 10);
      float vy = random(-10, -2);
      lineDis1[i] = new displayLines(330, 350, vx, vy, 2.0f);
      lineDis2[i] = new displayLines(150, 350, vx, vy, 2.0f);
      lineDis3[i] = new displayLines(550, 350, vx, vy, 2.0f);
    }
}      

public void draw() {
    // Set background color, noStroke and fill color
    background(value[0],value[1],value[2]);
    rad += .03f;  
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
      particle.applyForces(0.0f, gravity);
      particle.display();
    }

    for (displayLines particle : lineDis1) {
      particle.applyForces(0.0f, gravity);
      particle.display();
    }
    
    for (displayLines particle : lineDis2) {
      particle.applyForces(0.0f, gravity);
      particle.display();
    }
    
    for (displayLines particle : lineDis3) {
      particle.applyForces(0.0f, gravity);
      particle.display();
    }
}


AudioDevice device;
SoundFile[] file;

// Define the number of samples 
int numsounds = 5;

int value[] = {0,0,0};
public void keyboard(){
  device = new AudioDevice(this, 48000, 32);
  file = new SoundFile[numsounds];
  
  // Load 5 soundfiles from a folder in a for loop. By naming the files 1., 2., 3., n.aif it is easy to iterate
  // through the folder and load all files in one line of code.
  for (int i = 0; i < numsounds; i++){
    file[i] = new SoundFile(this, (i+1) + ".aif");
  }
  
}
public void keyPressed() {
  for (int i=0; i < 3; i++) {  
      value[i]=PApplet.parseInt(random(255));
  }
 
  switch(key){
  case 'a':
    file[0].play(3.0f, 1.0f);
    break;

  case 's':
    file[1].play(2.8f, 1.0f);
    break;
  
  case 'd':
    file[2].play(2.8f, 1.0f);
    break;
  
  case 'f':
    file[3].play(2.8f, 1.0f);
    break;
  
  case 'g':
    file[4].play(0.8f, 1.0f);
    break;
  
   case 'h':
    file[0].play(1.1f, 1.0f);
    break;
   
   case 'j':
    file[1].play(1.2f, 1.0f);
    break;

   case 'k':
    file[2].play(1.4f, 1.0f);
    break;
    
   case 'l':
    file[2].play(2.8f, 1.0f);
    break;
    
   case '\u00f6':
    file[4].play(1.0f, 1.0f);
    break;
    
   case '\u00e4':
    file[0].play(2.0f, 1.0f);
    break;
    
   case 'q':
    file[1].play(2.4f, 1.0f);
    break;
   
   case 'w':
    file[2].play(2.1f, 1.0f);
    break;    
   
   case 'e':
    file[3].play(2.8f, 1.0f);
   break;
   
   case 'r':
    file[4].play(0.6f, 1.0f);
   break; 
   
   case 't':
    file[0].play(3.0f, 1.0f);
    break;
    
   case 'z':
    file[1].play(2.1f, 1.0f);
    break;
   
   case 'u':
    file[2].play(2.8f, 1.0f);
    break;    
   
   case 'i':
    file[3].play(3.7f, 1.0f);
   break;
   
   case 'o':
    file[4].play(3.0f, 1.0f);
    break;
   
   case 'p':
    file[0].play(3.8f, 1.0f);
    break;    
   
   case '\u00fc':
    file[1].play(4.0f, 1.0f);
   break;   
  }
}
class Particle {
  float x, y;
  float vx, vy;
  float r;

  Particle(float _x, float _y, float _vx, float _vy, float _r) {
    x = _x;
    y = _y;
    vx = _vx;
    vy = _vy;
    r = _r;
  }

  public void applyForces(float _fx, float _fy) {
    vy += _fy;
    vx += _fx;
    y += vy;
    x += vx;
  }

  public void display() {
    ellipse(x, y, r, r);
    stroke(frameCount * .3f % 255, frameCount * .5f % 255, frameCount * 6 % 255);
  }

}
int xspacing = 16;   // How far apart should each horizontal location be spaced
int w;              // Width of entire wave
float theta = 0.0f;  // Start angle at 0
float amplitude = 75.0f;  // Height of wave
float period = 500.0f;  // How many pixels before the wave repeats
float dx;  // Value for incrementing X, a function of period and xspacing
float[] yvalues;  // Using an array to store height values for the wave

public void calcWave() {
  // Increment theta (try different values for 'angular velocity' here
  theta += 0.02f;

  // For every x value, calculate a y value with sine function
  float x = theta;
  for (int i = 0; i < yvalues.length; i++) {
    yvalues[i] = sin(x)*amplitude;
    x+=dx;
  }
}

public void renderWave(float wave_rms) {
    noFill();
    strokeWeight(2);
    stroke(frameCount * .3f % 255, frameCount * .5f % 255, frameCount * 6 % 255);
    // A simple way to draw the wave with an ellipse at each location
    for (int x = 0; x < yvalues.length; x++) {
      //rect(x*xspacing, height/2+yvalues[x], .2*wave_rms, .8*wave_rms);
      rect(x*xspacing, height/2+yvalues[x], .2f*wave_rms, wave_rms);
      
  }
}
class BounceParticle extends Particle {
  float friction = -0.33f;
  
  BounceParticle(float _x, float _y, float _vx, float _vy, float _r) {
    super(_x, _y, _vx, _vy, _r);
  }
  
  public void applyForces(float _fx, float _fy) {
    super.applyForces(_fx, _fy);
    vx *= friction;
    vy *= friction;
    constrainParticles();
  }
  
  public void constrainParticles() {
    if (x < 0 || x > width) {
      vx = -vx;
      x = constrain(x, 0, width);
    }
    
    if (y < 0 || y > height) {
      vy = -vy;
      y = height;
    }
  }
}
class displayLines extends Particle {
  float friction = .93f;
  
  displayLines(float _x, float _y, float _vx, float _vy, float _r) {
    super(_x, _y, _vx, _vy, _r);
  }
  
  public void applyForces(float _fx, float _fy) {
    super.applyForces(_fx, _fy);
    vx *= friction;
    vy *= friction;
    constrainParticles();
  }
  
  public void constrainParticles() {
    if (x < 0 || x > width) {
      vx = -vx;
      x = constrain(x, 0, width);
    }
    
    if (y < 0 || y > height) {
      vy = -vy;
      y = height;
    }
  }
}
class Spring2D {

  //Spring properties
  float x, y;
  float vx, vy;
  float rx, ry;
  float m;
  float r = 10.0f;
  float ks = 0.1f;
  float kd = 0.1f;
  
  Spring2D(float _rx, float _ry, float _m) {
    rx = _rx;
    ry = _ry;
    m = _m;
  }
  
  //Set the actual position of the spring versus its rest position
  public void setPosition(float _x, float _y) {
    x = _x;
    y = _y;
  }
  
  //_fx, _fy are any additional forces we want to apply
  public void applyForces(float _fx, float _fy) {
    //Calculate acceleration, velocity and position on the x axis
    float fx = -((ks * (x - rx)) + kd*vx) + _fx;
    float ax = fx/m;
    vx += ax;
    x += vx;
    
    //Calculate acceleration, velocity and position on the y axis
    float fy = -((ks * (y - ry)) + kd*vy) + _fy;
    float ay = fy/m;
    vy += ay;
    y += vy;
  }
  
  public void display(float x1, float x2) {
    ellipse(x, y, r*2, r*2);
    line(x, y, x1, x2);
  }

}
  public void settings() {  size(640,360, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "interactive_music" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
