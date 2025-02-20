package client.coms;

import server.servermanager.ServerManager;

public class AVGdistanceCommand extends AbstractCommand{

    public AVGdistanceCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("average_of_distance");
        return getTarget().avgdistance();
    }

    @Override
    public String description() {
        return "shows the average distance of the routes in the collection";
    }
}
