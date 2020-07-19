package com.tictactoe.tictactoeserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tictactoe")
public class TicTacToeProperties {

  private String app;
  private String broker;
  private String endpoint;
  private String frontendUrl;
  private String userPrefix;
}
