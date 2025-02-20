package client.coms;

import server.servermanager.ServerManager;

public class HistoryCommand extends AbstractCommand{

    public HistoryCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistory("history");
        return getTarget().showHistory();
    }

    @Override
    public String description() {
        return "shows the last 12 commands";
    }
}
