abstract /**
 * Player
 */
class Player{



    public Player(){
        createBoard();
    }


    abstract public void createBoard();

    abstract public int[] nextStrike();
}