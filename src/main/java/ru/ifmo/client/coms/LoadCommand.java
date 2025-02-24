package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;
/**
 * Команда для загрузки классов в коллекцию из XML
 */
public class LoadCommand extends AbstractCommand{

    private final String filename;
    /**
     * Команда для загрузки классов в коллекцию из XML
     * @param target класс-адресат
     * @param param путь к XML
     */
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
