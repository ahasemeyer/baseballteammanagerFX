package Model;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public final class PlayerFX
{    
    private SimpleIntegerProperty playerid;
    private SimpleStringProperty battingstance;
    private SimpleStringProperty fname;
    private SimpleStringProperty lname;
    private SimpleIntegerProperty playernumber;
    private SimpleStringProperty position;
    private SimpleIntegerProperty teamid;
    private SimpleStringProperty throwingarm;
    
    public PlayerFX()
    {}
    
    public PlayerFX(Integer ID)
    {
        this.playerid = new SimpleIntegerProperty(ID);
    }
    
    public PlayerFX(Integer ID, String first, String last, String pos, Integer number, String arm, String stance, Integer team)
    {
        setPlayerid(ID);
        setFname(first);
        setLname(last);
        setPosition(pos);
        setPlayernumber(number);
        setThrowingarm(arm);
        setBattingstance(stance);
        setTeamid(team);
    }
    
    public PlayerFX(SimpleStringProperty first, SimpleStringProperty last, SimpleStringProperty pos, 
            SimpleIntegerProperty number, SimpleStringProperty arm, SimpleStringProperty stance, SimpleIntegerProperty team)
    {
        fname = first;
        lname = last;
        position = pos; 
        playernumber = number;
        throwingarm = arm;
        battingstance = stance; 
        teamid = team; 
    }
    
    public void setFname(String inName)
    {
        this.fname = new SimpleStringProperty(inName);
    }
    
    public String getFname()
    {
        return fname.get();
    }
    
    public SimpleStringProperty getFnameProperty() 
    {
        return fname;
    }
    
    public void setLname(String inName)
    {
        this.lname = new SimpleStringProperty(inName);
    }
    
    public String getLname()
    {
        return lname.get();
    }
    
    public SimpleStringProperty getLnameProperty()
    {
        return lname; 
    }
    
    public void setPosition(String inPosition)
    {
        this.position = new SimpleStringProperty(inPosition);
    }
    
    public String getPosition()
    {
        return position.get();
    }
    
    public SimpleStringProperty getPositionProperty()
    {
        return position; 
    }

    public void setPlayerid(Integer playerid) 
    {
        this.playerid = new SimpleIntegerProperty(playerid);
    }
    
    public Integer getPlayerid() 
    {
        return playerid.get();
    }
    
    public SimpleIntegerProperty getPlayeridProperty()
    {
        return playerid; 
    }

    public void setBattingstance(String battingstance) 
    {
        this.battingstance = new SimpleStringProperty(battingstance);
    }
    
    public String getBattingstance() 
    {
        return battingstance.get();
    }
    
    public SimpleStringProperty getBattingstanceProperty()
    {
        return battingstance; 
    }

    public void setPlayernumber(Integer playernumber) 
    {
        this.playernumber = new SimpleIntegerProperty(playernumber);
    }

    public Integer getPlayernumber() 
    {
        return playernumber.get();
    }
    
    public SimpleIntegerProperty getPlayernumberProperty()
    {
        return playernumber; 
    }

    public void setTeamid(Integer teamid) 
    {
        this.teamid = new SimpleIntegerProperty(teamid);
    }
    
    public Integer getTeamid() 
    {
        return teamid.get();
    }
    
    public SimpleIntegerProperty getTeamidProperty()
    {
        return teamid; 
    }

    public void setThrowingarm(String throwingarm) 
    {
        this.throwingarm = new SimpleStringProperty(throwingarm);
    }
    
    public String getThrowingarm() 
    {
        return throwingarm.get();
    }

    public SimpleStringProperty getThrowingarmProperty()
    {
        return throwingarm; 
    }
}
