package model.players.botBuild.factories;

import controller.Difficulties.Difficulty;
import model.players.Bot;
import model.players.marketstrategies.MarketStrategy;
import model.players.marketstrategies.aggressiveStrategy;
import model.players.marketstrategies.dumbassStrategy;

public class DumbassBotFactory extends BotFactory {

	@Override
	public Bot generateBot(Difficulty diff) {
		MarketStrategy ms = new dumbassStrategy();
		Double adap = diff.getAdaptabilityRatio();
		setGenericAttributes();
		return new Bot(name, surname, initLocIdx, money, ms, adap, sex);
	}

}
