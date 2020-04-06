package com.tictactoe.tictactoeserver.tictactoe;

import lombok.Data;

@Data
public class TreeNode {
  private Character[] boards;

  public TreeNode(int boardSize) {
    boards = new Character[boardSize * boardSize];
  }
  public String toString() {

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < boards.length; i++) {
      for (int j = 0; j < boards.length; j++) sb.append(boards[i * boards.length + j]).append(" ");
      sb.append("\n");
    }
    return sb.toString();
  }
}
