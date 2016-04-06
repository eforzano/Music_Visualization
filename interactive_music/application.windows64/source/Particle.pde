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

  void applyForces(float _fx, float _fy) {
    vy += _fy;
    vx += _fx;
    y += vy;
    x += vx;
  }

  void display() {
    ellipse(x, y, r, r);
    stroke(frameCount * .3 % 255, frameCount * .5 % 255, frameCount * 6 % 255);
  }

}