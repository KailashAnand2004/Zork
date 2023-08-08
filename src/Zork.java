/**
 * The StoryTree class.
 *
 * @author
 *   Kailash Anand ID:115158238
 * Assignment:
 *    Recitation: R01
 *    Homework #5 for CSE 214
 * Date:
 *    April 10th, 2023
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;

public class Zork
{
    static Scanner input = new Scanner(System.in);

    /**
     * used to edit the tree.
     *
     * @param tree
     *  The tree to edit.
     *
     * @throws IOException
     */
    public static void editTree(StoryTree tree) throws IOException {
        boolean run = true;
        while(run)
        {
            System.out.println("Zork Editor:\n" +
                    "    V: View the cursor's position, option and message.\n" +
                    "    S: Select a child of this cursor (options are 1, 2, and 3).\n" +
                    "    O: Set the option of the cursor.\n" +
                    "    M: Set the message of the cursor.\n" +
                    "    A: Add a child StoryNode to the cursor.\n" +
                    "    P: Return to parent.\n" +
                    "    D: Delete one of the cursor's children and all its descendants.\n" +
                    "    R: Move the cursor to the root of the tree.\n" +
                    "    Q: Quit editing and return to main menu.");

            System.out.print("\nPlease enter a choice: ");
            String enter = input.nextLine();
            System.out.println();
            switch(enter.toUpperCase())
            {
                case "V":
                    System.out.println("Position: " + tree.getCursorPosition());
                    System.out.println("Option: " + tree.getCursorOption());
                    System.out.println("Message: " + tree.getCursorMessage());
                    System.out.println("\n");
                    break;

                case "S":

                    if(tree.getCursor().getLeftChild() == null)
                        System.out.println("no child");
                    else {
                        System.out.print("Please select a child: [1,2,3]");
                        String child = input.nextLine();

                        try {
                            tree.selectChild((child));
                        } catch (NodeNotPresentException n) {
                            if (child.matches("\\d")) {
                                System.out.print("Error. No child " + child + " for the current node.\n");
                            }
                        }
                    }
                    break;

                case "O":
                    System.out.print("Please enter a new Option: ");
                    String option = input.nextLine();

                    tree.setCursorOption(option);
                    System.out.println("\n");
                    break;

                case "M":
                    System.out.print("Please enter a new Message: ");
                    String message = input.nextLine();

                    tree.setCursorMessage(message);
                    System.out.println("\n");
                    break;

                case "A":
                    System.out.print("Enter an option: ");
                    String newOption = input.nextLine();
                    System.out.print("Enter a message: ");
                    String newMessage = input.nextLine();

                    System.out.println("\n");
                    try
                    { tree.addChild(newOption,newMessage); }
                    catch(TreeFullException t)
                    { System.out.println("Error\n"); }
                    break;

                case "P":
                    tree.returnToParent();
                    System.out.print("The cursor returned to its parent.\n");
                    break;

                case "D":
                    if(tree.getCursor().getLeftChild() == null)
                        System.out.println("no child");
                    else {
                        System.out.print("Please select child: ");
                        String childRemoved = input.nextLine();

                        try {
                            tree.removeChild(childRemoved);
                        } catch (NodeNotPresentException n) {
                            if (childRemoved.matches("\\d")) {
                                System.out.print("Error. No child " + childRemoved + " for the current node.\n");
                            } else {
                                System.out.println("no child\n");
                            }
                        }
                    }
                    break;

                case "R":
                    tree.resetCursor();
                    System.out.println("Cursor moved to root.\n");
                    break;

                case "Q":
                    run = false;
                    break;
            }
        }
    }

    /**
     * Used to play the tree
     *
     * @param tree
     *  The tree to be played with.
     */
    public static void playTree(StoryTree tree)
    {
        tree.resetCursor();
        int choice = -1;

        while(tree.getGameState().toString().equals("GAME_NOT_OVER"))
        {
            System.out.println(tree.getCursorMessage());
            for (int i = 0; i < tree.getOptions().length; i++)
            {
                for (int j = 0; j < tree.getOptions()[0].length; j++)
                {
                    if (tree.getOptions()[i][j] != null) {
                        System.out.print(tree.getOptions()[i][j]);
                    }
                }
                System.out.println();
            }
            System.out.print("\nPlease make a choice: ");
            choice = input.nextInt();
            input.nextLine();
            tree.play(choice);
        }

        System.out.println("\n");
    }

    /**
     * The main method.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        boolean run = true;
        System.out.print("Please enter file name: ");
        String file = input.nextLine();

        StoryTree newTree = new StoryTree();


        newTree = StoryTree.readTree(file);

        System.out.println("\nLoading game from file...\n");
        System.out.println("File loaded!\n");

        while(run)
        {
            System.out.print("Would you like to edit(E), play(P) or quit(Q)? ");
            String enter = input.nextLine();
            System.out.println("\n");

            switch (enter.toUpperCase())
            {
                case "E":
                    editTree(newTree);
                    break;

                case "P":
                    playTree(newTree);
                    break;

                case "Q":
                    if(!newTree.getCursorOption().equals("root"))
                    StoryTree.saveTree("SampleStory.txt", newTree);
                    System.out.println("Program terminating normally.");
                    run = false;
                    break;
            }
        }
    }
}