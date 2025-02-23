package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;

public class ClearCommand extends AbstractCommand {
    public ClearCommand(ServerManager target, String[] param) {
        super(target,param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("clear");
        return getTarget().clear();
    }

    @Override
    public String description() {
        return "deletes all elements from the collection";
    }
}
