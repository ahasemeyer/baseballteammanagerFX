/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 *
 * @author hasmy
 */
@Entity
@Table(name = "team")
public class Team implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int teamID;
    private String teamName;
    private int wins;
    private int losses;
    private int ties; 
    private double winPerc;
    
    
    public Team()
    {
    }
    
    public Team(String inTeamName)
    {
        teamName = inTeamName;
        wins = 0;
        losses = 0;
        ties = 0;
        winPerc = 0;
    }
    
    public Team(int inTeamID)
    {
        teamID = inTeamID; 
        teamName = "";
        wins = 0;
        losses = 0;
        ties = 0;
        winPerc = 0;
    }
    
    public Team(int inTeamID, String name)
    {
        teamID = inTeamID; 
        teamName = name;
        wins = 0;
        losses = 0;
        ties = 0;
        winPerc = 0;
    }
    
    public static Team loadTeamData(int ID)
    {
        EntityManager em = Data.DBUtil.getEM();
        
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


