import greenfoot.*;

/**
 * Write a description of class LevelTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LevelTwo implements IUpgradeChain
{
    // instance variables - replace the example below with your own
    private Mars world;
    
    private IUpgradeChain next;
    
    private Counter counter;
    
    private boolean isDone;
    
     private int worldWidth,worldHeight;
    

    /**
     * Constructor for objects of class LevelTwo
     */
    public LevelTwo(Mars world,Counter counter)
    {
        this.world = world;
        isDone = false;
        
        this.worldWidth = world.getWidth();
        this.worldHeight = world.getHeight();
        this.counter=counter;
        
    }

    
    public void prepare()
    {
        isDone = true;
        world.setBackground("weave.jpg");
      
        Rover rover = new Rover();
        world.addObject(rover, 171, 168);
        rover.addScoreObserver(counter);
        rover.addLifeObserver(counter);
        
            
        Drink drink = new Drink();
      
         for(int i=0;i<8;i++)
        {
            world.addObject(drink.cloneActor(), Greenfoot.getRandomNumber(worldWidth),Greenfoot.getRandomNumber(worldHeight));
        }
       
        
        Alien alien = new Alien();
        
        for(int i=0;i<4;i++)
        {
            world.addObject(alien.cloneActor(), Greenfoot.getRandomNumber(worldWidth),Greenfoot.getRandomNumber(worldHeight));
        }
        
        for(Alien s : world.getObjects(Alien.class))
        {
            s.addLifeObserver(counter);
        }
        
        O2 o2 = new O2();
        
        for(int i=0;i<3;i++)
        {
            world.addObject(o2.cloneActor(), Greenfoot.getRandomNumber(worldWidth),Greenfoot.getRandomNumber(worldHeight));
        }

    }
    
     public void setNext(IUpgradeChain next)
    {
     this.next = next;   
    }
    
    public void handleUpgrade()
    {
        if(isDone)
        {
            if(next != null)
            next.handleUpgrade();
            else
            world.gameOver();
        }
        else
        {
        prepare();
        }
     }
    
    public IUpgradeChain getNext()
    {
     if(this.next != null)
     return next;
     else
     return null;
    }
}
