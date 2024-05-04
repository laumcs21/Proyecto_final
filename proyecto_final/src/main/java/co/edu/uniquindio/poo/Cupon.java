package co.edu.uniquindio.poo;

public class Cupon {

    public String nombreCupon;
    public int porcentajeDescuento;

    public Cupon(String nombreCupon, int porcentajeDescuento) {

        assert nombreCupon != null;
        assert porcentajeDescuento >= 0 && porcentajeDescuento <= 100;

        this.nombreCupon = nombreCupon;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getNombreCupon() {
        return nombreCupon;
    }

    public void setNombreCupon(String nombreCupon) {
        this.nombreCupon = nombreCupon;
    }

    public int getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(int porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

}
