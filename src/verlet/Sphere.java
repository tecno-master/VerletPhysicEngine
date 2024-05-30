package verlet;

public interface Sphere {
    void updatePosition(float dt);
    void accelerate(double x, double y);
    void setX(double x);
    void setY(double y);
    double getX();
    double getY();
    float getRadius();
    float getWeight();
}
