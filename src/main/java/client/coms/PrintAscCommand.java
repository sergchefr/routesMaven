package client.coms;

import server.servermanager.ServerManager;

public class PrintAscCommand extends AbstractCommand{
    public PrintAscCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("print_ascending");
        return getTarget().printAsc();
    }

    @Override
    public String description() {
        return "shows elements of the collection, sorted by their distance";
    }
}
