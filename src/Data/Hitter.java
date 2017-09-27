/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;


/**
 *
 * @author hasmy
 */
@Entity
@Table(name = "hitter")
public class Hitter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PLAYERID")
    private Integer playerid;
    @Column(name = "ATBATS")
    private Integer atbats;
    @Column(name = "BASEONBALLS")
    private Integer baseonballs;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "BATTINGAVG")
    private Double battingavg;
    @Column(name = "CAUGHTSTEALING")
    private Integer caughtstealing;
    @Column(name = "DOUBLEHIT")
    private Integer doublehit;
    @Column(name = "HITBYPITCH")
    private Integer hitbypitch;
    @Column(name = "HITS")
    private Integer hits;
    @Column(name = "HOMERUNS")
    private Integer homeruns;
    @Column(name = "ONBASEPERC")
    private Double onbaseperc;
    @Column(name = "SACFLY")
    private Integer sacfly;
    @Column(name = "SLUGGING")
    private Double slugging;
    @Column(name = "STEALINGPERC")
    private Double stealingperc;
    @Column(name = "STOLENBASES")
    private Integer stolenbases;
    @Column(name = "STRIKEOUTS")
    private Integer strikeouts;
    @Column(name = "TOTALBASES")
    private Integer totalbases;
    @Column(name = "TRIPLEHIT")
    private Integer triplehit;
        
    public Hitter()
    {
        playerid = 999; 
        atbats = 0;
        hits = 0;
        baseonballs = 0;
        hitbypitch = 0;
        doublehit = 0;
        triplehit = 0;
        homeruns = 0;
        sacfly = 0; 
        strikeouts = 0;
        stolenbases = 0;
        caughtstealing = 0;
        stealingperc = 0.00;
        battingavg = 0.00;
        slugging = 0.00;
        onbaseperc = 0.00; 
        totalbases = 0; 
    }
    
    
    public Hitter(int ID)
    {
        playerid = ID;
        atbats = 0;
        hits = 0;
        baseonballs = 0;
        hitbypitch = 0;
        doublehit = 0;
        triplehit = 0;
        homeruns = 0;
        sacfly = 0; 
        strikeouts = 0;
        stolenbases = 0;
        caughtstealing = 0;
        stealingperc = 0.00;
        battingavg = 0.00;
        slugging = 0.00;
        onbaseperc = 0.00; 
        totalbases = 0; 
    }
    
    public void updateHitter(int AB, int H, int B2, int B3, int HR, int BB, int HBP, int Sac, int SO, int CS, int SB)
    {
       updateAB(AB);
       updateHits(H);
       updateDoubles(B2);
       updateTriples(B3);
       updateHomeruns(HR);
       updateBB(BB);
       updateHBP(HBP);
       updateSac(Sac);
       updateStrikeouts(SO);
       updateCaughtStealing(CS);
       updateStolenBases(SB);
       updateTotalBases();
       updateAvg();
       updateStealingPerc();
       updateSlugging(); 
       updateOBP(); 
       
       
       
    }
    
    public static Hitter loadHitterData(int inPlayerID)
    {
        EntityManager em = Data.DBUtil.getEM();
        
        Query AB = em.createNativeQuery("SELECT a.atbats FROM hitter a WHERE a.playerID=?");
        AB.setParameter(1,inPlayerID);
        int inAB = (int)AB.getSingleResult();
       
        Query H = em.createNativeQuery("SELECT a.hits FROM hitter a WHERE a.playerID=?");
        H.setParameter(1,inPlayerID);
        int inH = (int)H.getSingleResult();
        
        Query B2 = em.createNativeQuery("SELECT a.doublehit FROM hitter a WHERE a.playerID=?");
        B2.setParameter(1,inPlayerID);
        int inB2 = (int)H.getSingleResult();

        Query B3 = em.createNativeQuery("SELECT a.triplehit FROM hitter a WHERE a.playerID=?");
        B3.setParameter(1,inPlayerID);
        int inB3 = (int)B3.getSingleResult();        

        Query HR = em.createNativeQuery("SELECT a.homeruns FROM hitter a WHERE a.playerID=?");
        HR.setParameter(1,inPlayerID);
        int inHR = (int)HR.getSingleResult();      
        
        Query BB = em.createNativeQuery("SELECT a.baseonballs FROM hitter a WHERE a.playerID=?");
        BB.setParameter(1,inPlayerID);
        int inBB = (int)BB.getSingleResult();
        
        Query HBP = em.createNativeQuery("SELECT a.hitbypitch FROM hitter a WHERE a.playerID=?");
        HBP.setParameter(1,inPlayerID);
        int inHBP = (int)HBP.getSingleResult();
        
        Query Sac = em.createNativeQuery("SELECT a.sacfly FROM hitter a WHERE a.playerID=?");
        Sac.setParameter(1,inPlayerID);
        int inSac = (int)Sac.getSingleResult();
        
        Query SO = em.createNativeQuery("SELECT a.strikeouts FROM hitter a WHERE a.playerID=?");
        SO.setParameter(1,inPlayerID);
        int inSO = (int)SO.getSingleResult();
        
        Query CS = em.createNativeQuery("SELECT a.caughtstealing FROM hitter a WHERE a.playerID=?");
        CS.setParameter(1,inPlayerID);
        int inCS = (int)CS.getSingleResult();
        
        Query SB = em.createNativeQuery("SELECT a.stolenbases FROM hitter a WHERE a.playerID=?");
        SB.setParameter(1,inPlayerID);
        int inSB = (int)SB.getSingleResult();
        
        Hitter returnHitter = new Hitter(inPlayerID);
        returnHitter.updateHitter(inAB, inH, inB2, inB3, inHR, inBB, inHBP, inSac, inSO, inCS, inSB);
        
        return returnHitter;    
    }
    
    public int getPlayerID()
    {
        return playerid; 
    }

    public Integer getAtbats() 
    {
        return atbats;
    }

    public void setAtbats(Integer atbats) {
        this.atbats = atbats;
    }
    
    public void updateAB(int AB)
    {
        this.atbats += AB; 
    }
    
    public Integer getHits()
    {
        return hits; 
    }
    
    public void updateHits(int inHits)
    {
        this.hits += inHits; 
    }


    public void setHits(Integer hits) 
    {
        this.hits = hits;
    }
    
    public Integer getBB()
    {
        return baseonballs; 
    }
    
    public void updateBB(int walks)
    {
        this.baseonballs += walks; 
    }
    
    public void setBB(Integer baseonballs) 
    {
        this.baseonballs = baseonballs;
    }
    
    public Integer getHBP()
    {
        return hitbypitch; 
    }
    
    public void updateHBP(int HBP)
    {
        this.hitbypitch += HBP; 
    }

    public void setHBP(Integer hitbypitch) 
    {
        this.hitbypitch = hitbypitch;
    }
    
    public Integer getDoubles()
    {
        return doublehit; 
    }
    
    public void updateDoubles(int doublesHit)
    {
        this.doublehit += doublesHit; 
    }

    public void setDoublehit(Integer doublehit) 
    {
        this.doublehit = doublehit;
    }
    
    public Integer getTriples()
    {
        return triplehit; 
    }
    
    public void updateTriples(int triples)
    {
        this.triplehit += triples; 
    }

    public void setTriplehit(Integer triplehit) 
    {
        this.triplehit = triplehit;
    }
    
    public Integer getHomeruns()
    {
        return homeruns; 
    }
    
    public void updateHomeruns(int homers)
    {
        this.homeruns += homers; 
    }

    public void setHomeruns(Integer homeruns) 
    {
        this.homeruns = homeruns;
    }
    
    public Integer getStrikeouts()
    {
        return strikeouts; 
    }
    
    public void updateStrikeouts(int K)
    {
        this.strikeouts += K; 
    }

    public void setStrikeouts(Integer strikeouts) 
    {
        this.strikeouts = strikeouts;
    }
    
    public Integer getStolenBases()
    {
        return stolenbases; 
    }
    
    public void updateStolenBases(int steals)
    {
        this.stolenbases += steals;
    }

    public void setStolenbases(Integer stolenbases) 
    {
        this.stolenbases = stolenbases;
    }
    
    public Integer getCaughtStealing()
    {
        return caughtstealing;
    }
    
    public void updateCaughtStealing(int caught)
    {
        this.caughtstealing += caught;
    }

    public void setCaughtstealing(Integer caughtstealing) 
    {
        this.caughtstealing = caughtstealing;
    }
    
    public double getStealingPerc()
    {
        return stealingperc; 
    }
    
    public void updateStealingPerc()
    {
        if((stolenbases + caughtstealing) != 0)
            this.stealingperc = (double)stolenbases/(double)(stolenbases + caughtstealing);
        else
            stealingperc = 0.0; 
    }
    
    public int getSacFly()
    {
        return sacfly;
    }
    
    public void updateSac(int sac)
    {
        this.sacfly += sac; 
    }
    
    public int getTotalBases()
    {
        return totalbases; 
    }
    
    public void updateTotalBases()
    {
        this.totalbases = hits + (doublehit) + (triplehit * 2) + (homeruns * 3);
    }
    
    public double getBattingAvg()
    {
        return battingavg;
    }
    
    public void updateAvg()
    {
        if(atbats != 0)
            this.battingavg = (double)hits/(double)atbats;
        else
            battingavg = 0.0;
    }
    
    public double getSlugging()
    {
        return slugging; 
    }
    
    public void updateSlugging()
    {
        if(atbats != 0)
            this.slugging = (double)totalbases/(double)atbats;
        else
            slugging = 0.0;
    }
    
    public double getOBP()
    {
        return onbaseperc; 
    }
    
    public void updateOBP()
    {
        if((atbats + baseonballs + hitbypitch + sacfly) != 0)
            this.onbaseperc = (double)(hits + baseonballs + hitbypitch)/(double)(atbats + baseonballs + hitbypitch + sacfly);
        else
            onbaseperc = 0.0;
    }
    
    
    public void printStats()
    {
        System.out.println("AB: "+atbats+" Hits: "+hits+" Walks: "+baseonballs+" Strikeouts: "+strikeouts+" Avg: "+(String.format("%.3f", battingavg))+
                " Total Bases: "+totalbases+" Doubles: "+doublehit+" Triples: "+triplehit+" Homeruns: "+homeruns+" Slugging: "+(String.format("%.3f", slugging))+
                " OBP: "+(String.format("%.3f", onbaseperc))+" Stolen Base Perc: "+(String.format("%.3f", stealingperc)));  
    }
    
    public String returnStats()
    {
        String returnString = "AB: "+atbats+"  |  H: "+hits+"  |  BB: "+baseonballs+"  |  SO: "+strikeouts+"  |  AVG: "+(String.format("%.3f", battingavg))+
                "  |  TB: "+totalbases+"  |  2B: "+doublehit+"  |  3B: "+triplehit+"  |  HR: "+homeruns+"  |  SLG: "+(String.format("%.3f", slugging))+
                "  |  OBP: "+(String.format("%.3f", onbaseperc))+"  |  SB Perc: "+(String.format("%.3f", stealingperc));  
        return returnString; 
    }
}


