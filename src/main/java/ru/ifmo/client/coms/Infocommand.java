package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;
/**
 * Команда для просмотра информации о коллекции
 */
public class Infocommand extends AbstractCommand{
    /**
     * Команда для просмотра информации о коллекции
     * @param target класс-адресат
     * @param param не учитывается
     */
    public Infocommand(ServerManager target,String[] param) {
        super(target,param);
    }

    @Override
    public String execute() {
        getTarget().addCommandToHistiry("info");
        return getTarget().info();
    }

    @Override
    public String description() {
        return "shows the info about the collection";
    }
}
