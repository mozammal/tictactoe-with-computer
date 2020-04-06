package com.tictactoe.tictactoeserver.tictactoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TicTacToeUtilityFunction implements GameUtilityFunction {
  private TreeNode treeNode;

  private static final Logger logger = LoggerFactory.getLogger(TicTacToeUtilityFunction.class);

  TicTacToeUtilityFunction(TreeNode treeNode) {
    this.treeNode = treeNode;
  }

  @Override
  public int score() {

    int score = scoreEvaluatorForXOrO('O');
    if (score == 1) {
      return EvalStateEicTacToe.WIN.getCurrentEvalPoint();
    }
    score = scoreEvaluatorForXOrO('X');
    if (score == 1) {
      return EvalStateEicTacToe.LOSE.getCurrentEvalPoint();
    } else if (score == 2) {
      return EvalStateEicTacToe.ONGOING.getCurrentEvalPoint();
    } else {
      return EvalStateEicTacToe.DRAW.getCurrentEvalPoint();
    }
  }

  private int scoreEvaluatorForXOrO(Character c) {
    int cntDiagonalLeftToRight = 0, cntDiagonalRightToLeft = 0, cntEmptyCell = 0;
    final int TICTACTOE_BOARD_SIZE = treeNode.getBoards().length / 3;

    for (int i = 0; i < TICTACTOE_BOARD_SIZE; i++) {
      int cntRow = 0;
      int cntColumn = 0;
      for (int j = 0; j < TICTACTOE_BOARD_SIZE; j++) {
        if (treeNode.getBoards()[i * TICTACTOE_BOARD_SIZE + j] != null
            && treeNode.getBoards()[i * TICTACTOE_BOARD_SIZE + j].equals(c)) {
          cntRow++;
        }
        if (treeNode.getBoards()[j * TICTACTOE_BOARD_SIZE + i] != null
            && treeNode.getBoards()[j * TICTACTOE_BOARD_SIZE + i].equals(c)) {
          cntColumn++;
        }
      }
      if (cntRow == TICTACTOE_BOARD_SIZE || cntColumn == TICTACTOE_BOARD_SIZE) {
        return 1;
      }
    }

    for (int j = 0; j < TICTACTOE_BOARD_SIZE; j++) {
      if (treeNode.getBoards()[(j + 1) * TICTACTOE_BOARD_SIZE - 1 - j] != null
          && treeNode.getBoards()[(j + 1) * TICTACTOE_BOARD_SIZE - 1 - j].equals(c)) {
        cntDiagonalRightToLeft++;
      }
      if (treeNode.getBoards()[j * TICTACTOE_BOARD_SIZE + j] != null
          && treeNode.getBoards()[j * TICTACTOE_BOARD_SIZE + j].equals(c)) {
        cntDiagonalLeftToRight++;
      }
    }

    if (cntDiagonalLeftToRight == TICTACTOE_BOARD_SIZE
        || cntDiagonalRightToLeft == TICTACTOE_BOARD_SIZE) {
      return 1;
    }

    for (int j = 0; j < TICTACTOE_BOARD_SIZE * TICTACTOE_BOARD_SIZE; j++) {
      if (treeNode.getBoards()[j] == null) {
        cntEmptyCell++;
      }
    }

    return cntEmptyCell > 0 ? 2 : 0;
  }
}
