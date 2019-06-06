package shoot;

public class Bullet extends Flyer {
    
	private int speed=3;//�ӵ��������ٶ�Ϊ3
	
	/*
	 * �ӵ���Ĵ��ι��췽��
	 * ��Ϊ�ӵ��������λ��Ҫ����Ӣ�۷ɻ���λ�þ���
	 * �����ӵ�������x��yҪ����紫��
	 * @param xӢ�ۻ�ָ���ӵ�����λ�õ�x����
	 * @param yӢ�۷ɻ�ָ������λ�õ�y����
	 * @see shoot.Flyer#step()
	 */
	public Bullet(int x,int y) {
		image=ShootGame.bullet;
		width=image.getWidth();
		height=image.getHeight();
		this.x=x;
		this.y=y;
	}
	@Override
	public void step() {
		// TODO Auto-generated method stub
		//�ӵ�ÿ�������ƶ�һ��speed����
        y-=speed;
	}

	@Override
	public boolean outOfBounds() {
		// TODO Auto-generated method stub
		//�ӵ���y����+�ӵ��ĸ߶�<0,Խ��
		return (y+height)<0;
	}

}
