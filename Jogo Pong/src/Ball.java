import java.awt.*;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {
	
	double cx;
	double cy;
	double width;
	double height;
	Color color;
	double speed;
	double velx;
	double vely;
	boolean bateY;
	boolean bateX;
	
	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.velx = this.speed;
		this.vely = this.speed;
	}

	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){
		GameLib.setColor(Color.YELLOW);
		GameLib.fillRect(this.cx, this.cy, 20, 20);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		this.cy = this.cy + this.vely*delta;
		this.cx = this.cx + this.velx*delta;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		if (playerId.equals(Pong.PLAYER1)) {
			if (this.velx < 0) this.velx = this.velx*(-1);
		} else if (playerId.equals(Pong.PLAYER2)) {
			if (this.velx > 0) this.velx = this.velx*(-1);
		}
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		if (wallId == Pong.BOTTOM) this.vely = this.vely*(-1);
		else if (wallId == Pong.TOP) this.vely = this.vely*(-1);
		else if (wallId == Pong.LEFT) this.velx = this.velx*(-1);
		else if (wallId == Pong.RIGHT) this.velx = this.velx*(-1);
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		if (wall.getId() == Pong.BOTTOM) {
			if (this.cy >= Pong.FIELD_HEIGHT - 20 - this.width/2 && this.vely > 0) return true;
		} else if (wall.getId() == Pong.TOP) {
			if (this.cy <= 100 + 20 + this.width/2 && this.vely < 0) return true;
		} else if (wall.getId() == Pong.RIGHT) {
			if (this.cx >= Pong.FIELD_WIDTH - 20 - this.width/2 && this.velx > 0) return true;
		} else if (wall.getId() == Pong.LEFT) {
			if (this.cx <= 20 + this.width/2 && this.velx < 0) return true;
		}
		return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		if (this.cx >= player.cx - player.width / 2 && this.cx <= player.cx + player.width / 2 && (this.cy <= player.cy + player.height / 2) && (this.cy >= player.cy - player.height / 2)) {
			return true;
		}
		
		return false;
	}
	
	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){
		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){
		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){
		return this.speed;
	}

}
