package model.places;


public class Action {
    protected String name;
    protected String desc;
    protected int time, mental;

    /*
    protected int time
    podriamos migrar todo a esto y hacer que tengan tiempo, luego cambiamos un poco el formato de turno y showstate y tenemos d?as y acciones que tardan horas
    */


    public Action(String name, String desc, int time, int mental) {
        this.name = name;
        this.desc = desc;
        this.time = time;
        this.mental = mental;
    }

    @Override
    public String toString() {
        return name + ": " + desc;
    }
}
