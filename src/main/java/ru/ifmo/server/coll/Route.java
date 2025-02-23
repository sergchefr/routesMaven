package ru.ifmo.server.coll;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/** Класс, характеризующий некий маршрут.*/
public class Route implements Comparable{
    private final Long id;//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private static Long nextid=1L;
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Location from; //Поле может быть null
    private final Location to; //Поле не может быть null
    private final Float distance; //Поле не может быть null, Значение поля должно быть больше 1


    public Route(Long id, String name, Date creationDate, Location from, Location to, Float distance)throws IOException {
        if(id ==null)throw new IOException("id can`t be null");
        if(Objects.equals(name, ""))throw new IOException("name can`t be null or empty");
        if(from ==null)throw new IOException("from location can`t be null");
        if(to ==null)throw new IOException("destination location can`t be null");

        if(id >=nextid) nextid=id+1;
        this.id = id;
        this.name = name;
        //this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Route(String name,Location from, Location to, Float distance) throws IOException{
        if(Objects.equals(name, ""))throw new IOException("name can`t be null or empty");
        if(from ==null)throw new IOException("from location can`t be null");
        if(to ==null)throw new IOException("destination location can`t be null");

        id=nextid;
        nextid++;
        this.name = name;
        //this.coordinates = coordinates;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.creationDate=new Date();
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return (int)(this.id-((Route)o).id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public Coordinates getCoordinates() {
//        return coordinates;
//    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Float getDistance() {
        return distance;
    }

    public Location getFromLocation(){return from;}

    public Location getToLocation(){return to;}
}
