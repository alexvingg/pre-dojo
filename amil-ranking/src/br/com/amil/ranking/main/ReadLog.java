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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.amil.ranking.models.Weapon;
import br.com.amil.ranking.models.Kill;
import br.com.amil.ranking.models.Match;
import br.com.amil.ranking.models.Player;
import br.com.amil.ranking.constants.Constants;
import br.com.amil.ranking.utils.DateUtil;

public class ReadLog {

	private final String file;

	public ReadLog(String file) {
		this.file = file;
	}

	public List<Match> createMatches() {
		InputStream is;
		BufferedReader br;
		InputStreamReader isr;
		Match m = null;
		List<Match> matches = new ArrayList<>();
		List<Player> players = new ArrayList<>();
		List<Weapon> weapons = new ArrayList<>();
		try {
			is = new FileInputStream(file);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String s = br.readLine();
			while (s != null) {
				String[] line = s.split(" ");
				if (s.contains(Constants.NEW_MATCH)) {
					m = new Match(Integer.parseInt(line[5]));
					m.setDateStart(DateUtil.parseLocalDate(line[0] + " " + line[1]));
					matches.add(m);
				}
				if (s.contains(Constants.HAS_ENDED)) {
					m.setDateEnd(DateUtil.parseLocalDate(line[0] + " " + line[1]));
					players = new ArrayList();
				}
				if (s.contains(Constants.KILLED)) {
					Player playerDead = new Player(line[5]);
					players.add(playerDead);
					Kill kill = new Kill();
					kill.setDate(DateUtil.parseLocalDate(line[0] + " " + line[1]));
					Weapon weapon = new Weapon(line[7]);
					if (!weapons.contains(weapon)) {
						weapons.add(weapon);
					}
					kill.setPlayerDead(playerDead);
					if (!Constants.WORLD.equals(line[3])) {
						Player playerKiller = new Player(line[3]);
						players.add(playerKiller);
						kill.setPlayerKiller(playerKiller);
						kill.setWeapon(weapon);
					}
					m.getKills().add(kill);
				}
				s = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return matches;
	}
}
