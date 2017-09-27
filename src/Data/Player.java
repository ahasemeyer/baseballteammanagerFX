package Data;

/**
 *
 * @author hasmy
 */

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLAYERID", nullable = false)
    private Integer playerid;
    @Column(name = "BATTINGSTANCE", length = 255)
    private String battingstance;
    @Column(name = "FNAME", length = 255)
    private String fname;
    @Column(name = "LNAME", length = 255)
    private String lname;
    @Column(name = "PLAYERNUMBER")
    private Integer playernumber;
    @Column(name = "POSITION", length = 255)
    private String position;
    @Column(name = "TEAMID")
    private Integer teamid;
    @Column(name = "THROWINGARM", length = 255)
    private String throwingarm;
      
    public Player()
    {
        fname = "John";
        lname = "Doe";
        position = "unassigned";
        playernumber = 999; 
        throwingarm = "R";
        battingstance = "S";
        teamid = 0;
    }
    
    public Player(int ID)
    {
        playerid = ID;
        fname = "John";
        lname = "Doe";
        position = "unassigned";
        playernumber = 999; 
        throwingarm = "R";
        battingstance = "S";
        teamid = 0;
        
    }
    
    public Player(String first, String last, String pos, int number, String arm, String stance, int team)
    {
        fname = first;
        lname = last;
        position = pos; 
        playernumber = number;
        throwingarm = arm;
        battingstance = stance; 
        teamid = team; 
    }
    
    public static Player loadPlayerData(int inPlayerID)
    {
        EntityManager em = Data.DBUtil.getEM();
        
        Query fNameQuery = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        fNameQuery.setParameter(1,inPlayerID);
        String inFname = (String)fNameQuery.getSingleResult();
        
        Query lNameQuery = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        lNameQuery.setParameter(1,inPlayerID);
        String inLname = (String)lNameQuery.getSingleResult();
        
        Query positionQuery = em.createNativeQuery("SELECT a.position FROM player a WHERE a.playerID=?");
        positionQuery.setParameter(1,inPlayerID);
        String inPosition = (String)positionQuery.getSingleResult();
        
        Query numberQuery = em.createNativeQuery("SELECT a.playernumber FROM player a WHERE a.playerID=?");
        numberQuery.setParameter(1,inPlayerID);
        int inNumber = (int)numberQuery.getSingleResult();
        
        Query armQuery = em.createNativeQuery("SELECT a.throwingarm FROM player a WHERE a.playerID=?");
        armQuery.setParameter(1,inPlayerID);
        String inArm = (String)armQuery.getSingleResult();
        //char inArm = armStr.charAt(0);
        
        Query stanceQuery = em.createNativeQuery("SELECT a.battingstance FROM player a WHERE a.playerID=?");
        stanceQuery.setParameter(1,inPlayerID);
        String inStance = (String)stanceQuery.getSingleResult();
        //char inStance = stanceStr.charAt(0);
        
        Query teamQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?");
        teamQuery.setParameter(1,inPlayerID);
        int inTeam = (int)teamQuery.getSingleResult();
                
        
        Player returnPlayer = new Player(inPlayerID);
        returnPlayer.updatePlayer(inFname, inLname, inPosition, inNumber, inArm, inStance, inTeam);
        return returnPlayer; 
    }
    
    public void updatePlayer(String first, String last, String pos, int number, String arm, String stance, int team)
    {
        setFname(first);
        setLname(last);
        setPosition(pos);
        setPlayernumber(number);
        setThrowingarm(arm);
        setBattingstance(stance);
        setTeamid(team);
    }
    
    
    public void setFname(String inName)
    {
        fname = inName;
    }
    
    public void setLname(String inName)
    {
        lname = inName;
    }
    
    public String getLname()
    {
        return lname;
    }
    
    public void setPosition(String inPosition)
    {
        position = inPosition;
    }
    
   
//    
//    public void setPlayerNumber(int inNumber)
//    {
//        playerNumber = inNumber;
//    }
//    
//    public int getPlayerNumber()
//    {
//        return playerNumber;
//    }
//    
//    public void setPlayerArm(String inArm)
//    {
//        throwingArm = inArm;
//    }
//    
//    public String getPlayerArm()
//    {
//        return throwingArm;
//    }
    
//    public void setPlayerStance(String inStance)
//    {
//        battingStance = inStance;
//    }
//    
//    public String getPlayerStance()
//    {
//        return battingStance; 
//    }
//    
//    public void setTeamID(int inTeamID)
//    {
//        teamID = inTeamID;
//    }
//    
//    public int getTeamID()
//    {
//        return teamID; 
//    }

    public Player(Integer playerid) {
        this.playerid = playerid;
    }

    public Integer getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Integer playerid) {
        this.playerid = playerid;
    }

    public String getBattingstance() {
        return battingstance;
    }

    public void setBattingstance(String battingstance) {
        this.battingstance = battingstance;
    }

    public String getFname() {
        return fname;
    }

    public Integer getPlayernumber() {
        return playernumber;
    }

    public void setPlayernumber(Integer playernumber) {
        this.playernumber = playernumber;
    }

    public String getPosition() {
        return position;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public String getThrowingarm() {
        return throwingarm;
    }

    public void setThrowingarm(String throwingarm) {
        this.throwingarm = throwingarm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (playerid != null ? playerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        if ((this.playerid == null && other.playerid != null) || (this.playerid != null && !this.playerid.equals(other.playerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Data.Player[ playerid=" + playerid + " ]";
    }
}
