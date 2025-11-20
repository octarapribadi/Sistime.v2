package entity;

public class CModel{
    public static final int NONE = 0;
    public static final int NEW = 1;
    public static final int EDIT = 2;
    public static final int DELETE = 3;

    int rowState;
    public int getRowState() {
        return rowState;
    }
    public void setRowState(int rowState) {
        this.rowState = rowState;
    }
}
