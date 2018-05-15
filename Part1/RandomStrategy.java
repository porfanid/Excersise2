import java.util.Random;
import java.util.ArrayList;

class RandomStrategy{
	private int boardSize = 10;
	private Random rnd = new Random();
	private ArrayList<int[]> positions = new ArrayList<int[]>();
	
	public RandomStrategy(){
		for (int i = 0; i < boardSize; i ++){
			for (int j = 0; j < boardSize; j ++){
				int[] position = new int[2];
				position[0] = i;
				position[1] = j;
				positions.add(position);
			}
		}
	}
	
	public int[] nextStrike(){
		int index = rnd.nextInt(positions.size());
		int[] pos = positions.get(index);
		positions.remove(index);
		return pos;
	}
	
}