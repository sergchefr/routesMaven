package client.coms;

import server.servermanager.ServerManager;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(ServerManager target, String[] param) {
        super(target,param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistory("clear");
        return getTarget().clear();
    }

    @Override
    public String description() {
        return "deletes all elements from the collection";
    }
}
