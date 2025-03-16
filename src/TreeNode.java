import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Comparable<TreeNode> {
    private final City parent;
    private TreeNode parentNode; // Store parent for path reconstruction
    private int f, g, h;

    public TreeNode(City parent) {
        this.parent = parent;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public City getParent() {
        return parent;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public int compareTo(TreeNode other) {
        return Integer.compare(this.f, other.f);
    }
}
