abstract /**
 * ShipBoard
 */
class ShipBoard {
    private int[][]ShipBoard;
    abstract void  enterAllShips();
    abstract boolean  allShipsSank();
    abstract boolean  getStrike(int x,int y);
    abstract int[][] returnBoard();
}