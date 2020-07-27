import java.awt.Color;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
	Classe que gerencia uma ou mais bolas presentes em uma partida. Esta classe Ã© a responsÃ¡vel por instanciar 
	e gerenciar a bola principal do jogo (aquela que existe desde o Ã­nicio de uma partida), assim como eventuais 
	bolas extras que apareÃ§am no decorrer da partida. Esta classe tambÃ©m deve gerenciar a interaÃ§Ã£o da(s) bola(s)
	com os alvos, bem como a aplicaÃ§Ã£o dos efeitos produzidos para cada tipo de alvo atingido.
*/

public class BallManager {
	
	boolean ballBoosted = false;
	long boostTime = 0;
	
	List <RedBalls> newBalls = new ArrayList<RedBalls>();
	List <FxBall> trails = new ArrayList<FxBall>();
	
	double vx = 0.85 + Math.random() * 0.15;
	double vy = Math.sqrt(1.0 - vx * vx);
	
	FxBall MainTrail = new FxBall(Pong.FIELD_WIDTH/2, 100 + (Pong.FIELD_HEIGHT - 100)/2, 20, 20, Color.YELLOW, 0.65, vx, vy);
	
	/**
		Atributo privado que representa a bola principal do jogo.	
	*/

	private IBall theBall = null;

	/**
		Atributo privado que representa o tipo (classe) das instÃ¢ncias de bola que serÃ£o criadas por esta classe.
	*/

	private Class<?> ballClass = null;

	/**
		Construtor da classe BallManager.
		
		@param className nome da classe que define o tipo das instÃ¢ncias de bola que serÃ£o criadas por esta classe. 
	*/

	public BallManager(String className){

		try{
			ballClass = Class.forName(className);
		}
		catch(Exception e){

			System.out.println("Classe '" + className + "' nÃ£o reconhecida... Usando 'Ball' como classe padrÃ£o.");
			ballClass = Ball.class;
		}
	}

	/**
		Recebe as componetes x e y de um vetor, e devolve as componentes x e y do vetor normalizado (isto Ã©, com comprimento igual a 1.0).
	
		@param x componente x de um vetor que representa uma direÃ§Ã£o.
		@param y componente y de um vetor que represetna uma direÃ§Ã£o.

		@return array contendo dois valores double que representam as componentes x (Ã­ndice 0) e y (Ã­ndice 1) do vetor normalizado (unitÃ¡rio).
	*/
	private double [] normalize(double x, double y){

		double length = Math.sqrt(x * x + y * y);

		return new double [] { x / length, y / length };
	}
	
	/**
		Cria uma instancia de bola, a partir do tipo (classe) cujo nome foi passado ao construtor desta classe.
		O vetor direÃ§Ã£o definido por (vx, vy) nÃ£o precisa estar normalizado. A implemntaÃ§Ã£o do mÃ©todo se encarrega
		de fazer a normalizaÃ§Ã£o.

		@param cx coordenada x da posiÃ§Ã£o inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posiÃ§Ã£o inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
		@param vx componente x do vetor (nÃ£o precisa ser unitÃ¡rio) que representa a direÃ§Ã£o da bola.
		@param vy componente y do vetor (nÃ£o precisa ser unitÃ¡rio) que representa a direÃ§Ã£o da bola.
	*/

	private IBall createBallInstance(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

		IBall ball = null;
		double [] v = normalize(vx, vy);

		try{
			Constructor<?> constructor = ballClass.getConstructors()[0];
			ball = (IBall) constructor.newInstance(cx, cy, width, height, color, speed, v[0], v[1]);
		}
		catch(Exception e){

			System.out.println("Falha na instanciaÃ§Ã£o da bola do tipo '" + ballClass.getName() + "' ... Instanciando bola do tipo 'Ball'");
			ball = new Ball(cx, cy, width, height, color, speed, v[0], v[1]);
		}

		return ball;
	} 

