/**
   The Fleet class creates the fleet of ships
*/

public class Fleet
{
   // Fields
   private Ship battleShip;
   private Ship aircraftCarrier;
   private Ship cruiser;
   private Ship sub;
   private Ship destroyer;
   
   /**
      The constructor initializes the ships
   */
   
   public Fleet()
   {
     battleShip = new Battleship();
     aircraftCarrier = new AircraftCarrier();
     cruiser = new Cruiser();
     sub = new Sub();
     destroyer = new Destroyer();
   }
   
   /**
      The updateFleet method updates the fleet 
      @param Passes in the ship type
      @return Returns true
   */
   
   public boolean updateFleet(ShipType st)
   {
      if (st == ShipType.ST_SUB)
      {
         sub.hit();
         System.out.println("Submarine has been hit!");
         
         if(sub.getSunk())
         {
            System.out.println("Submarine sunk");
            return true;
         }
      }
      
      if (st == ShipType.ST_DESTROYER)
      {
         destroyer.hit();
         System.out.println("Destroyer has been hit!");
         
         if(destroyer.getSunk())
         {
            System.out.println("Destroyer sunk");
            return true;
         }
      }  
      
      if (st == ShipType.ST_BATTLESHIP)
      {
         battleShip.hit();
         System.out.println("Battleship has been hit!");
         
         if (battleShip.getSunk())
         {
            System.out.println("BattleShip Sunk!");
            return true;
         }
      }
      if (st == ShipType.ST_CRUISER)
      {
         cruiser.hit();
         System.out.println("Cruiser has been hit!");
         
         if (cruiser.getSunk())
         {
            System.out.println("Cruiser sunk!");
            return true;
         }
      }
      
      if (st == ShipType.ST_AIRCRAFT_CARRIER)
      {
         aircraftCarrier.hit();
         System.out.println("Aircraft carrier has been hit!");
         
         if (aircraftCarrier.getSunk())
         {
            System.out.println("Aircraft Carrier sunk!");
            return true;
         }
      }
         return true;
   }
   
   /**
      The gameOver method checks if all ships have been sunk
      @return Returns true if all ships are sunk
      @return Returns false if any ships remain
   */
   
   public boolean gameOver()
   {
      if (battleShip.getSunk() == true && aircraftCarrier.getSunk() == true
            && cruiser.getSunk() == true && sub.getSunk() == true &&
            destroyer.getSunk() == true)
               return true;
         
      else 
         return false;
        
   }
}