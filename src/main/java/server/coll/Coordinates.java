package server.coll;
/** Координаты чего-то. Зачем они? Не знаю. Пусть так и останется*/
public class Coordinates {
    private Long x; //Поле не может быть null
    private Integer y; //Поле не может быть null

    public Coordinates(Long x, Integer y){
    if(x==null|y==null) throw new NumberFormatException();
    this.x = x;
    this.y = y;
    }
}
