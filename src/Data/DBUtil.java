package Data;

import javax.persistence.*;
/**
 *
 * @author austin hasemeyer
 */
public class DBUtil {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("TeamManagerJavaFxPU");
    
    
    public static EntityManager getEM()
    {
        return emf.createEntityManager();
    }
    
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
}