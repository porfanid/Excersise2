abstract /**
 * Player
 */
class Player{



    public Player(){
        createBoard();
    }

// creating 2 abstract classes: createBoard and nextStrike.
    abstract public void createBoard();
    abstract int[][] update(int[] coordinates, boolean isTrue);
    abstract public int[] nextStrike();
    abstract int[][] returnStrikeBoard();
}