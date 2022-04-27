import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String [] args) {
    List<String> tempTxList = new ArrayList<String>();
    tempTxList.add("A");
    tempTxList.add("B");
    tempTxList.add("C");
    tempTxList.add("D");
    tempTxList.add("E");
    
    MerkleTrees merkleTrees = new MerkleTrees(tempTxList);
    merkleTrees.merkle_tree();
    System.out.println("root : " + merkleTrees.getRoot());
  }
}
