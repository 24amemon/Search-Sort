import processing.core.PApplet;
import java.util.ArrayList;
// import java.util.Random;
import processing.data.Table;
import processing.data.TableRow;


public class Main extends PApplet {

    public static PApplet app;
    public ArrayList<Double> view = new ArrayList<Double>();
    public ArrayList<Block> blocks = new ArrayList<Block>();
    public ArrayList<Block> blocks2 = new ArrayList<Block>();
    public ArrayList<Block> blocksReset = new ArrayList<Block>();
    private static int WIDTH = 1300;
    private static int HEIGHT = 700;
    private String s = " ";
    private double t;
    private int q = 0;
    private int bottom;
    private int top;

    private int target;

    public static void main(String[] args){
        PApplet.main("Main");
    }

    public Main() {
        super();
        app = this;
    }
    public void settings(){
        size(WIDTH, HEIGHT);
    }

    public void setup(){
        background(255);
        Table table = loadTable("data/WinkData.csv", "header");
        for (TableRow row : table.rows()) {
            double visitors = row.getDouble("WinkVisitors");
            view.add(visitors);
        }
        for(int i = 1; i < view.size(); i++){
            int xVal = (WIDTH/26) - 50 + (47 * i);
            double tall = (view.get(i - 1)) * 100;
            Block b = new Block(xVal, 50, 30, tall, BlockState.DEFAULT);
            blocks.add(i - 1, b);
        }
        top = 25;
        bottom = 0;
        printBlocks(blocks);

    }

    public void draw(){
    }

    public void keyPressed() {
        if (key == 's'){
            selectSort();
            System.out.println("sorted!");
        } if(Character.isDigit(key) || key == '.'){
            s = s + key;
        } if(key == RETURN || key == ENTER){
            t = Double.parseDouble(s);
            System.out.println("target is: " + t);
        } if(key == 'b'){
            binarySearch(t);
        } if(key == 'r'){
            System.out.println("reset");
            blocks2.clear();
            selectSort();
            top = 25;
            bottom = 0;
            s = " ";
        }
    }

    private void displayInstructions(){
        fill(50);
        strokeWeight(5);
        text("Click 's' to selection-sort the data.",10, 660);
        text("Type in the # you want to search for; press return to save.",10, 670);
        text("Press 'b' to iterate through your binary search!",10, 680);
        text("To reset the search, click 'r' and type in your new #. Have fun!",10, 690);
        strokeWeight(1);
    }

    private void selectSort(){
        for(int i = 0; i < view.size() - 1; i++){
            int minIndex = i;
            for(int j = minIndex + 1; j < view.size(); j++){
                if(view.get(j) < view.get(minIndex)){
                    minIndex = j;
                }
            }
            // replace current min with new min
            double temp = view.get(i);
            view.set(i, view.get(minIndex));
            view.set(minIndex, temp);
        }
        for(int i = 1; i < view.size(); i++){
            int xVal = (WIDTH/26) - 50 + (47 * i);
            double tall = (view.get(i - 1)) * 100;
            Block b = new Block(xVal, 50, 30, tall, BlockState.DEFAULT);
            blocks2.add(i - 1, b);
        }
        blocks2.get(bottom).colorChange(2);
        blocks2.get(top).colorChange(2);
        printBlocks(blocks2);
    }

    private void printBlocks(ArrayList<Block> arr){
        clear();
        background(255);
        for(int i = 0; i < arr.size(); i++){
            Block b = arr.get(i);
            b.display();
        }
        displayInstructions();
    }

    private int binarySearch(double target){
        System.out.println("top: " + top + ", bottom: " + bottom);
        if (view.get(bottom) <= view.get(top)){
            int mid = (bottom + top) / 2;
            double cur = view.get(mid);
            if(cur == target){
                blocks2.get(mid).colorChange(3);
                System.out.println("middle index is: " + mid + "!");
                printBlocks(blocks2);
                return mid;
            } else if(cur < target){
                blocks2.get(mid + 1).colorChange(2);
                blocks2.get(bottom).colorChange(1);
                bottom = mid + 1;
            } else {
                Block b = blocks2.get(mid - 1);
                b.colorChange(2);
                blocks2.get(top).colorChange(1);
                top = mid - 1;
            }
            printBlocks(blocks2);
        }
        return -1;
    }


}