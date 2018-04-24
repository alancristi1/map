package model;

/**
 * Created by alan.resende on 30/01/2018.
 */

public class pontoModel {
    private int id;
    private String codigo;
    private String nome;
    private String endereco;
    private double latitude;
    private double longitude;
    private String dataHora;
    private int enviado;

    public pontoModel(String nome, String endereco, double latitude, double longitude, String dataHora) {
        this.nome = nome;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataHora = dataHora;
    }

    public pontoModel(String nome, String endereco, String dataHora) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataHora = dataHora;
    }

    public pontoModel(String nome, String endereco, String codigo, double latitude, double longitude) {
        this.nome = nome;
        this.endereco = endereco;
        this.codigo = codigo;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public pontoModel(String codigo, String nome, String endereco, double latitude, double longitude, String dataHora) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataHora = dataHora;
    }

    public pontoModel(int idrow, String codigo, String nome, String endereco) {
        this.id = idrow;
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;

    }

    public pontoModel(int idrow, String codigo, String nome, String endereco, double latitude,
                      double longitude, String dataHora) {
        this.id = idrow;
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getEnviado() {
        return enviado;
    }

    public void setEnviado(int enviado) {
        this.enviado = enviado;
    }
}
