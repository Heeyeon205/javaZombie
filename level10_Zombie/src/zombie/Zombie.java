package zombie;
//좀비는 히어로 공격 가능하고 한턴이 지날때마다 공격한 대미지의 50%를 회복하는 능력을 가지고 있다 
public class Zombie extends Unit {
	Utils utils;

	public Zombie(String name, int pos, int power, int hp) {
		super(name, pos, power, hp);
		utils = Utils.getInstance();
	}
	
	protected void attack(Unit unit) {
		if(this.getHp() <= 0) return;
		this.setDamage(utils.getRandomValue(this.getPower())+1);
		unit.setHp(unit.getHp()-this.getDamage());
		if(unit.getHp() <= 0) {
			unit.setHp(0);
		}
		System.out.printf("%s가 %s를 %d의 데미지로 공격했습니다!\n %s\n",  this.getName(), unit.getName(), this.getDamage(), unit);
		if(this.getHp() != 0) {
		this.setHp(this.getHp() + this.getDamage()/2);
		if(this.getHp() > this.getMAX_HP()) {
			this.setHp(this.getMAX_HP());
		}
		System.out.printf("%s의 재생! %s\n", this.getName(), this);
		}
	}
}
