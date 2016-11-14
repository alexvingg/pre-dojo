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

package br.com.amil.ranking.models;

import java.util.HashMap;
import java.util.Map;

public class Ranking implements Comparable<Ranking>{
	
	public Ranking(){
		this.dead = 0;
		this.kill = 0;
		this.arms = new HashMap<>();
	}
	
	private Match match;
	
	private Player player;
	
	private Integer kill;
	
	private Integer dead;

	private Map<Weapon, Integer> arms;
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getKill() {
		return kill;
	}

	public void setKill(Integer kill) {
		this.kill = kill;
	}

	public Integer getDead() {
		return dead;
	}

	public void setDead(Integer dead) {
		this.dead = dead;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public void incrementArms(Weapon weapon){
		
		if(arms.containsKey(weapon)){
			arms.put(weapon, arms.get(weapon)+ 1);
		}else{
			arms.put(weapon, 1);
		}
	}
	
	public void incrementDead(){
		this.dead ++;
	}
	
	public void incrementKill(){
		this.kill ++;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dead;
		result = prime * result + kill;
		result = prime * result + ((match == null) ? 0 : match.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ranking other = (Ranking) obj;
		if (dead != other.dead)
			return false;
		if (kill != other.kill)
			return false;
		if (match == null) {
			if (other.match != null)
				return false;
		} else if (!match.equals(other.match))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

	@Override
	public int compareTo(Ranking o) {
		int comp = o.kill.compareTo(this.kill);
		if (comp != 0) {
            return comp;
         } else {
            return this.dead.compareTo(o.dead);
         }
	}

	public Map<Weapon, Integer> getArms() {
		return arms;
	}

	public void setArms(Map<Weapon, Integer> arms) {
		this.arms = arms;
	}
	
}
