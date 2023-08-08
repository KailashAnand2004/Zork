/**
 * The StoryTreeNode class.
 *
 * @author
 *   Kailash Anand ID:115158238
 * Assignment:
 *    Recitation: R01
 *    Homework #5 for CSE 214
 * Date:
 *    April 10th, 2023
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StoryTreeNode
{
    //A special string sequence which represents a leaf node which is a winning state.
    private static final String WIN_MESSAGE = "YOU WIN";
    //A special string sequence which indicates that a leaf node is a losing state.
    private static final String LOSE_MESSAGE = "YOU LOSE";
    private String position;//A String representing the position of the node in the tree.
    private String option;//A String representing the option which will select this node.
    private String message;//A String representing the message which is displayed after this node has been selected by the user.
    private StoryTreeNode leftChild;
    private StoryTreeNode middleChild;
    private StoryTreeNode rightChild;

    /**
     * A String representing the message which is displayed after this node has been selected by the user.
     */
    public StoryTreeNode()
    {}

    /**
     * Getter method for Win Message
     *
     * @return
     *  Returns the WinMessage.
     */
    public String getWinMessage()
    { return WIN_MESSAGE; };

    /**
     * Getter method for Lose Message
     *
     * @return
     *  Returns the LoseMessage
     */
    public String getLoseMessage()
    { return LOSE_MESSAGE; }

    /**
     * Getter method for position
     *
     * @return
     *  Returns the position
     */
    public String getPosition()
    { return position; }

    /**
     * Setter method for position
     *
     * @param position
     *  The position to be set
     */
    public void setPosition(String position)
    { this.position = position; }

    /**
     * Getter method for option
     *
     * @return
     *  Returns the option
     */
    public String getOption()
    { return option; }

    /**
     * Setter method for option
     *
     * @param option
     *  The option to be set
     */
    public void setOption(String option)
    { this.option = option; }

    /**
     * Getter method for message
     *
     * @return
     *  Returns the message.
     */
    public String getMessage()
    { return message; }

    /**
     * Setter method for message
     *
     * @param message
     *  The message to be set.
     */
    public void setMessage(String message)
    { this.message = message; }

    /**
     * Getter method for leftChild
     *
     * @return
     *  Returns the leftChild
     */
    public StoryTreeNode getLeftChild()
    { return leftChild; }

    /**
     * Setter method for leftChild
     *
     * @param newLeftChild
     *  The new leftChild to be set with.
     */
    public void setLeftChild(StoryTreeNode newLeftChild)
    { leftChild = newLeftChild; }

    /**
     * Getter method for middleChild
     *
     * @return
     *  Returns the middleChild
     */
    public StoryTreeNode getMiddleChild()
    { return middleChild; }

    /**
     * Setter method for middleChild
     *
     * @param newMiddleChild
     *  The new middleChild to be set with.
     */
    public void setMiddleChild(StoryTreeNode newMiddleChild)
    { middleChild = newMiddleChild; }

    /**
     * Getter method for rightChild
     *
     * @return
     *  Returns the rightChild
     */
    public StoryTreeNode getRightChild()
    { return rightChild; }

    /**
     * Setter method for rightChild
     *
     * @param newRightChild
     *  The new rightChild to be set with.
     */
    public void setRightChild(StoryTreeNode newRightChild)
    { rightChild = newRightChild; }

    /**
     * Checks if this node has any children.
     *
     * @Preconditions
     *  This node is initialized
     *
     * @PostConditions
     *  The tree remains unchanged
     *
     * @return
     *  True if there are no children.
     *  False if there is atleast one child.
     */
    public boolean isLeaf()
    {
        if(leftChild == null && middleChild == null && rightChild == null)
        { return true; }

        return false;
    }

    /**
     * Checks if this is a winning node.
     * In order to be a winning node, this node must be a leaf and contain the winning message.
     *
     * @Preconditions
     *  This node is initialized.
     *
     * @Postconditions
     *  The tree remains unchanged.
     *
     * @return
     *  True if this node is a leaf and contains the WIN_MESSAGE in the message.
     *  False otherwise.
     */
    public boolean isWinningNode()
    {
        if(isLeaf() && message.contains(WIN_MESSAGE))
        {
            return true;
        }

        return false;
    }

    /**
     * Checks if this is a losing node.
     * In order to be a losing node, this node must be a leaf and contain the losing message.
     *
     * @Preconditions
     *  This node is initialized.
     *
     * @Postcnditions
     *  The tree remains unchanged.
     *
     * @return
     *  True if this is a leaf and does not contain the WIN_MESSAGE in the message.
     *  False otherwise.
     */
    public boolean isLosingNode()
    {
        if(isLeaf() && message.contains(LOSE_MESSAGE))
        {
            return true;
        }

        return false;
    }

    /**
     * Used to fix the position after removing a child
     */
    public void fixPosition() {
        if (this.getLeftChild() != null) {
            this.getLeftChild().setPosition(this.getPosition() + "-1");
            this.getLeftChild().fixPosition();
        }

        if (this.getRightChild() != null) {
            this.getRightChild().setPosition(this.getPosition() + "-3");
            this.getRightChild().fixPosition();
        }

        if (this.getMiddleChild() != null) {
            this.getMiddleChild().setPosition(this.getPosition() + "-2");
            this.getMiddleChild().fixPosition();
        }
    }
}
