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

package br.com.amil.ranking.tests;

import br.com.amil.ranking.main.ResultRanking;
import br.com.amil.ranking.models.Weapon;
import br.com.amil.ranking.models.Kill;
import br.com.amil.ranking.models.Match;
import br.com.amil.ranking.models.Player;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResultRankingTest {

	@Test
	public void testPlayerWinner(){

        Player p1 = new Player("p1");
        Player p2 = new Player("p2");
        Player p3 = new Player("p3");
        Player p4 = new Player("p4");
        Player p5 = new Player("p5");

        Match m = new Match(1212121);
        m.setDateStart(LocalDateTime.now());
        m.setDateEnd(LocalDateTime.now().minusMinutes(20));

        Kill kill1 = new Kill();
        kill1.setPlayerKiller(p1);
        kill1.setPlayerDead(p2);
        kill1.setWeapon(new Weapon("M16"));
        kill1.setDate(LocalDateTime.now().plusMinutes(1));

        Kill kill2 = new Kill();
        kill2.setPlayerKiller(p1);
        kill2.setPlayerDead(p2);
        kill2.setWeapon(new Weapon("M16"));
        kill2.setDate(LocalDateTime.now().plusMinutes(3));

        Kill kill3 = new Kill();
        kill3.setPlayerKiller(null);
        kill3.setPlayerDead(p2);
        kill2.setDate(LocalDateTime.now().plusMinutes(5));

        Kill kill4 = new Kill();
        kill4.setPlayerKiller(p2);
        kill4.setPlayerDead(p3);
        kill4.setWeapon(new Weapon("KNIFE"));
        kill4.setDate(LocalDateTime.now().plusMinutes(6));

        Kill kill5 = new Kill();
        kill5.setPlayerKiller(p4);
        kill5.setPlayerDead(p5);
        kill5.setWeapon(new Weapon("KNIFE"));
        kill5.setDate(LocalDateTime.now().plusMinutes(7));

        Kill kill6 = new Kill();
        kill6.setPlayerKiller(p4);
        kill6.setPlayerDead(p3);
        kill6.setWeapon(new Weapon("M16"));
        kill6.setDate(LocalDateTime.now().plusMinutes(8));

        Kill kill7 = new Kill();
        kill7.setPlayerKiller(p1);
        kill7.setPlayerDead(p2);
        kill7.setWeapon(new Weapon("M16"));
        kill7.setDate(LocalDateTime.now().plusMinutes(4));

        m.getKills().add(kill1);
        m.getKills().add(kill2);
        m.getKills().add(kill3);
        m.getKills().add(kill4);
        m.getKills().add(kill5);
        m.getKills().add(kill6);
        m.getKills().add(kill7);

        List<Match> matchList = new ArrayList();
        matchList.add(m);

        ResultRanking result = new ResultRanking(matchList);

        List<Match> matchListWin = result.createResultRanking();

        Player win = null;
        Weapon weaponWin = null;
        int deads = 0;

        for (Match match: matchListWin) {
            Collections.sort(match.getRankings());
            win = match.getRankings().get(0).getPlayer();
            deads = match.getRankings().get(0).getDead();
            weaponWin = result.getPrefferedWeapon(match.getRankings().get(0));
        }

		assertEquals("Player win", p1.getName(), win.getName());
        assertEquals("Player Weapon win", new Weapon("M16").getName(), weaponWin.getName());
        assertEquals("Player Award", 0, deads);
	}
}
