class Spring2D {

  //Spring properties
  float x, y;
  float vx, vy;
  float rx, ry;
  float m;
  float r = 10.0;
  float ks = 0.1;
  float kd = 0.1;
  
  Spring2D(float _rx, float _ry, float _m) {
    rx = _rx;
    ry = _ry;
    m = _m;
  }
  
  //Set the actual position of the spring versus its rest position
  void setPosition(float _x, float _y) {
    x = _x;
    y = _y;
  }
  
  //_fx, _fy are any additional forces we want to apply
  void applyForces(float _fx, float _fy) {
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
  
  void display(float x1, float x2) {
    ellipse(x, y, r*2, r*2);
    line(x, y, x1, x2);
  }

}