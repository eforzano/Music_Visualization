int xspacing = 16;   // How far apart should each horizontal location be spaced
int w;              // Width of entire wave
float theta = 0.0;  // Start angle at 0
float amplitude = 75.0;  // Height of wave
float period = 500.0;  // How many pixels before the wave repeats
float dx;  // Value for incrementing X, a function of period and xspacing
float[] yvalues;  // Using an array to store height values for the wave

void calcWave() {
  // Increment theta (try different values for 'angular velocity' here
  theta += 0.02;

  // For every x value, calculate a y value with sine function
  float x = theta;
  for (int i = 0; i < yvalues.length; i++) {
    yvalues[i] = sin(x)*amplitude;
    x+=dx;
  }
}

void renderWave(float wave_rms) {
    noFill();
    strokeWeight(2);
    stroke(frameCount * .3 % 255, frameCount * .5 % 255, frameCount * 6 % 255);
    // A simple way to draw the wave with an ellipse at each location
    for (int x = 0; x < yvalues.length; x++) {
      //rect(x*xspacing, height/2+yvalues[x], .2*wave_rms, .8*wave_rms);
      rect(x*xspacing, height/2+yvalues[x], .2*wave_rms, wave_rms);
      
  }
}