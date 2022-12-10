public class Block {

    private int x;
    private int y;
    private int w;
    private double h;
    private int intH;
    private BlockState blockState;

    Block(int _x, int _y, int _w, double _h, BlockState _blockstate){
        x = _x;
        y = _y;
        w = _w;
        h = _h;
        intH = (int) h;
        blockState = _blockstate;
    }

    public void colorChange(int success){
        if(success == 3){
            blockState = BlockState.CORRECT;
        } else if(success == 2){
            blockState = BlockState.TOPBOT;
        } else if(success == 1){
            blockState = BlockState.DEFAULT;
        }
    }

    public void display(){

        if(blockState == BlockState.DEFAULT){
            Main.app.fill(150);
        } else if(blockState == BlockState.TOPBOT){
            Main.app.fill(185, 237, 191);
        } else if(blockState == BlockState.CORRECT){
            Main.app.fill(64, 153, 74);
        }
        Main.app.rect(x, y, w, intH);
    }

}
