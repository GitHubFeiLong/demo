package ssm.utils;

public class Pojo {

    private Byte bytenum;
    private Character charnum;
    private Short shortnum;
    private Integer intnum;
    private Long longnum;
    private Float floatnum;
    private Double doublenum;

    public Pojo(byte bytenum, char charnum, short shortnum, int intnum, long longnum, float floatnum, double doublenum) {
        this.bytenum = bytenum;
        this.charnum = charnum;
        this.shortnum = shortnum;
        this.intnum = intnum;
        this.longnum = longnum;
        this.floatnum = floatnum;
        this.doublenum = doublenum;
    }

    public Pojo() {
    }

    @Override
    public String toString() {
        return "Pojo{" +
                "bytenum=" + bytenum +
                ", charnum=" + charnum +
                ", shortnum=" + shortnum +
                ", intnum=" + intnum +
                ", longnum=" + longnum +
                ", floatnum=" + floatnum +
                ", doublenum=" + doublenum +
                '}';
    }

    public byte getBytenum() {
        return bytenum;
    }

    public void setBytenum(byte bytenum) {
        this.bytenum = bytenum;
    }

    public char getCharnum() {
        return charnum;
    }

    public void setCharnum(char charnum) {
        this.charnum = charnum;
    }

    public short getShortnum() {
        return shortnum;
    }

    public void setShortnum(short shortnum) {
        this.shortnum = shortnum;
    }

    public int getIntnum() {
        return intnum;
    }

    public void setIntnum(int intnum) {
        this.intnum = intnum;
    }

    public long getLongnum() {
        return longnum;
    }

    public void setLongnum(long longnum) {
        this.longnum = longnum;
    }

    public float getFloatnum() {
        return floatnum;
    }

    public void setFloatnum(float floatnum) {
        this.floatnum = floatnum;
    }

    public double getDoublenum() {
        return doublenum;
    }

    public void setDoublenum(double doublenum) {
        this.doublenum = doublenum;
    }
}