	/**
		Cria a bola principal do jogo. Este mÃ©todo Ã© chamado pela classe Pong, que contem uma instÃ¢ncia de BallManager.

		@param cx coordenada x da posiÃ§Ã£o inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posiÃ§Ã£o inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
		@param vx componente x do vetor (nÃ£o precisa ser unitÃ¡rio) que representa a direÃ§Ã£o da bola.
		@param vy componente y do vetor (nÃ£o precisa ser unitÃ¡rio) que representa a direÃ§Ã£o da bola.
	*/

	public void initMainBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy){

		theBall = createBallInstance(cx, cy, width, height, color, speed, vx, vy);
	}
	
	/**
		MÃ©todo que desenha todas as bolas gerenciadas pela instÃ¢ncia de BallManager.
		Chamado sempre que a(s) bola(s) precisa ser (re)desenhada(s).
	*/
	
	public void draw(){
		
		theBall.draw();
		for (int j = 0; j < MainTrail.ballPositions.size(); j++) {
			MainTrail.ballPositions.get(j).setColor(new Color(1f,1f,0f, (.3f - ((.3f / 20) * (20 - j)))));
			MainTrail.ballPositions.get(j).draw();
		}
		
		for (int i = 0; i < newBalls.size(); i++) {
			newBalls.get(i).ball.draw();
			
			for (int j = 0; j < trails.get(i).ballPositions.size(); j++) {
				trails.get(i).ballPositions.get(j).setColor(new Color(1f, 0f, 0f, (.3f - ((.3f / 20) * (20 - j)))));
				trails.get(i).ballPositions.get(j).draw();
			}
		}
		
	}
	
	/**
		MÃ©todo que atualiza todas as bolas gerenciadas pela instÃ¢ncia de BallManager, em decorrÃªncia da passagem do tempo.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualizaÃ§Ã£o do jogo e o atual.
	*/

	public void update(long delta){
		
		theBall.update(delta);
		Ball newTrail = new Ball(theBall.getCx(), theBall.getCy(), theBall.getHeight(), theBall.getWidth(), Color.YELLOW, theBall.getSpeed(), theBall.getVx(), theBall.getVy());
		MainTrail.ballPositions.add(newTrail);
		if (MainTrail.ballPositions.size() > 20) MainTrail.ballPositions.remove(0);
		
		for (int i = 0; i < newBalls.size(); i++) newBalls.get(i).ball.update(delta);
		
		if (ballBoosted == true) {
			boostTime = boostTime + delta;
			if (boostTime >= BoostTarget.BOOST_DURATION) {
				boostTime = 0;
				ballBoosted = false;
				theBall.setSpeed(theBall.getSpeed()/BoostTarget.BOOST_FACTOR);
			}
		}
		
		for (int i = 0; i < newBalls.size(); i++) {
			Ball newTrails = new Ball(newBalls.get(i).ball.getCx(), newBalls.get(i).ball.getCy(), newBalls.get(i).ball.getHeight(), newBalls.get(i).ball.getWidth(), Color.RED, newBalls.get(i).ball.getSpeed(), newBalls.get(i).ball.getVx(), newBalls.get(i).ball.getVy());
			trails.get(i).ballPositions.add(newTrails);
			if (trails.get(i).ballPositions.size() > 20) trails.get(i).ballPositions.remove(0);
			if (newBalls.get(i).ballBoosted == true) {
				newBalls.get(i).boostTime = newBalls.get(i).boostTime + delta;
				if (newBalls.get(i).boostTime >= BoostTarget.BOOST_DURATION) {
					newBalls.get(i).boostTime = 0;
					newBalls.get(i).ballBoosted = false;
					newBalls.get(i).ball.setSpeed(newBalls.get(i).ball.getSpeed()/BoostTarget.BOOST_FACTOR);
				}
			}
			newBalls.get(i).duplicatorTime = newBalls.get(i).duplicatorTime + delta;
			if (newBalls.get(i).duplicatorTime >= DuplicatorTarget.EXTRA_BALL_DURATION) {
				newBalls.remove(i);
				trails.remove(i);
			}
		}
		
	}
	
	/**
		MÃ©todo que processa as colisÃµes entre as bolas gerenciadas pela instÃ¢ncia de BallManager com uma parede.

		@param wall referÃªncia para uma instÃ¢ncia de Wall para a qual serÃ¡ verificada a ocorrÃªncia de colisÃµes.
		@return um valor int que indica quantas bolas colidiram com a parede (uma vez que Ã© possÃ­vel que mais de 
		uma bola tenha entrado em contato com a parede ao mesmo tempo).
	*/

	public int checkCollision(Wall wall){

		int hits = 0;

		if(theBall.checkCollision(wall)) hits++;
		for (int i = 0; i < newBalls.size(); i++) if(newBalls.get(i).ball.checkCollision(wall)) hits++;

		return hits;
	}

	/**
		MÃ©todo que processa as colisÃµes entre as bolas gerenciadas pela instÃ¢ncia de BallManager com um player.

		@param player referÃªncia para uma instÃ¢ncia de Player para a qual serÃ¡ verificada a ocorrÃªncia de colisÃµes.
	*/
	
	public void checkCollision(Player player){

		theBall.checkCollision(player);
		for (int i = 0; i < newBalls.size(); i++) newBalls.get(i).ball.checkCollision(player);
	}

	/**
		MÃ©todo que processa as colisÃµes entre as bolas gerenciadas pela instÃ¢ncia de BallManager com um alvo.

		@param target referÃªncia para uma instÃ¢ncia de Target para a qual serÃ¡ verificada a ocorrÃªncia de colisÃµes.
	*/

	public void checkCollision(Target target){

		if (theBall.checkCollision(target)) {
			
			if (target.getCy() == 100 + (Pong.FIELD_HEIGHT - 100) * 0.50) {
				//duplicator
				double newVx = 0.85 + Math.random() * 0.15;
				double newVy = Math.sqrt(1.0 - newVx * newVx);
				
				if (newVx < 0 && theBall.getVx() > 0) newVx = Math.abs(newVx);
				if (newVx > 0 && theBall.getVx() < 0) newVx = -newVx;
				
				Ball newBall = new Ball(Pong.FIELD_WIDTH/2, 100 + (Pong.FIELD_HEIGHT - 100)/2, 20, 20, Color.RED, 0.65, newVx, newVy);
				RedBalls balls = new RedBalls ();
				balls.ball = newBall;
				newBalls.add(balls);
				FxBall fxball = new FxBall(Pong.FIELD_WIDTH/2, 100 + (Pong.FIELD_HEIGHT - 100)/2, 20, 20, Color.RED, 0.65, newVx, newVy);
				trails.add(fxball);
			} else {
				//boost
				if (ballBoosted == false) {
					ballBoosted = true;
					theBall.setSpeed(theBall.getSpeed()*BoostTarget.BOOST_FACTOR);
				}
			}
			
		}
		for (int i = 0; i < newBalls.size(); i++) {
			if (newBalls.get(i).ball.checkCollision(target)) {
				
				if (target.getCy() == 100 + (Pong.FIELD_HEIGHT - 100) * 0.50) {
					//duplicator
					double newVx = 0.85 + Math.random() * 0.15;
					double newVy = Math.sqrt(1.0 - newVx * newVx);
					
					if (newVx < 0 && newBalls.get(i).ball.getVx() > 0) newVx = Math.abs(newVx);
					if (newVx > 0 && newBalls.get(i).ball.getVx() < 0) newVx = -newVx;

					Ball newBall = new Ball(Pong.FIELD_WIDTH/2, 100 + (Pong.FIELD_HEIGHT - 100)/2, 20, 20, Color.RED, 0.65, newVx, newVy);
					RedBalls balls = new RedBalls ();
					balls.ball = newBall;
					newBalls.add(balls);
					FxBall fxball = new FxBall(Pong.FIELD_WIDTH/2, 100 + (Pong.FIELD_HEIGHT - 100)/2, 20, 20, Color.RED, 0.65, newVx, newVy);
					trails.add(fxball);
				} else {
					//boost
					if (newBalls.get(i).ballBoosted == false) {
						newBalls.get(i).ballBoosted = true;
						newBalls.get(i).ball.setSpeed(newBalls.get(i).ball.getSpeed()*BoostTarget.BOOST_FACTOR);
					}
				}
				
			}
		}
		
	}
}


