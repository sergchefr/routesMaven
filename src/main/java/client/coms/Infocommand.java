package client.coms;

import server.servermanager.ServerManager;

public class Infocommand extends AbstractCommand{

    public Infocommand(ServerManager target,String[] param) {
        super(target,param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistory("info");
        return getTarget().info();
    }

    @Override
    public String description() {
        return "shows the info about the collection";
    }
}
