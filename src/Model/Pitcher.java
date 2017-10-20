/**
 * @author Austin Hasemeyer
 * @document Pitcher.java
 * @description This Model class represents Pitcher. Pitchers are anyone in baseball
 *      who pitches to batters and contains all statistics related to pitching.
 *      Pitchers are related to Players and share playerID with Players.
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

@Entity
@Table(name = "pitcher")
public class Pitcher implements Serializable
{
    @Id
    @Basic(optional = false)
    @Column(name = "PLAYERID", nullable = false)
    private Integer playerID;
    @Column(name = "WHIP")
    private Double WHIP;
    @Column(name = "BASEONBALLS")
    private Integer baseOnBalls;
    @Column(name = "EARNEDRUNAVERAGE")
    private Double earnedRunAverage;
    @Column(name = "EARNEDRUNS")
    private Integer earnedRuns;
    @Column(name = "GAMES")
    private Integer games;
    @Column(name = "GAMESSTARTED")
    private Integer gamesStarted;
    @Column(name = "HITBATTERS")
    private Integer hitBatters;
    @Column(name = "HITS")
    private Integer hits;
    @Column(name = "HOMERUNS")
    private Integer homeRuns;
    @Column(name = "INNINGSPITCHED")
    private double inningsPitched;
    @Column(name = "LOSSES")
    private Integer losses;
    @Column(name = "OPPAVG")
    private Double oppAvg;
    @Column(name = "RUNS")
    private Integer runs;
    @Column(name = "SAVEOPP")
    private Integer saveOpp;
    @Column(name = "SAVES")
    private Integer saves;
    @Column(name = "STRIKEOUTS")
    private Integer strikeOuts;
    @Column(name = "WINS")
    private Integer wins;
    private static final long serialVersionUID = 1L;
    
    public Pitcher()
    {
        
    }
    
    public Pitcher(int ID)   
    {
        playerID = ID;
        wins = 0;
        losses = 0;
        earnedRunAverage = 0.0;
        games = 0;
        gamesStarted = 0;
        saves = 0;
        saveOpp = 0;
        inningsPitched = 0.0;
        hits = 0;
        runs = 0;
        earnedRuns = 0;
        homeRuns = 0;
        baseOnBalls = 0;
        hitBatters = 0;
        strikeOuts = 0;
        oppAvg = 0.000;
        WHIP = 0.000;   
    }
    
    
    public void updatePitcher(int inWins, int inLosses, int inGames, int inGS, int inSave, int inSaveOpp, double inningsPitched,
            int inHits, int inRuns, int inER, int inHR, int inBB, int inHBP, int inSO)
    {
        updateWins(inWins);
        updateLosses(inLosses);
        updateGames(inGames);
        updateGamesStarted(inGS);
        updateSaves(inSave);
        updateSaveOpp(inSaveOpp);
        updateInningsPitched(inningsPitched);
        updateHits(inHits);
        updateRuns(inRuns);
        updateEarnedRuns(inER);
        updateHomeruns(inHR);
        updateBaseOnBalls(inBB);
        updateHitBatters(inHBP);
        updateStrikeOuts(inSO);
        updateERA();
        updateWHIP(); 
        updateOppAvg();
        
    }
    
    public static Pitcher loadPitcherData(int inPlayerID)
    {
        EntityManager em = Model.DBUtil.getEM();
        
        Query wins = em.createNativeQuery("SELECT a.wins FROM pitcher a WHERE a.playerID=?");
        wins.setParameter(1,inPlayerID);
        int inWins = (int)wins.getSingleResult();
       
        Query losses = em.createNativeQuery("SELECT a.losses FROM pitcher a WHERE a.playerID=?");
        losses.setParameter(1,inPlayerID);
        int inLosses = (int)losses.getSingleResult();
        
        Query game = em.createNativeQuery("SELECT a.games FROM pitcher a WHERE a.playerID=?");
        game.setParameter(1,inPlayerID);
        int inGames = (int)game.getSingleResult();

        Query gamesStarted = em.createNativeQuery("SELECT a.gamesstarted FROM pitcher a WHERE a.playerID=?");
        gamesStarted.setParameter(1,inPlayerID);
        int inGamesStarted = (int)gamesStarted.getSingleResult();

        Query saves = em.createNativeQuery("SELECT a.saves FROM pitcher a WHERE a.playerID=?");
        saves.setParameter(1,inPlayerID);
        int inSaves = (int)saves.getSingleResult();      
        
        Query saveOpp = em.createNativeQuery("SELECT a.saveopp FROM pitcher a WHERE a.playerID=?");
        saveOpp.setParameter(1,inPlayerID);
        int inSaveOpp = (int)saveOpp.getSingleResult();
        
        Query IP = em.createNativeQuery("SELECT a.inningspitched FROM pitcher a WHERE a.playerID=?");
        IP.setParameter(1,inPlayerID);
        double inIP = (double)IP.getSingleResult();
        
        Query hits = em.createNativeQuery("SELECT a.hits FROM pitcher a WHERE a.playerID=?");
        hits.setParameter(1,inPlayerID);
        int inHits = (int)hits.getSingleResult();
        
        Query runs = em.createNativeQuery("SELECT a.runs FROM pitcher a WHERE a.playerID=?");
        runs.setParameter(1,inPlayerID);
        int inRuns = (int)runs.getSingleResult();
        
        Query earnedRuns = em.createNativeQuery("SELECT a.earnedruns FROM pitcher a WHERE a.playerID=?");
        earnedRuns.setParameter(1,inPlayerID);
        int inER = (int)earnedRuns.getSingleResult();
        
        Query homeruns = em.createNativeQuery("SELECT a.homeruns FROM pitcher a WHERE a.playerID=?");
        homeruns.setParameter(1,inPlayerID);
        int inHR = (int)homeruns.getSingleResult();
        
        Query baseOnBalls = em.createNativeQuery("SELECT a.baseonballs FROM pitcher a WHERE a.playerID=?");
        baseOnBalls.setParameter(1,inPlayerID);
        int inBB = (int)baseOnBalls.getSingleResult();
        
        Query hitByPitch = em.createNativeQuery("SELECT a.hitbatters FROM pitcher a WHERE a.playerID=?");
        hitByPitch.setParameter(1,inPlayerID);
        int inHBP = (int)hitByPitch.getSingleResult();
        
        Query strikeOuts = em.createNativeQuery("SELECT a.strikeouts FROM pitcher a WHERE a.playerID=?");
        strikeOuts.setParameter(1,inPlayerID);
        int inSO = (int)strikeOuts.getSingleResult();
        
        Pitcher returnPitcher = new Pitcher(inPlayerID);
        returnPitcher.updatePitcher(inWins, inLosses, inGames, inGamesStarted, inSaves, inSaveOpp, inIP, inHits, inRuns, inER, inHR, inBB, inHBP, inSO);
        
        return returnPitcher;
    }

    
    public void printStats()
    {
        System.out.println("Wins: "+wins+" Losses: "+losses+" ERA: "+(String.format("%.3f", earnedRunAverage))+" Games: "+games+" Games Started: "+gamesStarted+
                " Saves: "+saves+" Save Opp: "+saveOpp+" Innings Pitched: "+(String.format("%.1f", inningsPitched))+" Hits: "+hits+" Runs: "+runs+
                " Earned Runs: "+earnedRuns+" Homeruns: "+homeRuns+" Base on Balls: "+baseOnBalls+" Hit Batters: "+hitBatters+" Strikeouts: "+strikeOuts+
                " Opp Average: "+(String.format("%.3f", oppAvg))+" WHIP: "+(String.format("%.3f", WHIP)));   
    }
    
    public String returnStats()
    {
        String returnString = "W: "+wins+"  |  L: "+losses+"  |  ERA: "+(String.format("%.3f", earnedRunAverage))+"  |  G: "+games+"  |  GS: "+gamesStarted+
                "  |  SV: "+saves+"  |  SVO: "+saveOpp+"  |  IP: "+(String.format("%.1f", inningsPitched))+"  |  H: "+hits+"  |  R: "+runs+
                "  |  ER: "+earnedRuns+"  |  HR: "+homeRuns+"  |  BB: "+baseOnBalls+"  |  HBP: "+hitBatters+"  |  SO: "+strikeOuts+
                "  |  Opp AVG: "+(String.format("%.3f", oppAvg))+"  |  WHIP: "+(String.format("%.3f", WHIP));
        return returnString; 
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
    
    public void updateLosses(int inLoss)
    {
        losses += inLoss;
    }
    
    public double getERA()
    {
        return earnedRunAverage;
    }
    
    public void updateERA()
    {
        double totalInnings = trueInnings(); 
        
        if(totalInnings > 0)   
            earnedRunAverage = (earnedRuns * 9)/(totalInnings);    
        else
            earnedRunAverage = 0.000; 
    }
    
    public int getGames()
    {
        return games;
    }
    
    public void updateGames(int inGames)
    {
        games += inGames;
    }
    
    public int getGamesStarted()
    {
        return gamesStarted;
    }
    
    public void updateGamesStarted(int inStarts)
    {
        gamesStarted += inStarts;
    }
    
    public int getSaves()
    {
        return saves;
    }
    
    public void updateSaves(int inSave)
    {
        saves += inSave;
    }
    
    public int getSaveOpp()
    {
        return saveOpp;
    }
    
    public void updateSaveOpp(int inSaveOpp)
    {
        saveOpp += inSaveOpp;
    }
    
    public double getInningsPitched()
    {
        return inningsPitched;
    }
    
    public void updateInningsPitched(double inInnings)
    {
        inningsPitched += inInnings;
        double remainder = inningsPitched - (int)inningsPitched;
        while(remainder >= 0.28)
        {
            remainder = remainder - 0.3;
            inningsPitched = inningsPitched + 1;
            inningsPitched = inningsPitched - 0.3;
        }
            
    }
    
    public int getHits()
    {
        return hits;
    }
    
    public void updateHits(int inHits)
    {
        hits += inHits;
    }
    
    public int getRuns()
    {
        return runs;
    }
    
    public void updateRuns(int inRuns)
    {
        runs += inRuns;
    }
    
    public int getEarnedruns()
    {
        return earnedRuns;
    }
    
    public void updateEarnedRuns(int inEarnedRuns)
    {
        earnedRuns += inEarnedRuns;
    }
    
    public int getHomeruns()
    {
        return homeRuns;
    }
    
    public void updateHomeruns(int inHR)
    {
        homeRuns += inHR;
    }
    
    public int getBaseonBalls()
    {
        return baseOnBalls;
    }
           
    public void updateBaseOnBalls(int inBB)
    {
        baseOnBalls += inBB;
    }
    
    public int getHitBatters()
    {
        return hitBatters; 
    }
    
    public void updateHitBatters(int inHitBatters)
    {
        hitBatters += inHitBatters;
    }
    
    public int getStrikeOuts()
    {
        return strikeOuts;
    }
    
    public void updateStrikeOuts(int inStrikeOuts)
    {
        strikeOuts += inStrikeOuts; 
    }
    
    public double getOppAvg()
    {
        return oppAvg; 
    }
    
    public void updateOppAvg()
    {
        if(totalAtBats() > 0)
            oppAvg = (hits)/totalAtBats(); 
        else
            oppAvg = 0.000;
    }
    
    public double getWHIP()
    {
        return WHIP;
    }
    
    public void updateWHIP()
    {
        if(trueInnings() > 0)
            WHIP = (hits+baseOnBalls)/trueInnings();
        else
            WHIP = 0.000; 
    }
    
    public int getPlayerID()
    {
        return playerID; 
    }
    
    private double trueInnings()
    {
        double trueInnings;
        double partialInning;
        int totalInnings = (int)inningsPitched; 
        
        partialInning = inningsPitched - (int)inningsPitched;
        
        if (partialInning > 0 && partialInning <= .1 )
        {
            partialInning = 1.0/3.0;
        }
        else if(partialInning > .1 && partialInning <= .2 )
        {
            partialInning = 2.0/3.0;
        }
        else
            partialInning = 0;
        
        trueInnings = totalInnings + partialInning;
        
        return trueInnings; 
    }
    
    private double totalAtBats()
    {
        double totalAB;
        
        totalAB = hits + (trueInnings() * 3);
        
        return totalAB;
    }
}
