package ru.ifmo.server.coll;

import java.util.Objects;

/** Класс, характеризующий некую локацию. используется в {@link Route}*/
public class Location {
    private final Integer x; //Поле не может быть null
    private final Integer y; //Поле не может быть null
    private final Float z; //Поле не может быть null
    private final String name; //Строка не может быть пустой, Поле не может быть null

    public Location(Integer x, Integer y, Float z, String name) {
        if(x==null|y==null|z==null| Objects.equals(name,"")) throw new NumberFormatException();
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
        //System.out.println("location created");
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }
}
