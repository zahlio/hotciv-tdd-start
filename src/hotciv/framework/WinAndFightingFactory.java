package hotciv.framework;

public interface WinAndFightingFactory {

	public WinStrategy createWinner();
	
	public AttackStrategy createAttack();
}
