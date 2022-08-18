package low.level.design.snakeladder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SnakeAndLadderService {
	private SnakeAndLadderBoard snakeAndLadderBoard;
	private int initialNumberOfPlayers;
	private Queue<Player> players;

	private boolean isGameCompleted;

	private int noOfDices;
	private boolean shouldGameContinueTillLastPlayer;
	private boolean shouldAllowMultipleDiceRollOnSix;

	private static final int DEFAULT_BOARD_SIZE = 100;
	private static final int DEFAULT_NO_OF_DICES = 1;

	public SnakeAndLadderService(int boardSize) {
		this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
		this.players = new LinkedList<Player>();
		this.noOfDices = SnakeAndLadderService.DEFAULT_NO_OF_DICES;
	}

	public SnakeAndLadderService() {
		this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
	}

	public void setNoOfDices(int noOfDices) {
		this.noOfDices = noOfDices;
	}

	public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
		this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
	}

	public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
		this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
	}

	/**
	 * ==================Initialize board==================
	 */

	public void setPlayers(List<Player> players) {
		this.players = new LinkedList<Player>();
		this.initialNumberOfPlayers = players.size();
		Map<String, Integer> playerPieces = new HashMap<String, Integer>();
		for (Player player : players) {
			this.players.add(player);
			playerPieces.put(player.getId(), 0);
		}
		snakeAndLadderBoard.setPlayer(playerPieces);
	}

	public void setSnakes(List<Snake> snakes) {
		snakeAndLadderBoard.setSnakes(snakes);
	}

	public void setLadders(List<Ladder> ladders) {
		snakeAndLadderBoard.setLadders(ladders);
	}

	/**
	 * ==========Core business logic for the game==========
	 */

	private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
		int previousPosition;

		do {
			previousPosition = newPosition;
			for (Snake snake : snakeAndLadderBoard.getSnakes()) {
				if (snake.getStart() == newPosition) {
					newPosition = snake.getEnd();
				}
			}

			for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
				if (ladder.getStart() == newPosition) {
					newPosition = ladder.getEnd();
				}
			}
		} while (newPosition != previousPosition);
		return newPosition;
	}

	private void movePlayer(Player player, int positions) {
		int oldPosition = snakeAndLadderBoard.getPlayer().get(player.getId());
		int newPosition = oldPosition + positions;

		int boardSize = snakeAndLadderBoard.getSize();

		if (newPosition > boardSize) {
			newPosition = oldPosition;
		} else {
			newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
		}

		snakeAndLadderBoard.getPlayer().put(player.getId(), newPosition);

		System.out.println(
				player.getName() + " rolled a " + positions + " and moved from " + oldPosition + " to " + newPosition);
	}

	private int getTotalValueAfterDiceRolls() {
		return DiceService.roll();
	}

	private boolean hasPlayerWon(Player player) {
		int playerPosition = snakeAndLadderBoard.getPlayer().get(player.getId());
		int winningPosition = snakeAndLadderBoard.getSize();
		return playerPosition == winningPosition;
	}

	private boolean isGameCompleted() {
		int currentNumberOfPlayers = players.size();
		return currentNumberOfPlayers < initialNumberOfPlayers;
	}

	public void startGame() {
		while (!isGameCompleted()) {
			int totalDiceValue = getTotalValueAfterDiceRolls();
			Player currentPlayer = players.poll();
			movePlayer(currentPlayer, totalDiceValue);
			if (hasPlayerWon(currentPlayer)) {
				snakeAndLadderBoard.getPlayer().remove(currentPlayer.getId());
			} else {
				players.add(currentPlayer);
			}
		}
	}

	/**
	 * =======================================================
	 */
}
