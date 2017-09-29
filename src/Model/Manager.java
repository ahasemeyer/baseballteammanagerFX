/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 *
 * @author hasmy
 */
@Entity
@Table(name = "manager")
public class Manager implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PLAYERID", nullable = false)
    private Integer playerID;
    @Column(name = "LOSSES")
    private Integer losses;
    @Column(name = "TEAMID")
    private Integer teamID;
    @Column(name = "TIES")
    private Integer ties;
    @Column(name = "WINPERC", precision = 22)
    private Double winPerc;
    @Column(name = "WINS")
    private Integer wins;
    
    public Manager()
    {playerID=888; teamID=0; wins=0; losses=0; ties=0; winPerc=0.0;}
    
    public Manager(int inPlayerID)
    {
        playerID = inPlayerID;
        teamID = 0;
        losses = 0;
        wins = 0;
        ties = 0; 
        winPerc = 0.0;
    }
    
    public Manager(int inPlayerID, int inTeamID)
    {
        playerID = inPlayerID;
        teamID = inTeamID;
        wins = 0;
        losses = 0;
        ties = 0;
        winPerc = 0.0;
    }
    
    public void updateManager(int inWins, int inLosses, int inTies)
    {
        updateWins(inWins);
        updateLosses(inLosses);
        updateTies(inTies);
        updateWinPerc();
    }
    
    public static Manager loadManagerData(int inPlayerID)
    {
        EntityManager em = Model.DBUtil.getEM();
        
        Query wins = em.createNativeQuery("SELECT a.wins FROM manager a WHERE a.playerID=?");
        wins.setParameter(1,inPlayerID);
        int inWins = (int)wins.getSingleResult();  
        
        Query losses = em.createNativeQuery("SELECT a.losses FROM manager a WHERE a.playerID=?");
        losses.setParameter(1,inPlayerID);
        int inLosses = (int)losses.getSingleResult(); 
        
        Query ties = em.createNativeQuery("SELECT a.ties FROM manager a WHERE a.playerID=?");
        ties.setParameter(1,inPlayerID);
        int inTies = (int)ties.getSingleResult(); 
        
        Query teamID = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?");
        teamID.setParameter(1,inPlayerID);
        int inTeamID = (int)teamID.getSingleResult(); 
        
        Manager returnManager = new Manager(inPlayerID, inTeamID);
        returnManager.updateManager(inWins, inLosses, inTies);
        
        return returnManager; 
    }
    
    public int getPlayerID()
    {
        return playerID;
    }
    
    public int getTeamID()
    {
        return teamID;
    }
    
    public void setTeamID(int inTeamID)
    {
        teamID = inTeamID;
    }
    
    public int getWins()
    {
        return wins;
    }
    
    public void updateWins(int inWins)
    {
        wins += inWins;
    }
    
    public int getLosses()
    {
        return losses;
    }
    
    public void updateLosses(int inLosses)
    {
        losses += inLosses;
    }
    
    public int getTies()
    {
        return ties;
    }
    
    public void updateTies(int inTies)
    {
        ties += inTies; 
    }
    
    public double getWinPerc()
    {
        return winPerc;
    }
    
    public void updateWinPerc()
    {
        double totalGames = wins + losses + ties;
        if(totalGames == 0)
            winPerc = 0.0;
        else
            winPerc = (double)wins/totalGames;
    }
    
    
    public void printStats()
    {
        System.out.println("PlayerID: "+playerID+" TeamID: "+teamID+" Wins: "+wins+" Losses: "+losses+" Ties: "+
                ties+" Win Percentage: "+(String.format("%.3f", winPerc))); 
    }
    
    public String returnStats()
    {
        String returnString ="TeamID: "+teamID+"  |  W: "+wins+"  |  L: "+losses+"  |  T: "+
                ties+"  |  Win Percentage: "+(String.format("%.3f", winPerc)); 
        return returnString; 
    }
}


