package hotciv.framework;

public interface CivFactory {
	
	public AgingStrategy createAging();

	public WinStrategy createWinner();
	
	public UnitActionStrategy createUnitAction();
	
	public WorldLayoutStrategy createLayout();
	
	public AttackStrategy createAttack();
}
