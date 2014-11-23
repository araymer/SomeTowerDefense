package Model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Vector;

import View.TilePanel;

public class MasterList extends LinkedList<Drawable> implements Serializable, Cloneable {
	private static MasterList masterList;
	public static Vector<Drawable> unitList;
	public static boolean side;
	
	public static MasterList getInstance() {
		if (masterList == null) {
			masterList = new MasterList();
		}
		return masterList;
	}
	
	public boolean hostile() {
		return false;

	}
	
	

}
