
/**
 * @author flavia
 * Tester class
 */
public class tester {
	
	public static void main (String[] args){
		Map m = new Map(20, 20);
		Room r = new Room(7, 7, 4);
		m.genRoom(r);
		m.addNewRoom();
		System.out.println(m.toString());
	}
}
