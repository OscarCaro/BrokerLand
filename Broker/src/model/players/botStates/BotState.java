package model.players.botStates;

import model.players.Bot;

public interface BotState {
    void updateSocialStrategy(Bot b);
    void updateActionStrategy(Bot b);
    void update(Bot b);
}
