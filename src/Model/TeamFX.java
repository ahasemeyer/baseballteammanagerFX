/**
 * @author Austin Hasemeyer
 * @document ManagerFX.java
 * @description This Model class translates information from Java to JavaFX
 *      SimpleProperties. This is so information can be retrieved from SQL 
 *      then passed to Java, translated into JavaFX to be displayed in the GUI. 
 */
package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public final class TeamFX
{
    private SimpleIntegerProperty teamID;
    private SimpleIntegerProperty losses;
    private SimpleStringProperty teamName;
    private SimpleIntegerProperty ties;
    private SimpleDoubleProperty winPerc;
    private SimpleIntegerProperty wins;
     
    public TeamFX()
    {}
    
    public TeamFX(int inTeamID, int losses, String teamname, double winPerc, int wins, int ties)
    {
        this.setTeamID(inTeamID);
        this.setWins(wins);
        this.setLosses(losses);
        this.setTeamName(teamname);
        this.setWinPerc(winPerc);
        this.setTies(ties);
    }
    
    public String getTeamName()
    {
        return teamName.get(); 
    }
    
    public SimpleStringProperty getTeamNameProperty()
    {
        return teamName; 
    }
    
    public void setTeamName(String teamName)
    {
        this.teamName = new SimpleStringProperty(teamName);
    }
    
    public int getTeamID()
    {
        return teamID.get();
    }

    public SimpleIntegerProperty getTeamIDProperty()
    {
        return teamID;
    }
    
    public void setTeamID(int teamid)
    {
        this.teamID = new SimpleIntegerProperty(teamid);
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


