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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Match {

	public Match(int id){
		this.id = id;
	}

	private int id;
	
	private LocalDateTime dateStart;
	
	private LocalDateTime dateEnd;
	
	private List<Kill> kills;

	private List<Ranking> rankings;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDateStart() {
		return dateStart;
	}

	public void setDateStart(LocalDateTime dateStart) {
		this.dateStart = dateStart;
	}

	public LocalDateTime getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(LocalDateTime dateEnd) {
		this.dateEnd = dateEnd;
	}

	public List<Kill> getKills() {
		
		if(null == kills){
			kills = new ArrayList<Kill>();
		}
		return kills;
	}

	public List<Ranking> getRankings() {
		if(null == rankings){
			rankings = new ArrayList<Ranking>();
		}
		return rankings;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (id != match.id) return false;
        if (!dateStart.equals(match.dateStart)) return false;
        if (!dateEnd.equals(match.dateEnd)) return false;
        if (kills != null ? !kills.equals(match.kills) : match.kills != null) return false;
        return !(rankings != null ? !rankings.equals(match.rankings) : match.rankings != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dateStart.hashCode();
        result = 31 * result + dateEnd.hashCode();
        result = 31 * result + (kills != null ? kills.hashCode() : 0);
        result = 31 * result + (rankings != null ? rankings.hashCode() : 0);
        return result;
    }
}
