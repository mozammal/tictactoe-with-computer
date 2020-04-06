package com.tictactoe.tictactoeserver.tictactoe;

import com.tictactoe.tictactoeserver.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicTacToeGameEngine {
  private static final int BOARD_SIZE = 3;
  private static Logger logger = LoggerFactory.getLogger(TicTacToeGameEngine.class);

  private enum TicTacToGameStateForComputer {
    WIN,
    DRAW
  }

  public Board TicTacToePlayedByComputer(Board board) {

    boolean isSolutionFound = false;
    TreeNode treeNode = new TreeNode(BOARD_SIZE);
    treeNode.setBoards(board.getBoard());
    Algorithm algorithm = new AlphaBetaAlgorithm(treeNode);
    Character[] boardGeneratedByMAxScore = treeNode.getBoards().clone();
    int maxScoreByComputer = Integer.MIN_VALUE;
    for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
      if (treeNode.getBoards()[i] == null) {
        treeNode.getBoards()[i] = 'O';
        int scoreByComputer = algorithm.execute();
        if (maxScoreByComputer < scoreByComputer) {
          boardGeneratedByMAxScore = treeNode.getBoards().clone();
          maxScoreByComputer = scoreByComputer;
          logger.debug("win: (" + (i / 3) + "," + (i % 3) + ")");
        }
        treeNode.getBoards()[i] = null;
      }
    }
    board.setBoard(boardGeneratedByMAxScore);
    return board;
  }
}
