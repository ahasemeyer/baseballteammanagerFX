package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author hasmy
 */

public final class ManagerFX
{
    private SimpleIntegerProperty playerID;
    private SimpleIntegerProperty losses;
    private SimpleIntegerProperty teamID;
    private SimpleIntegerProperty ties;
    private SimpleDoubleProperty winPerc;
    private SimpleIntegerProperty wins;
    
    public ManagerFX() {}
    
    public ManagerFX(int inPlayerID)
    {
        this.playerID = new SimpleIntegerProperty(inPlayerID);
    }
    
    public ManagerFX(int inPlayerID, int inTeamID, int inWins, int inLosses, int inTies, double inWinPerc)
    {
        this.setPlayerID(inPlayerID);
        this.setTeamID(inTeamID);
        this.setWins(inWins);
        this.setLosses(inLosses);
        this.setTies(inTies);
        this.setWinPerc(inWinPerc);
    }
    
    public int getPlayerID()
    {
        return playerID.get();
    }
    
    public SimpleIntegerProperty getPlayerIDProperty()
    {
        return playerID;
    }
    
    public void setPlayerID(int playerID)
    {
        this.playerID = new SimpleIntegerProperty(playerID);
    }
    
    public int getTeamID()
    {
        return teamID.get();
    }
    
    public SimpleIntegerProperty getTeamIDProperty()
    {
        return teamID;
    }
    
    public void setTeamID(int inTeamID)
    {
        teamID = new SimpleIntegerProperty(inTeamID);
    }
    
    public int getWins()
    {
        return wins.get();
    }
    
    public SimpleIntegerProperty getWinsProperty()
    {
        return wins;
    }
    
    public void setWins(int wins)
    {
        this.wins = new SimpleIntegerProperty(wins);
    }
    
    public int getLosses()
    {
        return losses.get();
    }
    
    public SimpleIntegerProperty getLossesProperty()
    {
        return losses;
    }
    
    public void setLosses(int losses)
    {
        this.losses = new SimpleIntegerProperty(losses);
    }
    
    public int getTies()
    {
        return ties.get();
    }
    
    public SimpleIntegerProperty getTiesProperty()
    {
        return ties; 
    }
    
    public void setTies(int ties)
    {
        this.ties = new SimpleIntegerProperty(ties);
    }
    
    public double getWinPerc()
    {
        return winPerc.get();
    }
    
    public SimpleDoubleProperty getWinPercProperty()
    {
        return winPerc;
    }
    
    public void setWinPerc(double winPerc)
    {
        this.winPerc = new SimpleDoubleProperty(winPerc);
    }
}


