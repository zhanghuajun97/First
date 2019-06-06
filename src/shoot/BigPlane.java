package shoot;

import java.util.Random;

/*
 * ��װ��ɻ����Ժ͹��ܵ���
 */

public class BigPlane extends Flyer {
	
	//���影�����͵ı�ѡ���
	public static final int DOUBLE_FIRE=0;//��������Ϊ0��˵������˫������
	public static final int FILE=1;//����������1��˵������һ������
	
	//��ɻ���˽�г�Ա
	private int xspeed=1;//ˮƽ�ƶ����ٶ�Ϊ1
	private int yspeed=2;//��ֱ�ƶ����ٶ�Ϊ2
	private int awardType;//��ǰ��ɻ�����Ľ�������
	
	//�����ṩ�Ķ�ȡ��ɻ��������͵ķ���
	public int getAwardType() {
		return awardType;
	}
	
	/*
	 * ��ɻ����޲ι��췽��
	 * (non-Javadoc)
	 * @see shoot.Flyer#step()
	 */
    
	public BigPlane() {
		//step1:���������л�ȡ��ɻ�ͼƬ�ľ�̬����---bigplane
		image=ShootGame.bigplane;
		//step2:ʹ��ͼƬ�������Ϊ������
		width=image.getWidth();
		height=image.getHeight();
		//step3:���ô�ɻ���ʼ����ĸ߶�
		y=-height;
		//step4:��ɻ�����ʼ�����x������0~�������ȡ�����ɻ�ͼƬ��ȣ�֮ǰ���ѡ
		Random r=new Random();
		x=r.nextInt(ShootGame.WIDTH-width);
		//��0��1֮�����ѡ��һ�ֽ�������
		awardType=r.nextInt(2);
		
	}
	@Override
	public void step() {
		// TODO Auto-generated method stub
        //ÿ��x�ƶ�һ��xspeed,y�ƶ�һ��yspeed
		x+=xspeed;
		y+=yspeed;
		//��ɻ���������߽磬һ��������ôxspeed*��-1�����൱�ڷ������ƶ�
		if(x<0||x>ShootGame.WIDTH-width) {
			xspeed*=-1;
		}
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		//��ɻ���y����>��Ϸ���棬Խ��
		return y>ShootGame.HEIGHT;
	}

}
