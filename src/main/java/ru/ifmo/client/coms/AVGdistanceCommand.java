package ru.ifmo.client.coms;

import ru.ifmo.server.servermanager.ServerManager;

/**
 * Команда для получения средней дистанции из всех элементов в коллекции
 */
public class AVGdistanceCommand extends AbstractCommand{
    /**
     *Команда для получения средней дистанции из всех элементов в коллекции
     * @param target класс-адресат
     * @param param не учитывается
     */
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
