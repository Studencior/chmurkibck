package pl.game.tictac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import pl.game.tictac.dto.GameJoinMessage;
import pl.game.tictac.dto.GameMove;
import pl.game.tictac.dto.GameState;
import pl.game.tictac.dto.GameUpdate;
import pl.game.tictac.model.Game;
import pl.game.tictac.model.Player;
import pl.game.tictac.repository.PlayerRepository;
import pl.game.tictac.service.GameService;

@Controller
@CrossOrigin(origins = "http://34.235.143.104:4200") // Angular app address
public class WebSocketController {

    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerRepository playerRepository;

    @MessageMapping("/game/move/{gameId}")
    @SendTo("/topic/game/{gameId}")
    public GameState makeMove(@DestinationVariable Long gameId, GameMove move) {
        return gameService.processMove(gameId, move);
    }




    @MessageMapping("/game/join/{gameId}")
    @SendTo("/topic/game/{gameId}")
    public GameUpdate joinGame(@DestinationVariable Long gameId, @Payload GameJoinMessage joinMessage) {
        Player player = playerRepository.findByUsername(joinMessage.getUsername())
                .orElseThrow(() -> new RuntimeException("Player not found"));
        Game game = gameService.joinGame(gameId, player.getId());
        return new GameUpdate(game);
    }
    // ... other WebSocket-related methods ...
}
