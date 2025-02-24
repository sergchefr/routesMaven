package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;
/**
 * Команда для просмотра истории выполненных команд(кроме help)
 */
public class HistoryCommand extends AbstractCommand{
    /**
     * Команда для просмотра истории выполненных команд(кроме help)
     * @param target класс-адресат
     * @param param не учитывается
     */
    public HistoryCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("history");
        return getTarget().showHistory();
    }

    @Override
    public String description() {
        return "shows the last 12 commands";
    }
}
