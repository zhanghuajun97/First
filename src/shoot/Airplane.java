package shoot;

import java.util.Random;

/*
 * ��װ�л����Ժ͹��ܵ���
 */

public class Airplane extends Flyer {
    
	private int speed=2;//�л�ÿ������2����λ����
	private int score=5;//�л������Ľ�������
	
	//�����ṩ�Ķ�ȡ�л����������ķ���
	public int getScore() {
		return score;
	}
	
	/*
	 * �л����޲ι��췽��
	 */
	public Airplane() {
		image=ShootGame.airplane;
		width=image.getHeight();
		y=-height;
		Random r=new Random();
		x=r.nextInt(ShootGame.WIDTH-width);
	}
	@Override
	public void step() {
		//�л�ÿ�������ƶ�һ��speed����
		y+=speed;

	}

	@Override
	public boolean outOfBounds() {
		//�л�y���꣬��Ϸ���棬Խ��
		return y>ShootGame.HEIGHT;
	}

}
