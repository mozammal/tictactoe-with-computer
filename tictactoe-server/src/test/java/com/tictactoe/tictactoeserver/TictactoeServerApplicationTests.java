package com.tictactoe.tictactoeserver;

import com.tictactoe.tictactoeserver.tictactoe.Algorithm;
import com.tictactoe.tictactoeserver.tictactoe.AlphaBetaAlgorithm;
import com.tictactoe.tictactoeserver.tictactoe.TreeNode;
import jdk.nashorn.internal.AssertsEnabled;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.nio.cs.Surrogate;

@SpringBootTest
class TictactoeServerApplicationTests {

  @Test
  void contextLoads() {}

  @Test
  public void shouldReturnComputerWinWheAllCellInARowIsO() {
    TreeNode treeNode = new TreeNode(3);
    treeNode.setBoards(new Character[] {'O', 'O', 'O', 'X', 'X', 'O', 'X', 'X', null});
    Algorithm algorithm = new AlphaBetaAlgorithm(treeNode);
    int score = ((AlphaBetaAlgorithm) algorithm).getGameUtilityFunction().score();
    Assertions.assertThat(score).isEqualTo(1);
  }
}
