package client.coms;

import server.servermanager.ServerManager;

public class LoadCommand extends AbstractCommand{

    private final String filename;

    public LoadCommand(ServerManager target, String[] param) {
        super(target, param);
        filename = param[0];
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("load");
        return getTarget().load(filename);
    }

    @Override
    public String description() {
        return "loads the xml datafile with routes to the database";
    }
}
