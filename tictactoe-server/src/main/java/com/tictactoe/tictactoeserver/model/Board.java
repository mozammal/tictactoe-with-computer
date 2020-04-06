package com.tictactoe.tictactoeserver.model;

import java.util.Arrays;

public class Board {
  private Character[] board;

  public Board() {}

  public Character[] getBoard() {
    return board;
  }

  public void setBoard(Character[] board) {
    this.board = board;
  }

  @Override
  public String toString() {
    return "Board{" + "board=" + Arrays.toString(board) + '}';
  }
}
