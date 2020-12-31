import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * 
 * @author Logan
 * @version 1.4
 */
public class BubbleSortVisualizer extends Actor
{
    //initialize blocklist, position ints, size int, and comparison int
    List blockList;
    int positionA, positionZ;
    int size;
    int comparisons;
    
    //set size to -1 on call to set up block list on first act
    public BubbleSortVisualizer() {
        size = -1;
    }
    
    //update list using displayList method from world
    void updateList() {
        ((SortingVisualization)getWorld()).displayList();
    }
    
    //check if the blocks are sorted fully
    boolean checkComplete() {
        //for all blocks...
        for (int i=0; i < blockList.size(); i++ ){
            //if at the end of the loop, return true as all blocks are sorted
            //if i+1 = size of list, you are looking beyond block index, and must be complete
            if (i+1 == blockList.size()) {
                return true;
            } else {
                //get current block and next block in list
                Block firstBlock = (Block)blockList.get(i);
                Block secondBlock = (Block)blockList.get(i+1);
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
            blockList = ((SortingVisualization)getWorld()).getBlockList();
            size = blockList.size();
            positionZ = size-1;
        } else if (size > 0) { //when size is greater than 0...
            //add one to comparisons
            comparisons+=1;
            //check positionZ value, if positionZ != 0, continue checking list
            if (positionZ != 0) {
                positionA = positionZ-1;
            } else if (positionZ == 0) { //if posZ = 0, restart checking by going to size-1 again
                positionZ = size-1;
                positionA = positionZ-1;
            }
            //get blocks at posZ and posA locations
            Block nextPos = (Block)blockList.get(positionA);
            Block currentPos = (Block)blockList.get(positionZ);
            //if next block in list is bigger, swap blocks
            if (nextPos.getValue() > currentPos.getValue()) {
                blockList.set(positionA, currentPos);
                blockList.set(positionZ, nextPos);
                //update list to change order of blocks
                updateList();
                //check if fully sorted, if it is, stop program and display comparisons and end text
                if (checkComplete()) {
                    System.out.println("Complete!");
                    System.out.println("Total comparisons: " + comparisons);
                    Greenfoot.stop();
                }
            } else { //if next block in list is not bigger, change posZ to next block in list and continue comparisons
                positionZ-=1;
            }
        }
    }
}
