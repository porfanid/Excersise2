abstract /**
 * Player
 */
class Player{

    int[][] ShipBoard;

    public Player(){
        System.out.println("hello world");
        createBoard();
    }

// creating 2 abstract classes: createBoard and nextStrike.
    abstract public void createBoard();

    abstract int[][] update(int[] coordinates, boolean isTrue);
    
    abstract public int[] nextStrike();
    
    abstract int[][] returnStrikeBoard();

    abstract int[][] returnBoard();

    abstract ShipBoard returnShipBoard();
}