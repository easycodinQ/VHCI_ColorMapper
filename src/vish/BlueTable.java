package vish;

import java.awt.*;

public class BlueTable extends ColorTable{
	public BlueTable(int size, double fMin, double fMax) {
		super(size, fMin, fMax);

		for(int i=0; i<size;i++){
			float color = (float) i/size;
			table[i] = new Color(Color.HSBtoRGB((float)2/3,color,1));
		}
	}
}
