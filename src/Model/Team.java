/**
 * @author Austin Hasemeyer
 * @document Player.java
 * @description This Model class represents Team. Teams are the only class that
 *      does not require a player object to be created. Players must have a team
 *      but teams do not require players. 
 */
package Model;

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
@Table(name = "team")
public class Team implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEAMID", nullable = false)
    private Integer teamID;
    @Column(name = "LOSSES")
    private Integer losses;
    @Column(name = "TEAMNAME", length = 255)
    private String teamName;
    @Column(name = "TIES")
    private Integer ties;
    @Column(name = "WINPERC", precision = 22)
    private Double winPerc;
    @Column(name = "WINS")
    private Integer wins;
    
    //default constructor
    public Team()
    {}
    
    //Pre: inTeamName must be unique
    //Post: a team object is created
    public Team(String inTeamName)
    {
        teamName = inTeamName;
        wins = 0;
        losses = 0;
        ties = 0;
        winPerc = 0.0;
    }
    
    //Pre: inTeamID must be generated and unique
    //Post: creates a team with a teamID, used for testing
    public Team(int inTeamID)
    {
        teamID = inTeamID; 
        teamName = "";
        wins = 0;
        losses = 0;
        ties = 0;
        winPerc = 0.0;
    }
    
    //Pre: inTeamID must unique and generated, Team name must also be unique
    //Post: team object is created with a teamID and teamName
    public Team(int inTeamID, String name)
    {
        teamID = inTeamID; 
        teamName = name;
        wins = 0;
        losses = 0;
        ties = 0;
        winPerc = 0.0;
    }
    
    //Pre: param ID must already exist in the SQL database
    //Post: a Team object is loaded with data from the SQL database and returned
    public static Team loadTeamData(int ID)
    {
        EntityManager em = Model.DBUtil.getEM();
        
        Query winsQuery = em.createNativeQuery("SELECT a.wins FROM team a WHERE a.teamid=?");
        winsQuery.setParameter(1,ID);
        int inWins = (int)winsQuery.getSingleResult();   
        
        Query lossesQuery = em.createNativeQuery("SELECT a.losses FROM team a WHERE a.teamid=?");
        lossesQuery.setParameter(1,ID);
        int inLosses = (int)lossesQuery.getSingleResult(); 
        
        Query tiesQuery = em.createNativeQuery("SELECT a.ties FROM team a WHERE a.teamid=?");
        tiesQuery.setParameter(1,ID);
        int inTies = (int)tiesQuery.getSingleResult(); 
    
        Team returnTeam = new Team(ID);
        returnTeam.updateTeam(inWins, inLosses, inTies);
        return returnTeam;  
    }
    
    public String getTeamName()
    {
        return teamName; 
    }
    
    public int getTeamID()
    {
        return teamID;
    }
    
    public int getLosses()
    {
        return losses; 
    }
    
    public double getWinPerc()
    {
        return winPerc;
    }
    
    public int getWins()
    {
        return wins;
    }
    
    public int getTies()
    {
        return ties; 
    }
    
    public void updateTeam(int inWins, int inLosses, int inTies)
    {
        updateWins(inWins);
        updateLosses(inLosses);
        updateTies(inTies);
        updateWinPerc(); 
    }
    
    public void setName(String inName)
    {
        teamName = inName; 
    }
    
    public void updateWinPerc()
    {
        int totalGames = wins + losses + ties; 
        if(totalGames > 0)
            winPerc = wins/(double)totalGames;
        else
            winPerc = 0.0; 
    }
    
    public void updateWins(int inWins)
    {
        wins += inWins;
    }
    
    public void updateLosses(int inLosses)
    {
        losses += inLosses;
    }
    
    public void updateTies(int inTies)
    {
        ties += inTies; 
    }
}


