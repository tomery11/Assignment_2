
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.Random;

public class Ball {
    private Point center;
    private int r;
    private java.awt.Color color;
    private  Velocity velocity;

    public Ball(Point center, int r, java.awt.Color color){
        this.center = center;
        this.r = r;
        this.color = color;
    }

    public Ball(int i, int i1, int i2, Color red) {
        this.center = new Point(i, i1);
        this.r = i2;
        this.color = red;
    }


    public int getX(){
        return (int)this.center.getX();
    }
    public int getY(){
        return (int)this.center.getY();
    }
    public int getSize(){
        return this.r;
    }
    public java.awt.Color getColor(){
        return this.color;
    }

    public void drawOn(DrawSurface surface){
        surface.setColor(this.color);
        surface.fillCircle(this.getX(),this.getY(),this.r);
    }


    public void setVelocity(Velocity v){
        this.velocity = v;
    }
    public void setVelocity(double dx, double dy){
        this.velocity = new Velocity(dx,dy);
    }
    public Velocity getVelocity(){
        return this.velocity;
    }

    public void moveOneStep() {


        this.center = this.getVelocity().applyToPoint(this.center);
        if (this.center.getX() - this.r < 0){
            this.velocity.setVelocity(-this.velocity.get_dx(), this.velocity.get_dy());
            this.center = new Point(this.r, this.center.getY());
        }
        else if (this.center.getX() + this.r > 200){
            this.velocity.setVelocity(-this.velocity.get_dx(), this.velocity.get_dy());
            this.center = new Point(200 - this.r, this.center.getY());
        }
        if (this.center.getY() - this.r < 0){
            this.velocity.setVelocity(this.velocity.get_dx(), -this.velocity.get_dy());
            this.center = new Point(this.center.getX(), this.r);
        }
        else if (this.center.getY() + this.r > 200){
            this.velocity.setVelocity(this.velocity.get_dx(), -this.velocity.get_dy());
            this.center = new Point(this.center.getX(), 200 - this.r);
        }



    }


    public static void main(String[] args) {
        GUI gui = new GUI("title",200,200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

}
