package com.tictactoe.tictactoeserver.web;

import com.tictactoe.tictactoeserver.config.TicTacToeProperties;
import com.tictactoe.tictactoeserver.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GameWebController {
  Logger logger = LoggerFactory.getLogger(GameWebController.class);

  @Autowired GameEventHandler gameEventHandler;

  private SimpMessageSendingOperations template;
  private TicTacToeProperties properties;

  public GameWebController(SimpMessageSendingOperations template, TicTacToeProperties properties) {
    this.template = template;
    this.properties = properties;
  }

  @MessageMapping("/emit.game.event")
  public void findNextMoveByComputer(
      @Payload Board board, @Header("simpSessionId") String sessionId) {
    Board nextBoard = gameEventHandler.findNextMoveByComputer(board);
    this.template.convertAndSendToUser(
        sessionId, properties.getBroker() + "/game.event",
            nextBoard,
            createHeaders(sessionId));
  }

  private MessageHeaders createHeaders(String sessionId) {
    SimpMessageHeaderAccessor headerAccessor =
        SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
    headerAccessor.setSessionId(sessionId);
    headerAccessor.setLeaveMutable(true);
    return headerAccessor.getMessageHeaders();
  }
}
