package client.coms;

import server.servermanager.ServerManager;

public class PrintFieldAscDistCommand extends AbstractCommand{
    public PrintFieldAscDistCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistory("print_field_ascending_distance");
        return getTarget().printAscDist();
    }

    @Override
    public String description() {
        return "shows the sorted distances of the elements of the collection";
    }
}
