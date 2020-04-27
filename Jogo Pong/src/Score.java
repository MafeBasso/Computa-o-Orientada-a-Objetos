import java.awt.*;

/**
	Esta classe representa um placar no jogo. A classe princial do jogo (Pong)
	instancia dois objeto deste tipo, cada um responsável por gerenciar a pontuação
	de um player, quando a execução é iniciada.
*/

public class Score {
	
	String player;
	int pontos = 0;
	
	/**
		Construtor da classe Score.

		@param playerId uma string que identifica o player ao qual este placar está associado.
	*/

	public Score(String playerId){
		this.player = playerId;
	}

	/**
		Método de desenho do placar.
	*/

	public void draw(){
		if (this.player.equals(Pong.PLAYER1)) {
			GameLib.setColor(Color.GREEN);
			GameLib.drawText("Player 1: " + getScore(), 70, GameLib.ALIGN_LEFT);
		} else {
			GameLib.setColor(Color.BLUE);
			GameLib.drawText("Player 2: " + getScore(), 70, GameLib.ALIGN_RIGHT);
		}
	}

	/**
		Método que incrementa em 1 unidade a contagem de pontos.
	*/

	public void inc(){
		pontos++;
	}

	/**
		Método que devolve a contagem de pontos mantida pelo placar.

		@return o valor inteiro referente ao total de pontos.
	*/

	public int getScore(){
		return pontos;
	}
}
