package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alan.resende on 05/02/2018.
 */

public class clienteModel {

    @SerializedName("pontosEmRaio")
    @Expose
    private List<clientesModel> clientes = null;

    public List<clientesModel> getClientes() {
        return clientes;
    }

    public void setClientes(List<clientesModel> clientes) {
        this.clientes = clientes;
    }

}
