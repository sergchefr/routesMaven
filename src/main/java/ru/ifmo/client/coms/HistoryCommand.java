package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;

public class HistoryCommand extends AbstractCommand{

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
