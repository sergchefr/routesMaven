package client.coms;

import server.servermanager.ServerManager;

public class ShowCommand extends AbstractCommand{

    public ShowCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("show");
        return getTarget().show();
    }

    @Override
    public String description() {
        return "shows the collection`s elements";
    }
}
