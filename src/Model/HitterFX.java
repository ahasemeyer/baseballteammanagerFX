/**
 * @author Austin Hasemeyer
 * @document HitterFX.java
 * @description This Model class translates information from Java to JavaFX
 *      SimpleProperties. This is so information can be retrieved from SQL 
 *      then passed to Java, translated into JavaFX to be displayed in the GUI. 
 */
package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class HitterFX 
{
    private SimpleIntegerProperty playerid; 
    private SimpleIntegerProperty atbats; 
    private SimpleIntegerProperty baseonballs;
    private SimpleDoubleProperty battingavg;  
    private SimpleIntegerProperty caughtstealing;
    private SimpleIntegerProperty doublehit; 
    private SimpleIntegerProperty hitbypitch;
    private SimpleIntegerProperty hits; 
    private SimpleIntegerProperty homeruns; 
    private SimpleDoubleProperty onbaseperc;
    private SimpleIntegerProperty sacfly; 
    private SimpleDoubleProperty slugging; 
    private SimpleDoubleProperty stealingperc; 
    private SimpleIntegerProperty stolenbases;
    private SimpleIntegerProperty strikeouts; 
    private SimpleIntegerProperty totalbases;  
    private SimpleIntegerProperty triplehit; 
    private SimpleIntegerProperty rbis;
        
    public HitterFX()
    {}
    
    public HitterFX(int ID)
    {
        this.playerid = new SimpleIntegerProperty(ID);
    }
    
    public HitterFX(int ID, int AB, int BB, double BA, int CS, int B2, int HBP, double OPC, int sac,
            double SLUG, double SBPerc, int SB, int SO, int TB, int B3, int H, int HR, int RBI)
    {
        this.setPlayerID(ID);
        this.setAtbats(AB);
        this.setBB(BB);
        this.setBattingAvg(BA);
        this.setCaughtstealing(CS);
        this.setDoublehit(B2);
        this.setHBP(HBP);
        this.setOBP(OPC);
        this.setSacFly(sac);
        this.setSlugging(SLUG);
        this.setStealingPerc(SBPerc);
        this.setStolenbases(SB);
        this.setStrikeouts(SO);
        this.setTotalBases(TB);
        this.setTriplehit(B3);
        this.setHits(H);
        this.setHomeruns(HR);
        this.setRBI(RBI);
    }
    
    public int getPlayerID()
    {
        return playerid.get(); 
    }

    public SimpleIntegerProperty getPlayerIDProperty()
    {
        return playerid; 
    }
    
    public void setPlayerID(int ID)
    {
        this.playerid = new SimpleIntegerProperty(ID);
    }
    
    public Integer getRBI()
    {
        return rbis.get();
    }
    
    public void setRBI(int inRBI)
    {
        this.rbis = new SimpleIntegerProperty(inRBI);
    }
    
    public SimpleIntegerProperty getRBIProperty()
    {
        return rbis;
    }
    
    public Integer getAtbats() 
    {
        return atbats.get();
    }
    
    public SimpleIntegerProperty getAtBatsProperty()
    {
        return atbats; 
    }

    public void setAtbats(Integer atbats) 
    {
        this.atbats = new SimpleIntegerProperty(atbats);
    }

    public Integer getBB()
    {
        return baseonballs.get(); 
    }
    
    public SimpleIntegerProperty getBBProperty()
    {
        return baseonballs; 
    }
    
    public void setBB(Integer baseonballs) 
    {
        this.baseonballs = new SimpleIntegerProperty(baseonballs);
    }
    
    public double getBattingAvg()
    {
        return battingavg.get();
    }
    
    public SimpleDoubleProperty getBattingAvgProperty()
    {
        return battingavg; 
    }
    
    public void setBattingAvg(double BA)
    {
        this.battingavg = new SimpleDoubleProperty(BA);
    }
    
    public Integer getCaughtStealing()
    {
        return caughtstealing.get();
    }
    
    public SimpleIntegerProperty getCaughtStealingProperty()
    {
        return caughtstealing; 
    }

    public void setCaughtstealing(Integer caughtstealing) 
    {
        this.caughtstealing = new SimpleIntegerProperty(caughtstealing);
    }
    
    public Integer getDoubles()
    {
        return doublehit.get(); 
    }
    
    public SimpleIntegerProperty getDoublesProperty()
    {
        return doublehit; 
    }

    public void setDoublehit(Integer doublehit) 
    {
        this.doublehit = new SimpleIntegerProperty(doublehit);
    }
    
    public Integer getHBP()
    {
        return hitbypitch.get(); 
    }

    public SimpleIntegerProperty getHBPProperty()
    {
        return hitbypitch; 
    }
    
    public void setHBP(Integer hitbypitch) 
    {
        this.hitbypitch = new SimpleIntegerProperty(hitbypitch);
    }
    
    public Integer getHits()
    {
        return hits.get(); 
    }
    
    public SimpleIntegerProperty getHitsProperty()
    {
        return hits; 
    }

    public void setHits(int inHit)
    {
        this.hits = new SimpleIntegerProperty(inHit);
    }
    
    public Integer getHomeruns()
    {
        return homeruns.get(); 
    }
    
    public SimpleIntegerProperty getHomerunsProperty()
    {
        return homeruns;
    }
   
    public void setHomeruns(Integer homeruns) 
    {
        this.homeruns = new SimpleIntegerProperty(homeruns);
    }
    
    public double getOBP()
    {
        return onbaseperc.get(); 
    }
    
    public SimpleDoubleProperty getOBPProperty()
    {
        return onbaseperc;
    }
    
    public void setOBP(double inOBP)
    {
        this.onbaseperc = new SimpleDoubleProperty(inOBP);
    }      
    
    public int getSacFly()
    {
        return sacfly.get();
    }
    
    public SimpleIntegerProperty getSacFlyProperty()
    {
        return sacfly;
    }
    
    public void setSacFly(int inFly)
    {
        this.sacfly = new SimpleIntegerProperty(inFly);
    }
    
    public double getSlugging()
    {
        return slugging.get(); 
    }
    
    public SimpleDoubleProperty getSluggingProperty()
    {
        return slugging;
    }
    
    public void setSlugging(double inSlug)
    {
        this.slugging = new SimpleDoubleProperty(inSlug);
    }
    
    public double getStealingPerc()
    {
        return stealingperc.get(); 
    }
    
    public SimpleDoubleProperty getStealingPercProperty()
    {
        return stealingperc; 
    }
    
    public void setStealingPerc(double inPerc)
    {
        this.stealingperc = new SimpleDoubleProperty(inPerc);
    }
    
    public Integer getStolenBases()
    {
        return stolenbases.get(); 
    }
    
    public SimpleIntegerProperty getStolenBasesProperty()
    {
        return stolenbases;
    }
    
    public void setStolenbases(Integer stolenbases) 
    {
        this.stolenbases = new SimpleIntegerProperty(stolenbases);
    }
    
    public Integer getStrikeouts()
    {
        return strikeouts.get(); 
    }
    
    public SimpleIntegerProperty getStrikeoutsProperty()
    {
        return strikeouts; 
    }
    public void setStrikeouts(Integer strikeouts) 
    {
        this.strikeouts = new SimpleIntegerProperty(strikeouts);
    }
    
    public int getTotalBases()
    {
        return totalbases.get(); 
    }
    
    public SimpleIntegerProperty getTotalBasesProperty()
    {
        return totalbases;
    }
    
    public void setTotalBases(int inBases)
    {
        this.totalbases = new SimpleIntegerProperty(inBases);
    }

    public Integer getTriples()
    {
        return triplehit.get(); 
    }
    
    public SimpleIntegerProperty getTriplesProperty()
    {
        return triplehit; 
    }
    
    public void setTriplehit(Integer triplehit) 
    {
        this.triplehit = new SimpleIntegerProperty(triplehit);
    }  
}


