import javafx.scene.*;
import java.util.ArrayList;
import java.lang.Math.*;

public class Level extends Screen {
	Asteroid[] aList;
	Spaceship[] sList;
	Zigzag[] zList;
	Bomb[] bList;
	int totalEnemies = 0;
	int remaining;
	Level () {

	}
	//set Level using given input
	public void set (Player p, Asteroid[] a, Spaceship[] s, Zigzag[] z, Bomb[] b, String amount, String order) {
		this.add(p.getIV());
		setEnemyLists(amount);
		createEnemies(a, s, z, b);
		setPositions(order);
		addEnemies();
	}
	//determines the condition of the Level - 0 = continue, 1 = won, 2 = lost
	public int condition () {
		int condition = 0;
		int oneCount = 0;
		int twoCount = 0;
		for (int i = 0; i < aList.length; i++) {
			if (aList[i].getStatus() == 1) {
				oneCount++;
			}
			if (aList[i].getStatus() == 2) {
				twoCount++;
			}
		}
		for (int i = 0; i < sList.length; i++) {
			if (sList[i].getStatus() == 1) {
				oneCount++;
			}
			if (sList[i].getStatus() == 2) {
				twoCount++;
			}
		}
		for (int i = 0; i < zList.length; i++) {
			if (zList[i].getStatus() == 1) {
				oneCount++;
			}
			if (zList[i].getStatus() == 2) {
				twoCount++;
			}
		}
		for (int i = 0; i < bList.length; i++) {
			if (bList[i].getStatus() == 1) {
				oneCount++;
			}
			if (bList[i].getStatus() == 2) {
				twoCount++;
			}
		}
		if (twoCount > 0) {
			condition = 2;
		} else {
			if (oneCount == totalEnemies) {
				condition = 1;
			}
		}
		return condition;
	}
	private void setEnemyLists (String amount) {
		int number = Integer.parseInt(amount.substring(0, 1));
		aList = new Asteroid[number];
		this.totalEnemies += number;
		number = Integer.parseInt(amount.substring(1, 2));
		sList = new Spaceship[number];
		this.totalEnemies += number;
		number = Integer.parseInt(amount.substring(2, 3));
		zList = new Zigzag[number];
		this.totalEnemies += number;
		number = Integer.parseInt(amount.substring(3, 4));
		bList = new Bomb[number];
		this.totalEnemies += number;
	}
	private void createEnemies (Asteroid[] a, Spaceship[] s, Zigzag[] z, Bomb[] b) {
		for (int i = 0; i < aList.length; i++) {
			aList[i] = a[i];
		}
		for (int i = 0; i < sList.length; i++) {
			sList[i] = s[i];
		}
		for (int i = 0; i < zList.length; i++) {
			zList[i] = z[i];
		}
		for (int i = 0; i < bList.length; i++) {
			bList[i] = b[i];
		}
	}
	//sets the spacing between each enemy
	private void setPositions (String order) {
		double num = (double) order.length();
		double spacing = 500 / num;
		int aIndex = 0;
		int sIndex = 0;
		int zIndex = 0;
		int bIndex = 0;
		for (int i = 0; i < order.length(); i++) {
			char temp = order.charAt(i);
			switch (temp) {
				case 'a':
					aList[aIndex].setX(spacing * i + 50);
					aIndex++;
					break;
				case 's':
					sList[sIndex].setX(spacing * i + 50);
					sIndex++;
					break;
				case 'z':
					zList[zIndex].setX(spacing * i + 50);
					zIndex++;
					break;
				case 'b':
					bList[bIndex].setX(spacing * i + 50);
					bIndex++;
					break;
				default:
					break;
			}
		}
	}
	//adds enemy sprites to the Level
	private void addEnemies () {
		for (int i = 0; i < aList.length; i++) {
			this.add(aList[i].getIV());
		}
		for (int i = 0; i < sList.length; i++) {
			this.add(sList[i].getIV());
		}
		for (int i = 0; i < zList.length; i++) {
			this.add(zList[i].getIV());
		}
		for (int i = 0; i < bList.length; i++) {
			this.add(bList[i].getIV());
		}
	}
}
