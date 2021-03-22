package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	public static Ball ball;
	public double x, y;
	public double ix;
	public double iy;
	public int width, height;
	
	public double dx, dy;
	public double speed = 1;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.ix = x;
		this.iy = y;
		this.width = 3;
		this.height = 3;
		
		int angle = new Random().nextInt(120 - 45) + 45 + 1;
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
		
	}
	
	

	public void tick() {
		
		if(x+(dx*speed) + width >= Game.WIDTH ) {
			dx*= -1;
		}
		
		else if(x+(dx*speed)<0) {
			dx*=-1;
		}
		
		if(y >= Game.HEIGHT) {
			//PONRTO DO INIMIGO
			System.out.println("PONTO DO INIMIGO.");
			new Game();
			return;
		}
		
		else if(y < 0) {
			//PONTO DO JOGADOR
			System.out.println("PONTO DO JOGADOR.");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy = new Rectangle((int)(Game.enemy.x), (int)(Game.enemy.y), Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)) {
			int angle = new Random().nextInt(120 - 45) + 45 + 1;
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			speed+= 0.2;
			if(dy>0) {
				dy*= -1;
			}
		}
		else if(bounds.intersects(boundsEnemy)) {
			dy*= -1;
			speed+= 0.2;
		}
		
		x+= dx*speed;
		y+= dy*speed;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int) x, (int)y, width, height);
	}
	
}
