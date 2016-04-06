class displayLines extends Particle {
  float friction = .93;
  
  displayLines(float _x, float _y, float _vx, float _vy, float _r) {
    super(_x, _y, _vx, _vy, _r);
  }
  
  void applyForces(float _fx, float _fy) {
    super.applyForces(_fx, _fy);
    vx *= friction;
    vy *= friction;
    constrainParticles();
  }
  
  void constrainParticles() {
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