import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Enemies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemies extends Characters
{
    protected int reward = 2;
    protected int speed = 2;
    
    public Enemies(){
        GreenfootImage img = this.getImage();
        img.scale(40, 40);
        this.setImage(img);
        this.setRotation(90);
    }
    
    public void act()
    {
        this.move(speed);
        World wrld = (MyWorld)this.getWorld();
        
        List<Player> pls = this.getNeighbours(100, true, Player.class);
        if(pls.size()>0){
            this.turnTowards(pls.get(0).getX(),pls.get(0).getY());
        }
        
        if(this.isTouching(Bullets.class)){
            ScoreBoard scoreBoard = wrld.getObjects(ScoreBoard.class).get(0);
            scoreBoard.addScore(reward);
            this.removeTouching(Bullets.class);
            wrld.removeObject(this);
            return;
        }
        
        if(this.getY() == wrld.getHeight()-1){
            wrld.removeObject(this);
        }
    }
}
