package Model;

import java.io.Serializable;
import java.util.Vector;

public class MasterList extends Vector implements Serializable, Cloneable {
	public static Vector<Object> unitList;
	public static boolean side;

	public boolean hostile() {
		return false;

	}

}
