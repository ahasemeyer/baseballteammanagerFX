/**
 * @author Austin Hasemeyer
 * @document DBUtil.java
 * @description This is a Utility class which handles all interactions with 
 *      the SQL database. These include connecting and passing information. 
 */
package Model;

import javax.persistence.*;

public class DBUtil 
{    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("TeamManagerJavaFxPU");
    
    //Pre: persistence.xml must be configured
    //Post: returns an EntityManager 
    public static EntityManager getEM()
    {
        return emf.createEntityManager();
    }
    
    //Pre: team name must be unique
    //Post: Creates a team record in SQL database
    public static Team createTeam(String teamName)
    {
        try {
            System.out.println("create Team record start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            Team t = new Team(teamName);
            em.persist(t);
            em.getTransaction().commit();
            em.close();
            System.out.println("create Team record ended.");
            return t;
        } catch (Exception e){
            System.out.println("Exception in create Team "+e.getMessage());
            e.printStackTrace();
            return null;
        }        
    }
    
    //Pre: Team object must be created from existing SQL record
    //Post: updates team in SQL database 
    public static Team updateTeam(Team t)
    {
        try {
            System.out.println("Update Team start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            em.close();
            System.out.println("Update Team ended.");
            return t;
        } catch (Exception e){
            System.out.println("Exception in Update Team "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    //Pre: must pass ID Player to make Manager, ID must already exist
    //Post: Creates a manager record in SQL database, with no team assigned
    public static Manager createManager(int inPlayerID)
    {
        try {
            System.out.println("create Manager record start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            Manager m = new Manager(inPlayerID);
            em.persist(m);
            em.getTransaction().commit();
            em.close();
            System.out.println("create Manager record ended.");
            return m;
        } catch (Exception e){
            System.out.println("Exception in create Manager "+e.getMessage());
            e.printStackTrace();
            return null;
        }        
    }
    
    //Pre: must pass Player ID to make Manager, ID must already exist
    //     must pass Team ID to assign team, ID must already exist
    //Post: Creates a manager record in SQL database, with team assignment
    public static Manager createManager(int inPlayerID, int inTeamID)
    {
        try {
            System.out.println("create Manager record start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            Manager m = new Manager(inPlayerID, inTeamID);
            em.persist(m);
            em.getTransaction().commit();
            em.close();
            System.out.println("create Manager record ended.");
            return m;
        } catch (Exception e){
            System.out.println("Exception in create Manager "+e.getMessage());
            e.printStackTrace();
            return null;
        }        
    }
    
    //Pre: Manager object with existing SQL database information
    //Post: Update SQL record for the existing Manager
    public static Manager updateManager(Manager m)
    {
        try {
            System.out.println("Update Manager start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
            em.close();
            System.out.println("Update Manager ended.");
            return m;
        } catch (Exception e){
            System.out.println("Exception in Update Manager "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    //Pre: no precondition
    //Post: creates a default player with default object variables
    public static Player createPlayer()
    {
        try {
            System.out.println("create Player Table start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            Player h = new Player();
            em.persist(h);
            em.getTransaction().commit();
            em.close();
            System.out.println("create Player Table ended.");
            return h;
        } catch (Exception e){
            System.out.println("Exception in create Player "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    //Pre: all parameters must be set accordingly, no parameters must be unique all must be entered
    //Post: A player record will be created in SQL with all fields provided, will produce a playerID
    public static Player createPlayer(String first, String last, String pos, int number, String arm, String stance, int team)
    {
        try {
            System.out.println("create Player record start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            Player h = new Player(first, last, pos, number, arm, stance, team);
            em.persist(h);
            em.getTransaction().commit();
            em.close();
            System.out.println("create Player record ended.");
            return h;
        } catch (Exception e){
            System.out.println("Exception in create Player "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    //Pre: playerID must correlate to an existing record
    //Post: Records in all tables that match with playerID will be deleted from SQL
    public static String deletePlayer(int playerID)
    {
        EntityManager em = DBUtil.getEM();
        System.out.println("Delete Player Started.");   
        em.getTransaction().begin();
        String returnString = "";
        Query deletePlayerQuery = em.createQuery("DELETE FROM Player c WHERE c.playerid = :p");
        int deletedPlayers = deletePlayerQuery.setParameter("p", playerID).executeUpdate();
        Query deletePitcherQuery = em.createQuery("DELETE FROM Pitcher c WHERE c.playerID = :p");
        int deletedPitcher = deletePitcherQuery.setParameter("p", playerID).executeUpdate();
        Query deleteHitterQuery = em.createQuery("DELETE FROM Hitter c WHERE c.playerid = :p");
        int deletedHitter = deleteHitterQuery.setParameter("p", playerID).executeUpdate();
        Query deleteManagerQuery = em.createQuery("DELETE FROM Manager c WHERE c.playerID = :p");
        int deletedManager = deleteManagerQuery.setParameter("p", playerID).executeUpdate();
        em.getTransaction().commit();
        em.close();
        returnString = deletedPlayers+" Player record(s) has been deleted.\n"+
            deletedPitcher+" Pitcher record(s) has been deleted.\n"+
            deletedHitter+" Hitter record(s) has been deleted.\n"+
            deletedManager+" Manager record(s) has been deleted.";
        
        return returnString;
    }
    
    //Pre: Player object must be loaded with current player information then updated.
    //Post: SQL record matching passed Player will be updated. 
    public static Player updatePlayer(Player p)
    {
        try {
            System.out.println("Update Player started.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            em.close();
            System.out.println("Update Player ended.");
            return p;
        } catch (Exception e){
            System.out.println("Exception in Update Player "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }  

    //Pre: playerID must be unique and correlate to existing SQL record.
    //Post: Hitter record will be created in SQL.
    public static Hitter createHitter(int playerID)
    {
        try {
            System.out.println("create Hitter record start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            Hitter h = new Hitter(playerID);
            em.persist(h);
            em.getTransaction().commit();
            em.close();
            System.out.println("create Hitter record ended.");
            return h;
        } catch (Exception e){
            System.out.println("Exception in create Hitter "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    //Pre: Hitter object must be loaded from SQL record then updated.
    //Post: SQL record matching passed hitter will be updated. 
    public static Hitter updateHitter(Hitter h)
    {
        try {
            System.out.println("Update Hitter start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            em.merge(h);
            em.getTransaction().commit();
            em.close();
            System.out.println("Update Hitter ended.");
            return h;
        } catch (Exception e){
            System.out.println("Exception in Update Hitter "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    //Pre: playerID must be unique and must come from existing Player record.
    //Post: Pitcher record will be created in SQL database. 
    public static Pitcher createPitcher(int playerID)
    {
        try {
            System.out.println("create Pitcher record start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            Pitcher p = new Pitcher(playerID);
            em.persist(p);
            em.getTransaction().commit();
            em.close();
            System.out.println("create Pitcher record ended.");
            return p;
        }catch (Exception e){
            System.out.println("Exception in create Pitcher "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    //Pre: Pitcher object must be loaded from SQL record then updated.
    //Post: SQL record will be updated. 
    public static Pitcher updatePitcher(Pitcher p)
    {
        try {
            System.out.println("Update Pitcher start.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            em.close();
            System.out.println("Update Pitcher finished.");
            return p;
        } catch (Exception e){
            System.out.println("Exception in Update Pitcher "+e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    public static void createUser(AppUsers user)
    {
        try {
            System.out.println("Create user started.");
            EntityManager em = getEM();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
            System.out.println("Create user finished.");
        } catch (Exception e){
            System.out.println("Exception in create User "+e.getMessage());
        }
    }
}