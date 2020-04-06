package com.tictactoe.tictactoeserver.web;

import com.tictactoe.tictactoeserver.model.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameWebController {
  Logger logger = LoggerFactory.getLogger(GameWebController.class);

  @Autowired GameEventHandler gameEventHandler;

  @MessageMapping("/emit.game.event")
  @SendTo("/topic/game.event")
  public void findNextMoveByComputer(@Payload Board board) {

    gameEventHandler.findNextMoveByComputer(board);
  }
}
