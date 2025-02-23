import client.clientmanager.ClientManager;
import client.consoleIO.ConsoleIO;
import server.coll.*;
import client.coms.*;
import server.servermanager.ServerManager;
public class Main {
    public static void main(String[] args) {
        TreeSetHandler tsh = new TreeSetHandler();

        ServerManager servermanager =  new ServerManager(null,tsh);
        ClientManager clientManager = new ClientManager(null);
        clientManager.setServerManager(servermanager);
        servermanager.setClientManager(clientManager);

        clientManager.init("JAVA_SAVE_ROUTE");//переменная окружения

        clientManager.addnewCommand("add", AddCommand.class);
        clientManager.addnewCommand("add_if_max", AddIfMaxCommand.class);
        clientManager.addnewCommand("add_if_min", AddIfMinCommand.class);
        clientManager.addnewCommand("info", Infocommand.class);
        clientManager.addnewCommand("show", ShowCommand.class);
        clientManager.addnewCommand("update", UpdateCommand.class);
        clientManager.addnewCommand("remove_by_id", RemoveByIdCommand.class);
        clientManager.addnewCommand("clear", ClearCommand.class);
        clientManager.addnewCommand("history", HistoryCommand.class);
        clientManager.addnewCommand("execute_script", ExecuteScriptCommand.class);
        clientManager.addnewCommand("exit", ExitCommand.class);
        clientManager.addnewCommand("average_of_distance", AVGdistanceCommand.class);
        clientManager.addnewCommand("print_ascending", PrintAscCommand.class);
        clientManager.addnewCommand("print_field_ascending_distance", PrintFieldAscDistCommand.class);
        clientManager.addnewCommand("save", SaveCommand.class);
        clientManager.addnewCommand("load", LoadCommand.class);

        ConsoleIO console= new ConsoleIO(clientManager);
        console.start();
    }
}//execute_script C:\Users\sergei\Desktop\routesave\script.txt