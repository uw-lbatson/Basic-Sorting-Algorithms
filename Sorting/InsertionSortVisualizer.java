import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * 
 * @author Logan
 * @version 1.2
 */
public class InsertionSortVisualizer extends Actor
{
    //initialize blocklist, position ints, size int, and comparison int
    List list;
    int positionA, positionZ;
    int size;
    int comparisons;
    
    //set size to -1 on call to set up block list on first act
    public InsertionSortVisualizer() {
        size = -1;
    }
    
    //update list using displayList method from world
    void updateList() {
        ((SortingVisualization)getWorld()).displayList();
    }
    
    //check if the blocks are sorted fully
    boolean checkComplete() {
        //for all blocks...
        for (int i=0; i < list.size(); i++ ){
            //if at the end of the loop, return true as all blocks are sorted
            //if i+1 = size of list, you are looking beyond block index, and must be complete
            if (i+1 == list.size()) {
                return true;
            } else {
                //get current block and next block in list
                Block firstBlock = (Block)list.get(i);
                Block secondBlock = (Block)list.get(i+1);
                //if current blocks value is less than or equal to next block, continue to check other blocks
                if ( firstBlock.getValue() <= secondBlock.getValue()) {
                    continue;
                } else { //if current block is bigger than next block, sorting isn't complete
                    return false;
                }
            }
        }
        return false;
    }
    
    public void act() {
        //on first act, get blocklist, size, and initialize positionZ int
        if (size <= 0) {
            list = ((SortingVisualization)getWorld()).getBlockList();
            size = list.size();
        } else if (size > 0) {
            Block temp;
            for (int i=0; i<size; i++) {
                for (int j=i; j>0; j--) {
                    if (((Block)list.get(j)).getValue() < ((Block)list.get(j-1)).getValue()) {
                        temp = (Block)list.get(j);
                        list.set(j, (Block)list.get(j-1));
                        list.set(j-1, temp);
                        updateList();
                        Greenfoot.delay(10);
                    }
                }
            }
        }
    }
}
