package gui;

import exceptions.InvalidUnitNumberException;
import fight.Damage;
import game.Game;
import gui_data.BlockSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import units.Unit;

public class FightForecasts extends VBox{

	private final static double FORSIGHT_BOX = 0.80 ;
	private final static double BACK_BOX = 0.20 ;
	private BlockSize blockSize;
	
	private Label attackerType;
	private Label defenderType;
	private Label attackerCurrentHealth;
	private Label defenderCurrentHealth;
	private Label attackerHealth;
	private Label defenderHealth;
	private Label attackerTakenDamages;
	private Label defenderTakenDamages;
	private Label vs;
	private Button attack;
	private Button getBack;
	private Label[] receivedDamages;
	
	private VBox forsightBox;
	private VBox backBox;
	
	public FightForecasts(Game game, BlockSize blockSize, GameBlock gameBlock) {
		super();
		setBlockSize(blockSize);
		setPrefSize(getBlockSize().getWidth(), getBlockSize().getHeight());
		
		initializeContent();
		initializeBoxes();
		initializeGetBackClick(gameBlock);
		initializeAttackClick(game, gameBlock);
		
		displayContent();
		setAlignment(Pos.TOP_CENTER);
	}
	
	public void initializeContent() {
		setAttackerType(new Label());
		setDefenderType(new Label());
		setAttackerCurrentHealth(new Label());
		setDefenderCurrentHealth(new Label());
		setAttackerHealth(new Label());
		setDefenderHealth(new Label());
		setAttackerTakenDamages(new Label());
		setDefenderTakenDamages(new Label());
		setVs(new Label());
		
		setReceivedDamages(new Label[2]);
		getReceivedDamages()[0] = new Label();
		getReceivedDamages()[1] = new Label();
		getReceivedDamages()[0].setText("Received damages :");
		getReceivedDamages()[1].setText("Received damages :");
		
		getAttackerType().setId("type");
		getDefenderType().setId("type");
		getVs().setText("VS");
		getVs().setId("vs");
		
		setAttack(new Button());
		getAttack().setText("Attack");
		getAttack().setId("menu_bar_button");	
		setGetBack(new Button());
		getGetBack().setText("Back");
		getGetBack().setId("menu_bar_button");		
	}
	
