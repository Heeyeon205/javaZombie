package zombie;

//공격 메소드 void attack( Unit enemy ) 
//일반공격 : 1~공격력(max) 사이의 랜덤 값을 공격력으로 사용하여 enemy의 체력 감소시킴 
//	(출력 예 : “보스가 15 의 공격력으로 일반 공격 : 현재 Hero의 hp는 25”)
//필살기 : 25%의 확률로 1~max 사이의 공격력의 2배 만큼으로 상대를 공격하는 기능 
//(출력 예 : “보스가 30 의 공격력으로 필살기 공격 : 현재 Hero의 hp는 25”)
//Boss 클래스는 생성자를 가짐(현재위치, 체력,공격력, 보호막)

public class Boss extends Zombie{
	private int shield;
	private final int MAX_SHIELD;
	Utils utils;

	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {     
		this.shield = shield;
	}

	public Boss(String name, int pos, int power, int hp, int shield) {
		super(name, pos, power, hp);
		this.shield = shield;
		MAX_SHIELD = shield;
		utils = Utils.getInstance();
	}
	@Override
	public String toString() {
		if(this.getShield() > 0) {
			return String.format("%s의 남은 쉴드: %d/%d\n", this.getName(), this.getShield(), MAX_SHIELD);
		}
		return String.format("%s의 HP: %d\n", this.getName(), this.getHp());
	}
	
	protected void attack(Unit unit) {
		if(this.getHp() <= 0) return;
		int rdNum = utils.getRandomValue(4);
		this.setDamage(utils.getRandomValue(this.getPower())+1);
		if(rdNum == 0) {
			int intensiveAtk = this.getDamage() * 2;
			unit.setHp(unit.getHp()-intensiveAtk);
			if(unit.getHp() <= 0) {
				unit.setHp(0);
			}
			System.out.printf("\n%s가 %s를 %d의 데미지로 필살기 공격!\n %s\n",  this.getName(), unit.getName(), intensiveAtk, unit);
		}else {
		unit.setHp(unit.getHp()-this.getDamage());
		if(unit.getHp() <= 0) {
			unit.setHp(0);
		}
		System.out.printf("\n%s가 %s를 %d의 데미지로 공격했습니다!\n %s\n",  this.getName(), unit.getName(), this.getDamage(), unit);
		}
	}
}
