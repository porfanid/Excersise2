abstract /**
 * Player
 */
class Player{



    public Player(){
        createBoard();
    }

// creating 2 abstract classes: createBoard and nextStrike.
    abstract public void createBoard();

    abstract public int[] nextStrike();
}