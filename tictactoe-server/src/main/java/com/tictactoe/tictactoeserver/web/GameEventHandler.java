package com.tictactoe.tictactoeserver.web;

import com.tictactoe.tictactoeserver.config.TicTacToeProperties;
import com.tictactoe.tictactoeserver.model.Board;
import com.tictactoe.tictactoeserver.tictactoe.TicTacToeGameEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameEventHandler {
  private static final Logger logger = LoggerFactory.getLogger(GameEventHandler.class);

  private SimpMessagingTemplate template;
  private TicTacToeProperties properties;

  public GameEventHandler(SimpMessagingTemplate template, TicTacToeProperties properties) {
    this.template = template;
    this.properties = properties;
  }

  public void findNextMoveByComputer(Board board) {

    TicTacToeGameEngine ticTacToeGameEngine = new TicTacToeGameEngine();
    Board returnedBoard = ticTacToeGameEngine.TicTacToePlayedByComputer(board);
    logger.info("board: {}", board.toString());
    this.template.convertAndSend(properties.getBroker() + "/game.event", returnedBoard);
  }
}
