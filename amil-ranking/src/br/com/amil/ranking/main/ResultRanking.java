/*
 * MIT License
 *
 * Copyright (c) 2016 Alex Costa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package br.com.amil.ranking.main;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.amil.ranking.models.*;

public class ResultRanking {

	private final List<Match> matches;
	private Map<Player, Ranking> mapRanking;
	
	public ResultRanking(List<Match> matches){
		this.matches = matches;
	}
		
	public List<Match> createResultRanking(){
		for (Match match : matches) {
			this.mapRanking = new HashMap();
			
			for (Kill kill : match.getKills()) {
				
				if(mapRanking.containsKey(kill.getPlayerKiller())){
					
					if(null != kill.getPlayerKiller()){
						Ranking rankingPlayerOne = mapRanking.get(kill.getPlayerKiller());
						rankingPlayerOne.setPlayer(kill.getPlayerKiller());
						rankingPlayerOne.incrementKill();
						rankingPlayerOne.incrementArms(kill.getWeapon());
						mapRanking.put(kill.getPlayerKiller(), rankingPlayerOne);
					}
					
				}else{

					if(null != kill.getPlayerKiller()){
						Ranking rankingPlayerOne = new Ranking();
						rankingPlayerOne.setPlayer(kill.getPlayerKiller());
						rankingPlayerOne.incrementKill();
						rankingPlayerOne.incrementArms(kill.getWeapon());
						mapRanking.put(kill.getPlayerKiller(), rankingPlayerOne);
					}
				}
				
				if(mapRanking.containsKey(kill.getPlayerDead())){
					Ranking rankingPlayerTwo = mapRanking.get(kill.getPlayerDead());
					rankingPlayerTwo.setPlayer(kill.getPlayerDead());
					rankingPlayerTwo.incrementDead();
					mapRanking.put(kill.getPlayerDead(), rankingPlayerTwo);
				}else{
					Ranking rankingPlayerTwo = new Ranking();
					rankingPlayerTwo.setPlayer(kill.getPlayerDead());
					rankingPlayerTwo.incrementDead();
					mapRanking.put(kill.getPlayerDead(), rankingPlayerTwo);
				}
			}
			
			for (Player player : mapRanking.keySet()) {
				Ranking r = mapRanking.get(player);
				match.getRankings().add(r);
			}
		}
		
		return this.matches;
	}

    public Weapon getPrefferedWeapon(Ranking ranking){
        Weapon weaponWin = null;
        int killsArm = 0;
        for(Weapon weapon : ranking.getArms().keySet()) {
            if(ranking.getArms().get(weapon) > killsArm){
                weaponWin = weapon;
            }
            killsArm = ranking.getArms().get(weapon);
        }
        return weaponWin;
    }

	public void showLogRanking(){
		for (Match match : matches) {
			List<Ranking> rankings = match.getRankings();
			Collections.sort(rankings);
			System.out.println("Match: " + match.getId());
			for (Ranking ranking : rankings) {
                String award = "";
                if(ranking.getDead() == 0){
                    award = "**";
                }
				System.out.println(ranking.getPlayer().getName() + " - Kills: " +
                    ranking.getKill() + " / Deads: " + ranking.getDead() +
                    " / Preffered Weapon: " + getPrefferedWeapon(ranking).getName() +
                    " " + award);
			}
			System.out.println("");
		}
	}
}
