package zombie;
//보스인지 아닌지 구분해서 공격 
//히어로는 체력 포션으로 100 체력 회복할 수 있고 체력 포션이 없으면 체력 회복이 안된다 
//외부의 적은 보스인지 아닌지 구별해서 공격 해야한다 
//보스이면 보호막을 가지고 있다 먼저 보호막을 다 뚫어야지만 직접 보스를 공격할 수 있다. 
//일반 적은 보호막 가지고 있지 않음 
public class Hero extends Unit {
	private int potionCnt;
	Utils utils;
	
	public Hero(String name, int pos, int power, int hp, int potionCnt) {
		super(name, pos, power, hp);
		this.potionCnt = potionCnt;
		utils = Utils.getInstance();
	}

	public void usePotion() {
		if(potionCnt > 0) {
			this.setHp(this.getHp()+100);
			System.out.println("영웅 HP 100 회복!");
			potionCnt--;
		}else {
			System.out.println("포션이 부족합니다!");
		}
		System.out.println(this);
	}

	public void bossAttack(Boss boss) {
		this.setDamage(utils.getRandomValue(this.getPower())+1);
		if(boss.getShield() > 0) {
			boss.setShield(boss.getShield()-this.getDamage());
			if(boss.getShield() <= 0) {
				boss.setShield(0);
				System.out.printf("%s의 쉴드 파괴! %s의 체력이 드러납니다.\n", boss.getName(), boss.getName());
			}
			System.out.printf("%s가 %s를 %d의 데미지로 공격했습니다!\n %s\n", this.getName(), boss.getName(), this.getDamage(), boss);
		}else {
			boss.setHp(boss.getHp()-this.getDamage());
			if(boss.getHp() <= 0) {
				boss.setHp(0);
			}
			System.out.printf("%s가 %s를 %d의 데미지로 공격했습니다!\n %s\n", this.getName(), boss.getName(), this.getDamage(), boss);
		}
	}
	
	public boolean isDead() {
		if(this.getHp() == 0) {
			return true;
		}
		return false;
	}

}
