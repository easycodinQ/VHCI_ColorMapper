/**
 * Visualization and HCI
 * Exercise 3: Color Mapping
 * Volker Ahlers, HS Hannover (volker.ahlers@hs-hannover.de)
 */

package vish;

import java.awt.Color;

public abstract class ColorTable {
	
	protected int size;
	protected double fMin;
	protected double fMax;
	protected Color table[];
		
	public ColorTable(int size, double fMin, double fMax) {
		this.size = size;
		this.fMin = fMin;
		this.fMax = fMax;
		this.table = new Color[size];		
	}
	
	public Color getColor(double f) {
		// To do: return appropriate color table entry
		return table[0];
	}
	
	public String toString() {
		String string = new String("Color table, size " + size + "\n");
		for (int i = 0; i < size; i++) {
			string += table[i] + "\n";
		}
		return string;
	}
	
}
