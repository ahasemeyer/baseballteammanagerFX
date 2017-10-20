/**
 * @author Austin Hasemeyer
 * @document PitcherFX.java
 * @description This Model class translates information from Java to JavaFX
 *      SimpleProperties. This is so information can be retrieved from SQL 
 *      then passed to Java, translated into JavaFX to be displayed in the GUI. 
 */
package Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public final class PitcherFX
{
    private SimpleIntegerProperty playerID;
    private SimpleDoubleProperty WHIP;
    private SimpleIntegerProperty baseOnBalls;
    private SimpleDoubleProperty earnedRunAverage;
    private SimpleIntegerProperty earnedRuns;
    private SimpleIntegerProperty games;
    private SimpleIntegerProperty gamesStarted;
    private SimpleIntegerProperty hitBatters;
    private SimpleIntegerProperty hits;
    private SimpleIntegerProperty homeRuns;
    private SimpleDoubleProperty inningsPitched;
    private SimpleIntegerProperty losses;
    private SimpleDoubleProperty oppAvg;
    private SimpleIntegerProperty runs;
    private SimpleIntegerProperty saveOpp;
    private SimpleIntegerProperty saves;
    private SimpleIntegerProperty strikeOuts;
    private SimpleIntegerProperty wins;
    
    public PitcherFX()
    {}

    public PitcherFX(int ID)   
    {
        this.playerID = new SimpleIntegerProperty(ID);
    }
    
    public PitcherFX(int playerID, double WHIP, int BB, double ERA, int ER, int G, int GS, int HBP,
            int H, int HR, double IP, int L, double OPA, int R, int SVO, int SV, int SO, int W)
    {
        this.setPlayerID(playerID);
        this.setWHIP(WHIP);
        this.setBaseOnBalls(BB);
        this.setERA(ERA);
        this.setEarnedruns(ER);
        this.setGames(G);
        this.setGamesStarted(GS);
        this.setHitBatters(HBP);
        this.setHits(H);
        this.setHomeruns(HR);
        this.setInningsPitched(IP);
        this.setLosses(L);
        this.setOppAvg(OPA);
        this.setRuns(R);
        this.setSaveOpp(SVO);
        this.setSaves(SV);
        this.setStrikeOuts(SO);
        this.setWins(W);          
    }
    
    public int getWins()
    {
        return wins.get();
    }
    
    public SimpleIntegerProperty getWinsProperty()
    {
        return this.wins;
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
        return this.losses;
    }
    
    public void setLosses(int losses)
    {
        this.losses = new SimpleIntegerProperty(losses);
    }
    
    public double getERA()
    {
        return earnedRunAverage.get();
    }
    
    public SimpleDoubleProperty getERAProperty()
    {
        return this.earnedRunAverage; 
    }
    
    public void setERA(double era)
    {
        this.earnedRunAverage = new SimpleDoubleProperty(era);
    }
    
    public int getGames()
    {
        return games.get();
    }
    
    public SimpleIntegerProperty getGamesProperty()
    {
        return games; 
    }
    
    public void setGames(int games)
    {
        this.games = new SimpleIntegerProperty(games);
    }
    
    public int getGamesStarted()
    {
        return gamesStarted.get();
    }
    
    public SimpleIntegerProperty getGamesStartedProperty()
    {
        return gamesStarted; 
    }
    
    public void setGamesStarted(int GS)
    {
        this.gamesStarted = new SimpleIntegerProperty(GS);
    }
    
    public int getSaves()
    {
        return saves.get();
    }
    
    public SimpleIntegerProperty getSavesProperty()
    {
        return this.saves; 
    }
    
    public void setSaves(int saves)
    {
        this.saves = new SimpleIntegerProperty(saves);
    }
    
    public int getSaveOpp()
    {
        return saveOpp.get();
    }
    
    public SimpleIntegerProperty getSavesOppProperty()
    {
        return saveOpp; 
    }
    
    public void setSaveOpp(int SVO)
    {
        this.saveOpp = new SimpleIntegerProperty(SVO); 
    }
    
    public double getInningsPitched()
    {
        return inningsPitched.get();
    }
    
    public SimpleDoubleProperty getInningsPitchedProperty()
    {
        return inningsPitched; 
    }
    
    public void setInningsPitched(double IP)
    {
        this.inningsPitched = new SimpleDoubleProperty(IP);
    }
    
    public int getHits()
    {
        return hits.get();
    }
    
    public SimpleIntegerProperty getHitsProperty()
    {
        return this.hits; 
    }
    
    public void setHits(int hits)
    {
        this.hits = new SimpleIntegerProperty(hits);
    }
    
    public int getRuns()
    {
        return runs.get();
    }
    
    public SimpleIntegerProperty getRunsProperty()
    {
        return runs; 
    }
    
    public void setRuns(int runs)
    {
        this.runs = new SimpleIntegerProperty(runs);
    }
    
    public int getEarnedruns()
    {
        return earnedRuns.get();
    }
    
    public SimpleIntegerProperty getEarnedrunsProperty()
    {
        return earnedRuns;
    }
    
    public void setEarnedruns(int runs)
    {
        this.earnedRuns = new SimpleIntegerProperty(runs);
    }
    
    public int getHomeruns()
    {
        return homeRuns.get();
    }
    
    public SimpleIntegerProperty getHomerunsProperty()
    {
        return homeRuns; 
    }
    
    public void setHomeruns(int hr)
    {
        this.homeRuns = new SimpleIntegerProperty(hr);
    }
    
    public int getBaseonBalls()
    {
        return baseOnBalls.get();
    }
           
    public SimpleIntegerProperty getBaseonBallsProperty()
    {
        return baseOnBalls; 
    }
    
    public void setBaseOnBalls(int bb)
    {
        this.baseOnBalls = new SimpleIntegerProperty(bb); 
    }
    
    public int getHitBatters()
    {
        return hitBatters.get(); 
    }
    
    public SimpleIntegerProperty getHitBattersProperty()
    {
        return hitBatters; 
    }
    
    public void setHitBatters(int hb)
    {
        this.hitBatters = new SimpleIntegerProperty(hb);
    }
    
    public int getStrikeOuts()
    {
        return strikeOuts.get();
    }
    
    public SimpleIntegerProperty getStrikeOutsProperty()
    {
        return strikeOuts; 
    }
    
    public void setStrikeOuts(int so)
    {
        this.strikeOuts = new SimpleIntegerProperty(so); 
    }
    
    public double getOppAvg()
    {
        return oppAvg.get(); 
    }
    
    public SimpleDoubleProperty getOppAvgProperty()
    {
        return oppAvg; 
    }
    
    public void setOppAvg(double inAvg)
    {
        this.oppAvg = new SimpleDoubleProperty(inAvg);
    }
    
    public double getWHIP()
    {
        return WHIP.get();
    }
    
    public SimpleDoubleProperty getWHIPProperty()
    {
        return WHIP;
    }
    
    public void setWHIP(double inWHIP)
    {
        this.WHIP = new SimpleDoubleProperty(inWHIP);
    }
    
    public int getPlayerID()
    {
        return playerID.get(); 
    }
    
    public SimpleIntegerProperty getPlayerIDProperty()
    {
        return playerID;
    }
    
    public void setPlayerID(int id)
    {
        this.playerID = new SimpleIntegerProperty(id);
    }
}
