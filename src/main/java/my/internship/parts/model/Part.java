package my.internship.parts.model;

import javax.persistence.*;

@Entity     //сообщаем что данный класс является сущностью
@Table(name="parts")    //указываем с какой таблицей он связан
public class Part {
    @Id //сообщаем что данное поле является id
    @Column(name="ID")  //связываем его с полем в таблице
    @GeneratedValue(strategy = GenerationType.IDENTITY) //сообщаем что это генерируемое значене
    private int id;

    @Column(name="PART_NAME")   //связываем данное поле с полем в таблице
    private String partName;

    @Column(name="PART_BASE")   //связываем данное поле с полем в таблице
    private boolean partBase;

    @Column(name="PART_VALUE")   //связываем данное поле с полем в таблице
    private int partValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public boolean isPartBase() {
        return partBase;
    }

    public void setPartBase(boolean partBase) {
        this.partBase = partBase;
    }

    public int getPartValue() {
        return partValue;
    }

    public void setPartValue(int partValue) {
        this.partValue = partValue;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", partName='" + partName + '\'' +
                ", partBase=" + partBase +
                ", partValue=" + partValue +
                '}';
    }
}