	public void initializeBoxes() {
		setForsightBox(new VBox());
		setBackBox(new VBox());
		
		getForsightBox().setPrefHeight(getBlockSize().getHeight()*FORSIGHT_BOX);
		getBackBox().setPrefHeight(getBlockSize().getHeight()*BACK_BOX);
		getForsightBox().setAlignment(Pos.TOP_CENTER);
		getBackBox().setAlignment(Pos.BOTTOM_CENTER);

		getForsightBox().setId("spacing");
		getBackBox().setId("spacing");
	}
	public void displayContent() {
		getForsightBox().getChildren().add(getAttackerType());
		getForsightBox().getChildren().add(getAttackerCurrentHealth());
		getForsightBox().getChildren().add(getReceivedDamages()[0]);
		getForsightBox().getChildren().add(getAttackerTakenDamages());
		getForsightBox().getChildren().add(getAttackerHealth());
		getForsightBox().getChildren().add(getVs());
		getForsightBox().getChildren().add(getDefenderType());
		getForsightBox().getChildren().add(getDefenderCurrentHealth());
		getForsightBox().getChildren().add(getReceivedDamages()[1]);
		getForsightBox().getChildren().add(getDefenderTakenDamages());
		getForsightBox().getChildren().add(getDefenderHealth());
		getForsightBox().getChildren().add(getAttack());
		
		getBackBox().getChildren().add(getGetBack());

		getChildren().add(getForsightBox());
		getChildren().add(getBackBox());
	}
	public void initializeGetBackClick(GameBlock gameBlock) {
		getGetBack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				gameBlock.getLeftMenu().getUsualLeftMenu().setVisible(true);
				gameBlock.getLeftMenu().getUsualLeftMenu().toFront();
				gameBlock.getLeftMenu().getFightForecasts().setVisible(false);
			}
		});
	}
	public void initializeAttackClick(Game game, GameBlock gameBlock) {
		getAttack().setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				
			}
		});
	}
	public void update(Unit attacker, Unit defender, Damage forsights) throws InvalidUnitNumberException{
		getAttackerType().setText(attacker.getName());
		getDefenderType().setText(defender.getName());
		getAttackerCurrentHealth().setText("PV :"+attacker.getCurrentHealth());
		getDefenderCurrentHealth().setText("PV :"+defender.getCurrentHealth());
		getAttackerHealth().setText("Result : ~"+forsights.getAttackerMinRemainingHealth()+" - "+forsights.getAttackerMaxRemainingHealth()+" PV");
		getDefenderHealth().setText("Result : ~"+forsights.getDefenderMinRemainingHealth()+" - "+forsights.getDefenderMaxRemainingHealth()+" PV");
		getAttackerTakenDamages().setText("~"+forsights.getMinRevengeDamage()+" - "+forsights.getMaxRevengeDamage()+" PV");
		getDefenderTakenDamages().setText("~"+forsights.getMinAttackDamage()+" - "+forsights.getMaxAttackDamage()+" PV");
	}

	public BlockSize getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(BlockSize blockSize) {
		this.blockSize = blockSize;
	}

	public Label getAttackerType() {
		return attackerType;
	}

	public void setAttackerType(Label attackerType) {
		this.attackerType = attackerType;
	}

	public Label getDefenderType() {
		return defenderType;
	}

	public void setDefenderType(Label defenderType) {
		this.defenderType = defenderType;
	}

	public Label getAttackerCurrentHealth() {
		return attackerCurrentHealth;
	}

	public void setAttackerCurrentHealth(Label attackerCurrentHealth) {
		this.attackerCurrentHealth = attackerCurrentHealth;
	}

	public Label getDefenderCurrentHealth() {
		return defenderCurrentHealth;
	}

	public void setDefenderCurrentHealth(Label defenderCurrentHealth) {
		this.defenderCurrentHealth = defenderCurrentHealth;
	}

	public Label getAttackerHealth() {
		return attackerHealth;
	}

	public void setAttackerHealth(Label attackerHealth) {
		this.attackerHealth = attackerHealth;
	}

	public Label getDefenderHealth() {
		return defenderHealth;
	}

	public void setDefenderHealth(Label defenderHealth) {
		this.defenderHealth = defenderHealth;
	}

	public Label getAttackerTakenDamages() {
		return attackerTakenDamages;
	}

	public void setAttackerTakenDamages(Label attackerTakenDamages) {
		this.attackerTakenDamages = attackerTakenDamages;
	}

	public Label getDefenderTakenDamages() {
		return defenderTakenDamages;
	}

	public void setDefenderTakenDamages(Label defenderTakenDamages) {
		this.defenderTakenDamages = defenderTakenDamages;
	}

	public Label getVs() {
		return vs;
	}

	public void setVs(Label vs) {
		this.vs = vs;
	}

	public Button getAttack() {
		return attack;
	}

	public void setAttack(Button attack) {
		this.attack = attack;
	}

	public Button getGetBack() {
		return getBack;
	}

	public void setGetBack(Button getBack) {
		this.getBack = getBack;
	}

	public VBox getBackBox() {
		return backBox;
	}

	public void setBackBox(VBox backBox) {
		this.backBox = backBox;
	}

	public VBox getForsightBox() {
		return forsightBox;
	}

	public void setForsightBox(VBox forsightBox) {
		this.forsightBox = forsightBox;
	}

	public Label[] getReceivedDamages() {
		return receivedDamages;
	}

	public void setReceivedDamages(Label[] receivedDamages) {
		this.receivedDamages = receivedDamages;
	}

	
}
