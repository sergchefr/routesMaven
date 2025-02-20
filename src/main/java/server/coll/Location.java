package server.coll;
/** Класс, характеризующий некую локацию. Используется в {@link Route#}*/
public class Location {
    private Integer x; //Поле не может быть null
    private Integer y; //Поле не может быть null
    private Float z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location(Integer x, Integer y, Float z, String name) {
        if(x==null|y==null|z==null|name==null|name=="") throw new NumberFormatException();
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
