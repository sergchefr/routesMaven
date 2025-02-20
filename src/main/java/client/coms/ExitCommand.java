package client.coms;

import server.servermanager.ServerManager;

public class ExitCommand extends AbstractCommand

{
    public ExitCommand(ServerManager target, String[] param) {
        super(target, param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("exit");
        return getTarget().exit();
    }

    @Override
    public String description() {
        return "use it to exit the program";
    }
}
