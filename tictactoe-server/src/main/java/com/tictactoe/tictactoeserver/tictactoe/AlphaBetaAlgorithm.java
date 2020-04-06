package com.tictactoe.tictactoeserver.tictactoe;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class AlphaBetaAlgorithm implements Algorithm {

  private static final Logger logger = LoggerFactory.getLogger(AlphaBetaAlgorithm.class);

  private TreeNode treeNode;
  private GameUtilityFunction gameUtilityFunction;

  public AlphaBetaAlgorithm(TreeNode treeNode) {
    this.treeNode = treeNode;
    this.gameUtilityFunction = new TicTacToeUtilityFunction(treeNode);
  }

  @Override
  public int execute() {
    return alphaBetaSearch(Integer.MIN_VALUE, Integer.MAX_VALUE, false, 100);
  }

  private int alphaBetaSearch(int alpha, int beta, boolean player, int depth) {

    int currentScore = gameUtilityFunction.score();
    if (currentScore != 2) {
      return currentScore;
    }

    for (int i = 0; i < treeNode.getBoards().length; i++) {
      if (treeNode.getBoards()[i] == null) {
        if (player) treeNode.getBoards()[i] = 'O';
        else treeNode.getBoards()[i] = 'X';
        if (player) {
          alpha = Math.max(alpha, alphaBetaSearch(alpha, beta, false, depth - 1));
        } else {
          beta = Math.min(beta, alphaBetaSearch(alpha, beta, true, depth - 1));
        }
        treeNode.getBoards()[i] = null;
        if (player && alpha >= beta) return alpha;
        if (!player && beta <= alpha) return beta;
      }
    }

    return player ? alpha : beta;
  }
}
