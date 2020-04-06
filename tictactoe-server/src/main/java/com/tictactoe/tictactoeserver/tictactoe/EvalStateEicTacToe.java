package com.tictactoe.tictactoeserver.tictactoe;

public enum EvalStateEicTacToe {
  LOSE(-1),
  WIN(1),
  DRAW(0),
  ONGOING(2);
  private int currentEvalPoint;

  EvalStateEicTacToe(int currentEvalPoint) {
    this.currentEvalPoint = currentEvalPoint;
  }
  public int getCurrentEvalPoint() {
    return currentEvalPoint;
  }
}
